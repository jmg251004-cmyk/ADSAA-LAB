import java.util.*;
public class DijkstraAlg{
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		//No of edges and vertices
		System.out.println("Enter no of vertices: ");
		int n = sc.nextInt();
		System.out.println("Enter no of edges: ");
		int m = sc.nextInt();
		//Adjacency list
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for(int i = 0;i<n;i++){
			graph.add(new ArrayList<>());
		}
		//Input edges
		System.out.print("Enter edges(u v w): ");
		for(int i = 0;i<m;i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
		    graph.get(u).add(new int[]{v,w});
		}
		//Source vertex
		System.out.println("Enter source vertex: ");
		int s = sc.nextInt();
		int[] dist = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(dist,INF);
		dist[s] = 0;
		for(int i = 0;i<n;i++){
			int u = -1;
			int min = INF;
			for(int j = 0;j<n;j++){
				if(!visited[j] && dist[j]<min){
					min = dist[j];
					u = j;
				}
			}
			if(u == -1){
				break;
			}
			visited[u] = true;
			for(int[] edge : graph.get(u)){
				int v = edge[0];
				int w = edge[1];
				if(!visited[v] && dist[u] != INF && dist[u] + w < dist[v]){
					dist[v] = dist[u] + w;
				}
			}
		}
		System.out.println("\nShortest dist from source: "+s+ ":");
		for(int i =0;i<n;i++){
			if(dist[i] == INF){
				System.out.println("Vertex " + i + ": INF");
			}
			else{
				System.out.println("Vertex " + i + " : " +dist[i]);
			}
		}
	}
}
		
		