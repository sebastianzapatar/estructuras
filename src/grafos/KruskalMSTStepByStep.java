package grafos;

import java.util.*;

public class KruskalMSTStepByStep {

    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
        @Override
        public String toString() {
            return u + "-" + v + " (" + w + ")";
        }
    }

    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]); // compresión de camino
            return parent[x];
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false; // ya conectados => ciclo

            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[ra] > rank[rb]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
            return true;
        }
    }

    private int V;
    private List<Edge> edges;

    public KruskalMSTStepByStep(int V) {
        this.V = V;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w)); // en MST se asume no dirigido
    }

    public List<Edge> kruskalWithTrace() {
        // 1) ordenar aristas por peso ascendente
        edges.sort(Comparator.comparingInt(e -> e.w));

        DSU dsu = new DSU(V);
        List<Edge> mst = new ArrayList<>();
        int total = 0;

        System.out.println("=== KRUSKAL (PASO A PASO) ===");
        System.out.println("Aristas ordenadas: " + edges);
        System.out.println();

        // 2) recorrer aristas en orden y decidir aceptar / rechazar
        for (Edge e : edges) {
            int ru = dsu.find(e.u);
            int rv = dsu.find(e.v);

            System.out.println("Evaluando arista " + e + "...");
            System.out.println("  - Raíz(" + e.u + ") = " + ru + ", Raíz(" + e.v + ") = " + rv);

            if (ru != rv) {
                // aceptar
                dsu.union(e.u, e.v);
                mst.add(e);
                total += e.w;

                System.out.println("  ✅ Se ACEPTA: conecta dos componentes distintas (no forma ciclo).");
                System.out.println("  MST ahora: " + mst);
                System.out.println("  Costo acumulado: " + total);
            } else {
                // rechazar
                System.out.println("  ❌ Se RECHAZA: u y v ya están conectados (formaría un ciclo).");
            }

            System.out.println("  #aristas en MST = " + mst.size() + " / " + (V - 1));
            System.out.println("----------------------------------------");

            if (mst.size() == V - 1) {
                System.out.println("✅ MST completo (tiene V-1 aristas). Se detiene.");
                break;
            }
        }

        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("MST: " + mst);
        System.out.println("Costo total: " + total);

        return mst;
    }

    public static void main(String[] args) {
        KruskalMSTStepByStep g = new KruskalMSTStepByStep(5);

        // Ejemplo (puedes cambiarlo por el de tu diapositiva)
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.kruskalWithTrace();
    }
}
