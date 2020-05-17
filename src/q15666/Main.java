package q15666;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
	static int n,r;
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set;
	static int[] arr;
	static int[] res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		r = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		set = new LinkedHashSet<Integer>();
				
		for(int i=0; i<n; i++) {
			set.add(Integer.parseInt(str[i]));
		}
		Iterator<Integer> it = set.iterator();
		int index = 0;
		arr = new int[set.size()];
		res = new int[n];
		while(it.hasNext()) {
			arr[index++] = it.next();
		}
		
		Arrays.sort(arr);
		
		comb(0,0);
		
		System.out.println(sb.toString());
	}
	
	static void comb(int index, int target) {
		if(index == r) {
			for(int i=0; i<r; i++) {
				sb.append(res[i] + " ");
			}
			
			sb.append("\n");
			return;
		} 
		
		if(target == set.size()) {
			return;
		}
		
		res[index] = arr[target];
		comb(index+1,target);
		comb(index,target+1);
		
	}

}
