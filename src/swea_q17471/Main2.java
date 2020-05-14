package swea_q17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int n;
	static int[] person;
	static int[][] map;
	static int[] comb;
	static int[] section;
	static boolean[] visited;
	static int total = 0;
	static int sec1_total = 0;
	static int sec2_total = 0;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		person = new int[n];
		map = new int[n][n];
		comb = new int[n];
		
		String[] str = br.readLine().split(" ");
		for(int i=0; i<n; i++) {
			person[i] = Integer.parseInt(str[i]);
			total += Integer.parseInt(str[i]);
		}
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<number; j++) {
				map[i][Integer.parseInt(st.nextToken())-1] = 1; 
			}
		}
			
		for(int t=1; t<=n/2; t++) {
			Arrays.fill(comb, 1);
			
			for(int j=0; j<t; j++) {
				comb[j] = 0;
			}
			
			do {
				section = new int[n];
				visited = new boolean[n];
				sec1_total = 0;
				sec2_total = 0;
				
				for(int i=0; i<n; i++) {
					if(comb[i] == 0) {
						section[i] = 1;
					}
				}
				
				for(int i=0; i<section.length; i++) {
					if(!visited[i] && section[i] == 1) {
						dfs_1(i);
						break;
					}
				}
				
				for(int i=0; i<section.length; i++) {
					if(!visited[i] && section[i] == 0) {
						dfs_2(i);
						break;
					}
				}
				
				if(sec1_total + sec2_total == total) {
					int sub = Math.abs(sec1_total - sec2_total);
					
					if(min > sub) {
						min = sub;
					}
				}
				
			} while(next_permutation(comb));
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}
	
	public static void dfs_1(int a) {
		visited[a] = true;
		sec1_total += person[a];
		
		for(int i=0; i<n; i++) {
			if(map[a][i] == 1 && section[i] == 1 &&!visited[i]) {
				dfs_1(i);
			}
		}
	}
	
	public static void dfs_2(int a) {
		visited[a] = true;
		sec2_total += person[a];
		
		for(int i=0; i<n; i++) {
			if(map[a][i] == 1 && section[i]==0  &&!visited[i]) {
				dfs_2(i);
			}
		}
	}
	
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1]>=arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		int j = arr.length-1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length-1;
		while(i < j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		return true;
		
	}
}
