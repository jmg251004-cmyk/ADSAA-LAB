import java.util.*;
public class Bell{
	static class Edge{
		int u,v,w;
		Edge(int u , int v, int w){
			this.u = u;
			this.v = v;
			this.w = w;
			}
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of vertices: ");
		int n = sc.nextInt();
		System.out.println("Enter no of edges: ");
		int m = sc.nextInt();
		ArrayList<Edge> edges = new ArrayList<>();
		System.out.println("Enter edges( u v w ): ");
		for(int i = 0;i<m;i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			edges.add(new Edge(u ,v, w));
		}
		System.out.println("Enter src vertex: ");
		int s = sc.nextInt();
		int[]dist = new int[n];
		Arrays.fill(dist,Integer.MAX_VALUE);
		dist[s] = 0;
		//Relax n-1 times
		for(int i = 1;i<n;i++){
			for(Edge e : edges){
				if(dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]){
					dist[e.v] = dist[e.u] + e.w;
				}
			}
		}
		//negative cycle
		boolean negCycle = false;
		for(Edge e : edges){
			if(dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]){
				negCycle = true;
				break;
			}
		}
		if(negCycle){
			System.out.println("Negative cycle detected");
			return;
		}
		
		//Result
		System.out.println("Vertex dist from source: ");
		for(int i = 0;i<n;i++){
			if(dist[i] == Integer.MAX_VALUE){
				System.out.println(i + ":  INF");
			}
			else{
				System.out.println(i + " : " + dist[i]);
			}
		}
	}
}
				
				
		