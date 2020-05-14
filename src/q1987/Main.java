package q1987;
//3260ms..

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int n,m;
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Character> arr = new ArrayList<Character>();
	static ArrayList<Integer> result = new ArrayList<Integer>();
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int count = 0;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new char[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			char[] tmp = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				map[i][j] = tmp[j];
			}
		}
		
		arr.add(map[0][0]);
		dfs(0,0);
		
		System.out.println(Collections.max(result));
	}
	
	public static void dfs(int a,int b) {
		count = 0;
		visited[a][b] = true;
		
		
		for(int i=0; i<4; i++) {
			int x1 = a + dx[i];
			int y1 = b + dy[i];
			
			if(x1>=0 && x1<n && y1>=0 && y1<m && !visited[x1][y1] && isChar(map[x1][y1])) {
				arr.add(map[x1][y1]);
				dfs(x1, y1);
			} else {
				count++;
			}
		}
		
		if(count == 4) {
			result.add(arr.size());
		}
		
		arr.remove(arr.size()-1);
		visited[a][b] = false;
		count = 0;
		return;
	}
	
	public static boolean isChar(char c) {
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i) == c) {
				return false;
			}
		}
		
		return true;
	}

}
