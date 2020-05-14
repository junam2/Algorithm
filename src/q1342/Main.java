package q1342;

import java.util.*;
import java.util.Scanner;

public class Main {
	static Set<String> set = new HashSet<String>();
	static String[] arr;
	static int count = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		arr = sc.next().split("");
		int[] tmp = new int[arr.length];
		for(int i=0; i<tmp.length; i++) {
			tmp[i] = i;
		}
		
		permutation(tmp, 0, arr.length, arr.length);
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			if(check(it.next())) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	static void permutation(int[] tmp, int depth, int n, int r) {
		if(depth==r) {
			String str = "";
			for(int i=0; i<tmp.length; i++) {
				str += arr[tmp[i]];
			}
			
			set.add(str);
			return;
		}
		
		for(int i=depth; i<n; i++) {
			swap(tmp, i, depth);
			permutation(tmp, depth+1, n, r);
			swap(tmp, i, depth);
		}
		
	}
	
	static void swap(int[] tmp, int i ,int j) {
		int t = tmp[i];
		tmp[i] = tmp[j];
		tmp[j] = t;
	}
	
	static boolean check(String str) {
		boolean flag = true;
		
		for(int i=0; i<str.length()-1; i++) {
			if(str.charAt(i) == str.charAt(i+1)) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}

}
