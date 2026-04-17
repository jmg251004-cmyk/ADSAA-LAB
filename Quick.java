import java.util.*;
public class Quick{
	public static void quickSort(int[] arr,int low,int high){
		if(low<high){
		int pi = partition(arr,low,high);
		quickSort(arr,low,pi-1);
		quickSort(arr,pi+1,high);
		}
	}
	public static int partition(int[] arr,int low,int high){
		int pi = arr[high];
		int i = low - 1;
		for(int j = low;j<high;j++){
			if(arr[j]<pi){
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return i+1;
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of elements: ");
		if(!sc.hasNextInt()){
			return;
		}
		int n = sc.nextInt();
		int[] arr1 = new int[n];
		System.out.println("Enter" + n + "elements: ");
		for(int i = 0;i<n;i++){
			arr1[i] = sc.nextInt();
		}
		long startMerge = System.nanoTime();
        quickSort(arr1, 0, n - 1);
        long endMerge = System.nanoTime();

        System.out.println("\nSorted array:");
        for (int x : arr1) {
            System.out.print(x + " "); // Changed to print on one line
        }

        System.out.printf("\n\nTime taken: %.4f ms\n", (endMerge - startMerge) / 1e6);
        sc.close();
    }
}