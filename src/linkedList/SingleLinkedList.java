package linkedList;

public class SingleLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;
    public int size;
    public Node createSingleLinkedList( T value){
        head=new Node<>();
        head.data=value;
        tail=head;
        size=1;
        return head;
    }

    public void insertE(T value, int index){
        Node<T> node=new Node<>();
        node.data=value;
        if(head==null){
            createSingleLinkedList(value);
            return;
        }
        if(index==0){
            node.next=head;
            head=node;

        }
        if(index>=size){
            node.next=null;
            tail.next=node;
            tail=node;

        }
        else{
            Node<T> temp=head;
            int i=0;
            while(i<index-1){
                temp=temp.next;
                i++;
            }
            Node<T> nextNode = temp.next;
            temp.next = node;
            node.next = nextNode;

        }
        size++;
    }
    public void deleteI(int index){
        if(head==null){
            System.out.println("Empty List");
        }
        if(index==0){
            head=head.next;
            size--;
        }
        else if(index>=size-1){
            Node<T> tempNode = head;
            for (int i = 0; i < size - 1; i++) {
                tempNode = tempNode.next;
            }
            if (tempNode == head) {
                tail = head = null;
                size--;
                return;
            }
            tempNode.next = null;
            tail = tempNode;
            size--;
        }
        else{
            Node<T> tempNode = head;
            for (int i = 0; i <index-1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            size--;
        }
    }
    public void delete(T value){
        Node<T> temp=head;
        Node<T> previous=null;
        while(temp!=null){
            if(head.data.equals(value)){
                head=head.next;
            }
            if(temp.data.equals(value)){
                if(temp.next==null){
                    previous.next=null;
                    tail=temp;
                    tail.next=null;

                }
                else{
                    previous.next=temp.next;

                }

                size--;
                break;
            }
            previous=temp;
            temp=temp.next;
        }
    }
    public void showLinkedList(){
        Node<T> temp=head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
    }
    public void clear(){
        head=null;
        size=0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public String rotate(int number) {
        int index = number;
        if (number < 0) {
            index = number + size;
        }
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return "No Rotation";
        }
        Node prevNode = head;
        for(int i=0; i<index-1; i++) {
            prevNode = prevNode.next;
        }
        if (prevNode == null) {
            return "No Rotation";
        }
        tail.next = head;
        head = prevNode.next;
        tail = prevNode;
        prevNode.next = null;
        return "Success";
    }
    public boolean set(int index, T value) {
        if (head == null) {
            head.data = value;
            tail.data = value;
        } else {
            Node<T> currentNode = head;
            for (int i =0; i<index; i++) {
                currentNode = currentNode.next;
                if (currentNode == null) {
                    return false;
                }
            }
            currentNode.data = value;
        }
        return true;
    }

}
