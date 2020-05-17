package q15656;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		LinkedList<Integer> permu = new LinkedList<Integer>();
		rePermutation(n,r,permu);
		
		System.out.println(sb.toString());
	}
	
	static void rePermutation(int n,int r, LinkedList<Integer> permu) {
		if(permu.size() == r) {
			for(int i=0; i<r; i++) {
				sb.append(permu.get(i) + " ");
			}
			
			sb.append("\n");
 			return;
		}
		
		for(int i=0; i<n; i++) {
			permu.add(arr[i]);
			rePermutation(n,r,permu);
			permu.removeLast();
		}
	}

}
