package q15665;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main {
	static int n,r;
	static StringBuilder sb = new StringBuilder();
	static Set<String> set;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		r = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		arr = new int[n];
		set = new LinkedHashSet<>();
		LinkedList<Integer> res = new LinkedList<Integer>();
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		Arrays.sort(arr);
		
		rePermu(0,0,res);
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			sb.append(it.next()).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static void rePermu(int a, int b, LinkedList<Integer> res) {
		if(b == r) {
			StringBuilder sb2 = new StringBuilder();
			
			for(int i=0; i<b; i++) {
				sb2.append(res.get(i) + " ");
			}
			
			set.add(sb2.toString());
			
			return;		
		}
		
		for(int i=0; i<n; i++) {
			res.add(arr[i]);
			rePermu(a,b+1,res);
			res.removeLast();
		}
	}
}
