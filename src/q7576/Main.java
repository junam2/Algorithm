package q7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int dx[] = {-1,1,0,0};
	static int dy[] = {0,0,-1,1};
	static int h,w;
	static int[][] map;
	static boolean[][] visited;
	static int day = 0;
	static Queue<dot> q = new LinkedList<dot>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		h = Integer.parseInt(str[1]);
		w = Integer.parseInt(str[0]);
		
		map = new int[h][w];
		visited = new boolean[h][w];
		
		for(int i=0; i<h; i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					q.add(new dot(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			dot d = q.poll();
			
			for(int i=0; i<dx.length; i++) {
				int x2 = d.x + dx[i];
				int y2 = d.y + dy[i];
				
				if(x2>=0 && x2<h && y2>=0 && y2<w && 
						map[x2][y2]==0 && !visited[x2][y2]) {
					q.add(new dot(x2,y2));
					visited[x2][y2] = true;
					map[x2][y2] = map[d.x][d.y] + 1;
				}
			}
		}
		
		day = maxdxdy(map)-1;
		if(iscomplete(map)) {
			System.out.println(day);
		} else {
			System.out.println(-1);
		}
	}
		
	static int maxdxdy(int[][] arr) {
		int max = -1;
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}
		
		return max;
	}
	
	static boolean iscomplete(int[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				if(arr[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}

}

class dot {
	int x;
	int y;
	
	public dot(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
