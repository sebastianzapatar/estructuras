package linkedList;

public class MainLL {
    static void main() {
        SingleLinkedList<Integer> singlyLinkedList = new SingleLinkedList();
        singlyLinkedList.insertE(5,1);  // Success

        System.out.println(singlyLinkedList.head.data); // 5
        System.out.println(singlyLinkedList.tail.data); // 5
        System.out.println(singlyLinkedList.size);
        singlyLinkedList.insertE(10,4);    // Success
        System.out.println(singlyLinkedList.head.data);
        System.out.println(singlyLinkedList.size);
        System.out.println(singlyLinkedList.head.next.data);
        System.out.println(singlyLinkedList.tail.data);

    }
}
