package q2309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
	static boolean flag = false;
	static int[] heights;
	static boolean[] visited;
	static int[] result = new int[7];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		heights = new int[9];
		visited = new boolean[9];
		
		for(int i=0; i<9; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		
		backTracking(0,0);
		
		Arrays.sort(result);
		
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i]);
			
		}
	}
	
	public static void backTracking(int cnt, int sum ) {
		if(flag) {
			return;
		}
		
		if(cnt == 7) {
			if(sum == 100) {
				int idx = 0;
				for(int i=0; i<heights.length; i++) {
					if(visited[i]) {
						result[idx++] = heights[i];
						flag = true;
					}
				}
			}
			return;
		} 
		
		for(int i=0; i<heights.length; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			backTracking(cnt + 1, sum + heights[i]);
			visited[i] = false;
		}
	}

}
