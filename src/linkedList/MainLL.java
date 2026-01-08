package linkedList;

public class MainLL {
    static void main() {
        SingleLinkedList<Integer> singleLinkedList=new SingleLinkedList<>();
        singleLinkedList.createSingleLinkedList(10);
        singleLinkedList.insertE(11,1);
        singleLinkedList.insertE(12,2);
        singleLinkedList.insertE(13,4);
        singleLinkedList.insertE(14,9);

        singleLinkedList.showLinkedList();
        System.out.println();

        singleLinkedList.delete(11);
        singleLinkedList.delete(13);
        singleLinkedList.delete(12);
        singleLinkedList.showLinkedList();
        System.out.println();

    }
}
