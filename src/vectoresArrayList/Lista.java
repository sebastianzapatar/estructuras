public class SingleDimensionArray<T> {

    private T[] arr;

    @SuppressWarnings("unchecked")
    public SingleDimensionArray(int sizeOfArray) {
        arr = (T[]) new Object[sizeOfArray];
        // Por defecto, Object[] queda lleno de null → posiciones vacías
    }

    // Insertar en una posición específica
    public void insert(int location, T valueToBeInserted) {
        try {
            if (arr[location] == null) {
                arr[location] = valueToBeInserted;
                System.out.println("Successfully inserted");
            } else {
                System.out.println("This cell is already occupied");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid index to access array!");
        }
    }

    // Recorrer el array
    public void traverseArray() {
        if (arr == null) {
            System.out.println("Array no longer exists!");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Buscar un elemento
    public void searchInArray(T valueToSearch) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(valueToSearch)) {
                System.out.println("Value is found at the index of " + i);
                return;
            }
        }
        System.out.println(valueToSearch + " is not found");
    }

    // Eliminar por índice
    public void deleteValue(int index) {
        try {
            arr[index] = null;
            System.out.println("The value has been deleted successfully");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The index is out of array range");
        }
    }

    // Insertar al final (creciendo el array)
    @SuppressWarnings("unchecked")
    public void insertAtEnd(T valueToBeInserted) {
        T[] newArr = (T[]) new Object[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        newArr[newArr.length - 1] = valueToBeInserted;
        arr = newArr;

        System.out.println("Successfully inserted at the end");
    }

    // Eliminar el último elemento
    @SuppressWarnings("unchecked")
    public void deleteLastElement() {
        if (arr.length == 0) {
            System.out.println("Array is already empty");
            return;
        }

        T[] newArr = (T[]) new Object[arr.length - 1];

        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
        System.out.println("Last element deleted successfully");
    }

    // Main de prueba
    public static void main(String[] args) {

        SingleDimensionArray<Integer> sda = new SingleDimensionArray<>(3);

        sda.insert(0, 10);
        sda.insert(1, 20);
        sda.insert(2, 30);

        sda.insertAtEnd(40);

        sda.traverseArray();

        sda.deleteLastElement();
        sda.traverseArray();

        sda.searchInArray(20);
    }
}
