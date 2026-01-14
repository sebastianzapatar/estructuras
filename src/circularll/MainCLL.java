package circularll;

public class MainCLL {
    public static void main(String[] args) {
        CircularLinkedList<Integer> cll=new CircularLinkedList<>();
        cll.createLinkedList(10);
        IO.println(cll.head.value);
        IO.println(cll.tail.value);
        IO.println(cll.head.next.value);
    }
}