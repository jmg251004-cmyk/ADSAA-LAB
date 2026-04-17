import java.util.*;
public class Prims{
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of vertices: ");
		int n = sc.nextInt();
		System.out.println("Enter no of edges: ");
		int m = sc.nextInt();
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for(int i = 0;i<n;i++){
			graph.add(new ArrayList<>());
		}
		System.out.println("Enter the edges(u v w): ");
		for(int i = 0;i<m;i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			graph.get(u).add(new int[]{v,w});
			graph.get(v).add(new int[]{u,w});
		}
		System.out.println("Enter source vertex: ");
		int s = sc.nextInt();
		int[] key = new int[n];
		boolean[] inMST = new boolean[n];
		int[] parent = new int[n];
		Arrays.fill(key,INF);
		Arrays.fill(parent,-1);
		key[s] = 0;
		int totalweight = 0;
		//Prims
		for(int i = 0;i< n;i++){
			int u = -1;
			int min = INF;
			for(int j =0;j<n;j++){
				if(!inMST[j] && key[j] < min){
					min = key[j];
					u = j;
				}
			}
			if(u == -1){
				break;
			}
			inMST[u] = true;
			totalweight += key[u];
			for(int[] edge:graph.get(u)){
				int v = edge[0];
				int w = edge[1];
				if(!inMST[v] && w <key[v]){
					key[v] = w;
					parent[v] = u;
					}
			}
		}
					
		System.out.println("Total weight: "+totalweight);
		System.out.println("Edges included in MST : ");
		for(int i = 0;i<n;i++){
			if(parent[i] != -1){
			System.out.println(parent[i] + "--" + i + "(Weight: " +key[i] + ")");
		}
	}
}
}




	
			
