package q1926;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int[] x = {-1,1,0,0};
	static int[] y = {0,0,-1,1};
	static int a;
	static int b;
	static int[][] map;
	static boolean[][] visited;
	static int count;
	static int tmp_square;
	static ArrayList<Integer> square = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] k = br.readLine().split(" ");
		a = Integer.parseInt(k[0]);
		b = Integer.parseInt(k[1]);
		
		map = new int[a][b];
		visited = new boolean[a][b];
		
		for(int i=0; i<a; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<b; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					tmp_square = 1;
					dfs(i,j);
					count++;
					square.add(tmp_square);
				}
			}
		}
		
		System.out.println(count);
		try {
			System.out.println(Collections.max(square));
		} catch (Exception e) {
			System.out.println(0);
		}
		
	}
	static void dfs(int k1, int k2) {
		visited[k1][k2] = true;
		
		for(int i=0; i<4; i++) {
			if(k1+x[i] >= 0 && k1+x[i] < a && k2+y[i] >= 0 && k2+y[i] < b &&
					map[k1+x[i]][k2+y[i]] == 1 && !visited[k1+x[i]][k2+y[i]]) {
				tmp_square++;
				dfs(k1+x[i],k2+y[i]);
			}
		}
	}

}
