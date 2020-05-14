package q17406;

import java.util.ArrayList;

public class test {
	static ArrayList<int[]> arr = new ArrayList<int[]>(); 
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		
		permutation(arr, 0, 4, 3);
		System.out.println();
		int[] tmp = new int[3];
		combination(tmp, 0, arr.length, 3, 0, arr);

	}
	
	public static void permutation(int[] arr, int depth, int n, int k) {
		if(depth == k) {
			print(arr, k);
			return;
		}
		
		for(int i=depth; i<n; i++) {
			swap(arr, i, depth);
			permutation(arr, depth+1, n, k);
			swap(arr, i, depth);
		}
	}
	
	public static void combination(int[] tmp, int depth, int n, int k, int target, int[] arr) {
		if(k==0) {
			int[] tmptmp = new int[tmp.length];
			
			for(int i=0; i<tmptmp.length; i++) {
				tmptmp[i] = arr[tmp[i]];
				System.out.print(tmptmp[i] + " ");
			}
			System.out.println();
		} else if(target == n) {
			return;
		} else {
			tmp[depth] = target;
			combination(tmp, depth+1, n, k-1, target+1, arr);
			combination(tmp, depth, n, k, target+1, arr);
		}
	}

	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void print(int[] arr, int k) { 
		for (int i = 0; i < k; i++) { 
			if (i == k - 1) 
				System.out.println(arr[i]); 
			else System.out.print(arr[i] + ","); 
		} 
	}

}
