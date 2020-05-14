package q17406;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int n,m,k;
	static int[][] real_map;
	static ArrayList<int[]> arr = new ArrayList<int[]>();
	static ArrayList<Integer> min_arr = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);
		
		real_map = new int[n][m];
		dot[] dots = new dot[k];
		
		for(int i=0; i<n; i++) {
			String[] str2 = br.readLine().split(" ");
 			for(int j=0; j<m; j++) {
				real_map[i][j] = Integer.parseInt(str2[j]);
			}
		}
		
		for(int i=0; i<k; i++) {
			str = br.readLine().split(" ");
			dots[i] = new dot(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		}
		
		int[] tmp_perm = new int[k];
		
		for(int i=0; i<k; i++) {
			tmp_perm[i] = i;
		}
		
		int[][] map_copy = new int[n][m];
		
		permitation(tmp_perm, 0, tmp_perm.length, tmp_perm.length);
		
		for(int i=0; i<arr.size(); i++) {
			int[] tmp_arr = arr.get(i);
			map_copy = deepcopy();
			for(int j=0; j<tmp_arr.length; j++) {
				allRotate(map_copy, dots[tmp_arr[j]]);
			}
			
			min_arr.add(findMin(map_copy));
		}
		
		System.out.println(Collections.min(min_arr));
		
	}
	
	public static int[][] deepcopy() {
		int[][] clone = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				clone[i][j] = real_map[i][j];
			}
		}
		
		return clone;
	}
	
	public static void allRotate(int[][] map, dot d) {
		int a = d.r - d.s - 1;
		int b = d.c - d.s - 1;
		int a1 = d.r + d.s -1;
		int b1 = d.c + d.s -1;
		
		int i = 0;
		while(a != a1) {
			subRotate(map, a, b, a1, b1);
			a++; b++; a1--; b1--;
		}
	}
	
	public static void subRotate(int[][] map, int a, int b, int a1, int b1) {
		int tmp = map[a][b];
		
		for(int i=a; i<a1; i++) {
			map[i][b] = map[i+1][b];
		}
		
		for(int i=b; i<b1; i++) {
			map[a1][i] = map[a1][i+1];
		}
		
		for(int i=a1; i>a; i--) {
			map[i][b1] = map[i-1][b1];
		}
		
		for(int i=b1; i>b+1; i--) {
			map[a][i] = map[a][i-1];
		}
		
		map[a][b+1] = tmp;
	}
	
	public static void permitation(int[] tmp, int depth, int n, int k) {
		if(depth == k) {
			int[] tmp_tmp = new int[k];
			
			for(int i=0; i<k; i++) {
				tmp_tmp[i] = tmp[i];
			}
			
			arr.add(tmp_tmp);
			return;
		}
		
		for(int i=depth; i<n; i++) {
			swap(tmp, i, depth);
			permitation(tmp, depth+1, n, k);
			swap(tmp, i, depth);
		}

	}
	
	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static int findMin(int[][] map) {
		int min = Integer.MAX_VALUE;
		int tmp_min = 0;
		
		for(int i=0; i<map.length; i++) {
			tmp_min = 0;
			for(int j=0; j<map[0].length; j++) {
				tmp_min += map[i][j];
			}
			
			if(tmp_min < min) {
				min = tmp_min;
			}
		}
		
		return min;
	}
}

class dot {
	int r;
	int c;
	int s;
	
	public dot(int r,int c,int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
