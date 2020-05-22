package q14890;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,l;
	static int[][] map;
	static boolean[][] visited;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//가로
		for(int i=0; i<n; i++) {
			int[] row = map[i];
			
			construct(row);
		}
		
		//세로 (visited 초기화 필요)
		visited = new boolean[n][n];
	}
	
	static void construct(int[] arr) {
		boolean[] bridge = new boolean[arr.length];
		
		for(int i=1; i<arr.length; i++) {
			if(Math.abs(arr[i] - arr[i-1]) == 1) {
				if(isPossible(arr)) {
					
				} else {
					return;
				}
			}
		}
		
		result++;
	}
	
	static boolean isPossible(int[] arr) {
		return true;
	}
}
