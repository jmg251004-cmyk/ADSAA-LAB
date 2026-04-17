import java.util.*;
public class FW{
	static final int INF = 999999;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of vertices: ");
		int V = sc.nextInt();
		System.out.println("Enter no of edges: ");
		int E = sc.nextInt();
		int[][] dist = new dist[V][V];
		for(int i = 0;i<V;i++){
			for(int j = 0;j<V;j++){
				if(i == j){
					dist[i][j] = 0;
				}
				else{
					dist[i][j] = INF;
				}
			}
		}
		System.out.println("Enter edges( u v w ): ");
		for(int e = 0;e<E;e++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			dist[u - 1][v - 1] = w;
		}
		for(int k = 0;k<V;k++){
			for(int i = 0;i<V; i++){
				for(int j = 0;j<V;j++){
					if(dist[i][k] != INF && dist[k][j] != INF && dist[i][j] > dist[i][k] + dist[j][k]){
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		System.out.println("Final matrix(shortest path among all pairs): ");
		printMatrix(dist,V);
		boolean negCycle = false;
		for(int i = 0;i<V;i++){
			if(dist[i][i] < 0){
				negCycle = true;
				break;
			}
		}
		if(negCycle){
			System.out.println("Negative cycle detected");
		}
		else{
			System.out.println("No negative cycle detected");
		}
		sc.close();
	}
		
		static void printMatrix(int[][]matrix, int V){
			for(int i = 0;i<V;i++){
				for(int j = 0;j<V;j++){
					if(dist[i][j] = INF){
						System.out.println("INF");
					}
					else{
						System.out.println(matrix[i][j] + "");
					}
				}
				System.out.println();
			}
		}
	}
	
					
			