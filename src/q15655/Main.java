package q15655;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int[] comb = new int[n];
		
		combination(n,r,comb,0,0);
		
		System.out.println(sb.toString());
	}
	
	static void combination(int n,int r,int[] comb,int index,int target) {
		if(r==0) {
			for(int i=0; i<index; i++) {
				sb.append(comb[i] + " ");
			}
			sb.append("\n");
			return;
		}
		if(target == n) return;
		
		comb[index] = arr[target];
		combination(n,r-1,comb,index+1, target+1);
		combination(n,r,comb,index,target+1);
	}
	
}
