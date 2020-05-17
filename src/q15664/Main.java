package q15664;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static Set<String> set;
	static int[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		int n = Integer.parseInt(str[0]);
		int r = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		arr = new int[n];
		set = new LinkedHashSet<>();
		int[] res = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		Arrays.sort(arr);
		
		comb(res,n,r,0,0);
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			sb.append(it.next()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void comb(int[] res, int n,int r, int index, int target) {
		if(r == 0) {
			StringBuilder sb2 = new StringBuilder();
			
			for(int i=0; i<index; i++) {
				sb2.append(res[i] + " ");
			}
			
			set.add(sb2.toString());
			
			return;
		}
		if(n==target) return;
		
		res[index] = arr[target];
		comb(res,n,r-1,index+1,target+1);
		comb(res,n,r,index,target+1);
	}

}
