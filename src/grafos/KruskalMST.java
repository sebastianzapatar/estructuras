package grafos;
import java.util.*;                      // Importa utilidades: listas, sort, etc.

public class KruskalMST {                // Clase principal del algoritmo

    // Representa una arista no dirigida con peso
    static class Edge {                  // Clase interna para modelar una arista
        int u, v, w;                     // u y v son los nodos; w es el peso

        Edge(int u, int v, int w) {      // Constructor: recibe extremos y peso
            this.u = u;                  // Guarda el nodo u en el atributo u
            this.v = v;                  // Guarda el nodo v en el atributo v
            this.w = w;                  // Guarda el peso w en el atributo w
        }
    }

    private int V;                       // Número de vértices del grafo
    private List<Edge> edges;            // Lista de todas las aristas del grafo

    public KruskalMST(int V) {           // Constructor que recibe número de nodos
        this.V = V;                      // Asigna V
        this.edges = new ArrayList<>();  // Crea la lista de aristas vacía
    }

    public void addEdge(int u, int v, int w) { // Agrega una arista al grafo
        edges.add(new Edge(u, v, w));          // Crea Edge y la mete a la lista
    }

    // ---- UNION-FIND (Disjoint Set Union / DSU) ----
    static class DSU {                   // Estructura para saber si hay ciclos
        int[] parent;                    // parent[x] = representante del conjunto de x
        int[] rank;                      // rank ayuda a unir árboles de forma eficiente

        DSU(int n) {                     // Crea DSU con n elementos (0..n-1)
            parent = new int[n];         // Reserva arreglo parent de tamaño n
            rank = new int[n];           // Reserva arreglo rank de tamaño n
            for (int i = 0; i < n; i++) {// Recorre todos los nodos
                parent[i] = i;           // Cada nodo inicia siendo su propio padre
                rank[i] = 0;             // Rank inicia en 0
            }
        }

        int find(int x) {                // Encuentra el representante del conjunto de x
            if (parent[x] != x) {        // Si x NO es su propio padre
                parent[x] = find(parent[x]); // Compresión de camino: apunta directo a raíz
            }
            return parent[x];            // Devuelve el representante (raíz)
        }

        boolean union(int a, int b) {    // Une los conjuntos de a y b si son distintos
            int ra = find(a);            // ra = raíz de a
            int rb = find(b);            // rb = raíz de b
            if (ra == rb) return false;  // Si ya están en el mismo conjunto, unir crea ciclo

            // Unión por rank: pega el árbol más bajo al más alto
            if (rank[ra] < rank[rb]) {   // Si el rank de ra es menor
                parent[ra] = rb;         // cuelga ra debajo de rb
            } else if (rank[ra] > rank[rb]) { // Si el rank de ra es mayor
                parent[rb] = ra;         // cuelga rb debajo de ra
            } else {                     // Si son iguales
                parent[rb] = ra;         // cuelga rb debajo de ra
                rank[ra]++;              // y aumenta rank de ra
            }
            return true;                 // Se unieron con éxito (no había ciclo)
        }
    }

    // ---- KRUSKAL: devuelve las aristas del MST ----
    public List<Edge> kruskal() {        // Método que construye el MST
        edges.sort(Comparator.comparingInt(e -> e.w)); // Ordena aristas por peso ascendente

        DSU dsu = new DSU(V);            // Crea DSU para detectar ciclos
        List<Edge> mst = new ArrayList<>(); // Lista donde guardaremos las aristas del MST

        for (Edge e : edges) {           // Recorre aristas desde la más barata
            if (dsu.union(e.u, e.v)) {   // Intenta unir u y v (solo si no forma ciclo)
                mst.add(e);              // Si no formó ciclo, agrega arista al MST
                if (mst.size() == V - 1) // Un MST tiene exactamente V-1 aristas
                    break;               // Si ya tenemos V-1, podemos parar
            }
        }

        return mst;                      // Retorna la lista de aristas del MST
    }

    // Método helper para imprimir resultado
    public void printMST(List<Edge> mst) {   // Imprime las aristas y el costo total
        int total = 0;                       // Acumulador del peso total
        System.out.println("Aristas del MST (Kruskal):"); // Encabezado
        for (Edge e : mst) {                 // Recorre las aristas del MST
            System.out.println(e.u + " - " + e.v + " (peso " + e.w + ")"); // Imprime arista
            total += e.w;                    // Suma el peso
        }
        System.out.println("Costo total = " + total); // Imprime costo total
    }

    // ---- MAIN de prueba ----
    public static void main(String[] args) { // Punto de entrada
        KruskalMST g = new KruskalMST(5);    // Crea un grafo con 5 nodos: 0..4

        g.addEdge(0, 1, 2);                  // Arista 0-1 con peso 2
        g.addEdge(0, 3, 6);                  // Arista 0-3 con peso 6
        g.addEdge(1, 2, 3);                  // Arista 1-2 con peso 3
        g.addEdge(1, 3, 8);                  // Arista 1-3 con peso 8
        g.addEdge(1, 4, 5);                  // Arista 1-4 con peso 5
        g.addEdge(2, 4, 7);                  // Arista 2-4 con peso 7
        g.addEdge(3, 4, 9);                  // Arista 3-4 con peso 9

        List<Edge> mst = g.kruskal();        // Ejecuta Kruskal y obtiene MST
        g.printMST(mst);                     // Imprime el MST y su costo
    }
}
