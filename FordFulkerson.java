import java.util.*;

public class FordFulkerson {

    static final int V = 100; // max size

    // BFS to find path
    static boolean bfs(int[][] rGraph, int s, int t, int[] parent, int vertices) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[t];
    }

    static int fordFulkerson(int[][] graph, int s, int t, int vertices) {

        int[][] rGraph = new int[vertices][vertices];

        // Copy graph
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++)
                rGraph[i][j] = graph[i][j];

        int[] parent = new int[vertices];
        int maxFlow = 0;

        // While path exists
        while (bfs(rGraph, s, t, parent, vertices)) {

            int pathFlow = Integer.MAX_VALUE;

            // Find bottleneck
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Update residual graph
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int vertices = sc.nextInt();
        int edges = sc.nextInt();

        int[][] graph = new int[vertices][vertices];

        // Input edges
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cap = sc.nextInt();
            graph[u][v] = cap;
        }

        int s = sc.nextInt();
        int t = sc.nextInt();

        int maxFlow = fordFulkerson(graph, s, t, vertices);

        System.out.println(maxFlow);
    }
}