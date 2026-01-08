package linkedList;
import java.util.LinkedList;

public class JavaLinkedList {
    static void main() {
        LinkedList<Integer> list = new LinkedList<>();

        // “crear” lista (en Java simplemente empiezas vacía y agregas)
        list.add(10);

        // equivalente a insertE(value, index)
        list.add(1, 11);
        list.add(2, 12);

        // si el índice es inválido, Java lanza excepción
        // list.add(4, 13); // <- esto explota porque size=3 (índices válidos 0..3)
        // list.add(9, 14); // <- también explota

        // Para simular tu lógica de "si index>=size, lo mete al final":
        addSafe(list, 13, 4);
        addSafe(list, 14, 9);

        System.out.println(list); // imprime [10, 11, 12, 13, 14]

        // equivalente a delete(value)
        list.remove(Integer.valueOf(11));
        list.remove(Integer.valueOf(13));
        list.remove(Integer.valueOf(12));

        System.out.println(list); // imprime [10, 14]
    }
    static void addSafe(LinkedList<Integer> list, int value, int index) {
        if (index <= 0) list.addFirst(value);
        else if (index >= list.size()) list.addLast(value);
        else list.add(index, value);
    }
}
