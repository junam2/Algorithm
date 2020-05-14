package swea_q17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] person;
	static int[][] map;
	static boolean[] visited;
	static int n;
	static ArrayList<int[]> combList = new ArrayList<int[]>();
	static ArrayList<Integer> result = new ArrayList<Integer>();
	static int[] gary;
	static int sum = 0;
	static int wg1, wg2 = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		person = new int[n];
		map = new int[n][n];
		visited = new boolean[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<t; j++) {
				int tmp = Integer.parseInt(st.nextToken()) -1;
				map[i][tmp] = 1;
				map[tmp][i] = 1;
			}
		}
		
		int[] comb = new int[n];
		
		
		for(int i=0; i<n; i++) {
			comb[i] = i;
		}
		
		for(int i=1; i<=n/2; i++) {
			int[] combArr = new int[i];
			Combination(comb, n, i, 0, 0, combArr);
		}
		
		for(int i=0; i<combList.size(); i++) {
			visited = new boolean[n];
			gary = new int[n];
			
			
			for(int j=0; j<combList.get(i).length; j++) {
				gary[combList.get(i)[j]] = 1;
			}
			
			for(int j=0; j<n; j++) {
				if(gary[j]==0 && !visited[j] ) {
					sum = 0;
					dfs(j, 0);
					wg1 = sum;
					break;
				}
			}
			
			for(int j=0; j<n; j++) {
				if(gary[j]==1 && !visited[j]) {
					sum = 0;
					dfs(j,1);
					wg2 = sum;
					break;
				}
			}
			
			if(check(visited)) {	
				result.add(Math.abs(wg1- wg2));
			}
			
		}
		if(result.size()!=0) {
			System.out.println(Collections.min(result));
		} else {
			System.out.println(-1);
		}
		
		
	}
	
	public static void Combination(int[] comb, int n, int r, int index, int target, int[] combArr) {
		if(r==0) {
			int[] tmp = new int[combArr.length];
			
			for(int i=0; i<tmp.length; i++) {
				tmp[i] = combArr[i];
			}
			combList.add(tmp);			
		} else if(target == n) {
			return;
		} else {
			combArr[index] = target;
			Combination(comb, n, r-1, index+1, target+1, combArr);
			Combination(comb, n, r, index, target+1, combArr);
		}
	}
	
	public static void dfs(int a, int b) {
		visited[a] = true;
		sum += person[a];
		
		for(int i=0; i<n; i++) {
			if(gary[i]==b && map[a][i] == 1 && !visited[i]) {
				dfs(i, b);
			}
		}
	}
	
	public static boolean check(boolean[] visited) {
		for(int i=0; i<visited.length; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		
		return true;
	}

}
