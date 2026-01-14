package circularll;

public class CircularLinkedList<T> {

    // Referencia al primer nodo de la lista
    public Node<T> head;

    // Referencia al último nodo (que apunta al head)
    public Node<T> tail;

    // Cantidad de nodos en la lista
    public int size;

    // ============================
    // Create Circular Linked List
    // ============================
    public Node<T> createLinkedList(T value){

        // Se crea un nuevo nodo
        Node<T> w = new Node<>();

        // Se asigna el valor al nodo
        w.value = value;

        // En una lista circular, el primer nodo se apunta a sí mismo
        w.next = w;

        // head y tail apuntan al mismo nodo
        head = w;
        tail = w;

        // El tamaño inicial es 1
        size = 1;

        // Se retorna el nodo creado
        return w;
    }

    // ============================
    // Search Method
    // ============================
    public boolean searchNode(T nodeValue) {

        // Verificamos que la lista exista
        if (head != null) {

            // Nodo temporal para recorrer la lista
            Node tempNode = head;

            // Recorremos exactamente "size" nodos
            // (en listas circulares NO usamos null como condición de parada)
            for(int i = 0; i < size; i++) {

                // Comparamos el valor del nodo actual
                if (tempNode.value.equals(nodeValue)) {
                    System.out.print("Found node at location: " + i);
                    return true;
                }

                // Avanzamos al siguiente nodo
                tempNode = tempNode.next;
            }
        }

        // Si no se encontró el nodo
        System.out.println("Node not found! ");
        return false;
    }

    // ============================
    // Insert in Circular Singly Linked List
    // ============================
    public void insertCSLL(T nodeValue, int location) {

        // Se crea el nuevo nodo a insertar
        Node node = new Node();
        node.value = nodeValue;

        // Caso 1: la lista no existe
        if (head == null) {
            createLinkedList(nodeValue);
            return;
        }

        // Caso 2: insertar al inicio (location == 0)
        else if (location == 0) {

            // El nuevo nodo apunta al antiguo head
            node.next = head;

            // El head se actualiza
            head = node;

            // El tail debe apuntar al nuevo head
            tail.next = head;
        }

        // Caso 3: insertar al final (location >= size)
        else if (location >= size) {

            // El antiguo tail apunta al nuevo nodo
            tail.next = node;

            // El nuevo nodo se convierte en tail
            tail = node;

            // El nuevo tail apunta al head (circularidad)
            tail.next = head;
        }

        // Caso 4: insertar en una posición intermedia
        else {

            // Nodo temporal para recorrer la lista
            Node tempNode = head;
            int index = 0;

            // Nos detenemos en el nodo anterior a la posición deseada
            while (index < location - 1) {
                tempNode = tempNode.next;
                index++;
            }

            // El nuevo nodo apunta al siguiente nodo
            node.next = tempNode.next;

            // El nodo anterior apunta al nuevo nodo
            tempNode.next = node;
        }

        // Aumentamos el tamaño de la lista
        size++;
    }

    // ============================
    // Traverse Circular Singly Linked List
    // ============================
    public void traverseCSLL() {

        // Verificamos que la lista exista
        if (head != null) {

            Node tempNode = head;

            // Recorremos exactamente "size" nodos
            for (int i = 0; i < size; i++) {

                System.out.print(tempNode.value);

                // Evita imprimir la flecha al final
                if (i != size - 1) {
                    System.out.print(" -> ");
                }

                tempNode = tempNode.next;
            }
            System.out.println("\n");

        } else {
            System.out.println("\nCSLL does not exist!");
        }
    }

    // ============================
    // Delete a Node
    // ============================
    public void deleteNode(int location) {

        // Caso 0: la lista no existe
        if (head == null) {
            System.out.println("The CSLL does not exist! ");
            return;
        }

        // Caso 1: eliminar el primer nodo
        else if (location == 0) {

            // El head avanza al siguiente nodo
            head = head.next;

            // El tail apunta al nuevo head
            tail.next = head;

            size--;

            // Si después de eliminar queda vacía
            if (size == 0) {
                tail = null;
                head.next = null;
                head = null;
            }
        }

        // Caso 2: eliminar el último nodo
        else if (location >= size) {

            Node tempNode = head;

            // Llegamos al nodo anterior al tail
            for (int i = 0; i < size - 1; i++) {
                tempNode = tempNode.next;
            }

            // Si solo hay un nodo
            if (tempNode == head) {
                head.next = null;
                tail = head = null;
                size--;
                return;
            }

            // El nuevo tail apunta al head
            tempNode.next = head;
            tail = tempNode;
            size--;
        }

        // Caso 3: eliminar un nodo intermedio
        else {

            Node tempNode = head;

            // Llegamos al nodo anterior al que se quiere eliminar
            for (int i = 0; i < location - 1; i++) {
                tempNode = tempNode.next;
            }

            // Saltamos el nodo a eliminar
            tempNode.next = tempNode.next.next;
            size--;
        }
    }

    // ============================
    // Delete Entire CSLL
    // ============================
    public void deleteCSLL() {

        if (head == null) {
            System.out.println("The CSLL does not exist!");
        } else {

            // Se rompe la circularidad
            head = null;
            tail.next = null;
            tail = null;

            System.out.println("The CSLL has been deleted!");
        }
    }
}
