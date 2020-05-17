package q15657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int[] comb = new int[n];
		
		reCombination(n,r,comb,0,0);
		
		System.out.println(sb.toString());
	}
	
	static void reCombination(int n,int r,int[] comb, int index, int target) {
		if(r == 0) {
			for(int i=0; i<index; i++) {
				sb.append(comb[i] + " ");
			}
			
			sb.append("\n");
			return;
		}
		if(n==target) return;
		
		comb[index] = arr[target];
		reCombination(n,r-1,comb,index+1,target);
		reCombination(n,r,comb,index,target+1);
	}

}
