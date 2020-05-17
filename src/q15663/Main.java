package q15663;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static LinkedHashSet<String> set = new LinkedHashSet<String>();
	static int[] arr;
	static String lastString = "";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		int[] check = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);		
		LinkedList<Integer> permu = new LinkedList<Integer>();
		permutation(n,r,permu,check);
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void permutation(int n,int r,LinkedList<Integer> permu, int[] check) {
		if(permu.size() == r) {
			StringBuilder tmp_sb = new StringBuilder();
			
			for(int i=0; i<r; i++) {
				tmp_sb.append(permu.get(i) + " ");
			}
			
			set.add(tmp_sb.toString());

			return;
		}
		
		for(int i=0; i<n; i++) {
			if(check[i] == 0) {
				permu.add(arr[i]);
				check[i] = 1;
				permutation(n,r,permu,check);
				check[i] = 0;
				permu.removeLast();
			}
		}
	}

}
