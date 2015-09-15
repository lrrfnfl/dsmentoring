
import java.util.Scanner;

/**
 * The type Linked list.
 */
public class LinkedList {
    /**
     * The Head.
     */
    Node head; // 첫번째 노드
    /**
     * The Tail.
     */
    Node tail; // 마지막 노드

    /**
     * The Size.
     */
    int size = 0; // 연결리스트의 크기

    /**
     * The type Node.
     */
    public class Node {
        /**
         * The Next.
         */
        Node next; // 다음 노드를 가리키는 필드
        /**
         * The Prev.
         */
        Node prev; // 이전 노드를 가리키는 필드
        /**
         * The Data.
         */
        Object data; // 저장될 데이터는 타입을 구분하지 않게 선언

        /**
         * Instantiates a new Node.
         *
         * @param input the input
         */
        Node(Object input) {
            this.data = input;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Node node.
     *
     * @param order the order
     * @return the node
     */
    Node node(int order) { // 노드 탐색을 위한 메소드
        if (order < size / 2) { // 탐색하려는 노드가 리스트의 앞부분에 있는지 뒷부분에 있는지 구별
            Node number = head;
            for (int i = 1; i <= order; i++) { // 1번째부터 order번째까지 이동하면서
                number = number.next;       // order번째 노드의 값을 리턴
            }
            return number;
        } else {
            Node number = tail;
            for (int i = size - 1; i > order; i--) { // 마지막부터 order번째까지 이동
                number = number.prev;
            }
            return number;
        }
    }

    /**
     * Create list.
     *
     * @param input the input
     */
    public void CreateList(Object input) { // 리스트(첫 노드)를 생성하는 메소드
        Node newNode = new Node(input);
        head = newNode;
        tail = newNode;
        newNode.next = null;
        newNode.prev = null;

        size++;
    }

    /**
     * Add first.
     *
     * @param input the input
     */
    public void AddFirst(Object input) { // 리스트의 맨 앞에 노드를 추가하는 메소드
        Node newNode = new Node(input);
        if (size == 0) {
            CreateList(input);
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        newNode.prev = null;

        size++;
    }

    /**
     * Add last.
     *
     * @param input the input
     */
    public void AddLast(Object input) { // 리스트의 맨 뒤에 노드를 추가하는 메소드
        Node newNode = new Node(input);
        if (size == 0) {
            CreateList(input);
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            newNode.next = null;

            size++;
        }
    }

    /**
     * Add void.
     *
     * @param order the order
     * @param input the input
     */
// 최종 노드 추가 메소드 Add(int order, Object input)
    public void Add(int order, Object input) { // 리스트의 사이사이에 노드를 추가하는 메소드
        if (size == 0) {
            CreateList(input);
            Input();
        } else {
            if (order == 1) { // 1번째에 노드를 추가하는것
                AddFirst(input);
            } else { // ( ex/ order = 2 라면, 새로운 노드를 두번째에 위치하게끔 추가)
                Node prevNode = node(order - 2); // 추가될 위치의 이전노드를 지정(첫노드를 1로 지정했기 때문에 -2)
                if (prevNode == tail) { // 이전노드가 마지막 노드일 경우
                    AddLast(input);
                } else {
                    Node nextNode = node(order); // 추가될 위치의 다음노드를 지정
                    Node newNode = new Node(input);
                    newNode.prev = prevNode;
                    newNode.next = nextNode;
                    prevNode.next = newNode;
                    nextNode.prev = newNode;

                    size++;
                }
            }
        }
    }

    /**
     * Remove list.
     */
    public void RemoveList() { // 노드가 하나인 리스트에서 노드를 삭제할때
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Delete first.
     */
    public void DeleteFirst() { // 리스트의 맨 앞의 노드를 삭제하는 메소드
        if (size == 1) { // 만약 노드가 하나라면 RemoveList()실행
            RemoveList();
        } else {
            Node headNode = head.next;
            head = headNode;
            headNode.prev = null;

            size--;
        }
    }

    /**
     * Delete last.
     */
    public void DeleteLast() { // 리스트의 맨 뒤의 노드를 삭제하는 메소드
        if (size == 1) { // 만약 노드가 하나라면 RemoveList()실행
            RemoveList();
        } else {
            Node tailNode = tail.prev;
            tail = tailNode;
            tailNode.next = null;

            size--;
        }
    }

    /**
     * Delete void.
     *
     * @param order the order
     */
// 최종 노드 삭제 메소드
    public void Delete(int order) { // order번째 노드를 삭제하는 메소드
        if (size == 0) {
            System.out.println("리스트가 없습니다.");
        }
        else if (size == 1) { // 만약 노드가 하나라면 RemoveList()실행
            RemoveList();
        } else {
            Node deleteNode = node(order-1);
            if (deleteNode == head) { // 만약 노드가 첫번째라면 DeleteFirst()실행
                DeleteFirst();
            } else if (deleteNode == tail) { // 만약 노드가 마지막노드라면 DeleteLast()실행
                DeleteLast();
            } else {
                Node prevNode = node(order - 2);
                Node nextNode = node(order);
                prevNode.next = nextNode;
                nextNode.prev = prevNode;

                size--;
            }
        }
    }

    /**
     * Print list.
     */
    public void printList() { // 리스트를 출력하는 메소드
        if (size == 0) {
            System.out.println("리스트가 없습니다.");
        } else {
            Node headNode = head;

            while (headNode != null) {
                System.out.print(" [" +headNode.data + "] ");
                headNode = headNode.next;
            }
            System.out.println();
        }
    }

    /**
     * Search void.
     *
     * @param order the order
     */
    public void Search(int order) { // 노드의 순서를 입력받아 검색한 후, 그 노드의 값을 출력
        if (size == 0) {
            System.out.println("리스트가없습니다");
        } else {
            Node searchNode = node(order-1);
            System.out.println(searchNode.data);
        }
    }

    /**
     * Input void.
     */
    public void Input() { // 사용자가 번호를 입력하여 추가, 삭제, 검색, 출력을 선택
        System.out.println();
        System.out.println("                번호를 입력하세요.");
        System.out.println("1.노드추가  2.노드삭제  3.노드검색  4.리스트출력  5.프로그램종료");

        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();


        switch (num) { // 사용자가 입력한 번호에 따라 case 별로 기능 분류
            case 1: { // Add()함수를 실행하기위해 추가될 리스트의 순서와 데이터필드값을 입력
                int AddOrder = sc.nextInt();
                Object AddInput = sc.nextLine();
                Add(AddOrder, AddInput);
                break;
            }
            case 2: { // Delete()함수를 실행하기위해 삭제할 리스트의 순서를 입력
                int DeleteOrder = sc.nextInt();
                Delete(DeleteOrder);
                break;
            }
            case 3: { // Search()함수를 실행하기위해 검색할 리스트의 순서를 입력
                int SearchOrder = sc.nextInt();
                Search(SearchOrder);
                break;
            }
            case 4: { // 리스트 출력
                printList();
                break;
            }
            case 5: { // 프로그램 종료
                System.exit(0);
            }
            default: {
                System.out.println("입력값이 잘못되었습니다.");
                break;
            }
        } Input(); // 사용자가 프로그램을 종료하기전에 프로그램이 종료되는것을 방지하기위해 Input()함수 루프.
    }
}