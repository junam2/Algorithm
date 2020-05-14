package q7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main2 {
	static int m;
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int day = 0;
	static Queue<edge> q = new LinkedList<edge>();

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		arr = new int[m][n];
		visited = new boolean[m][n];
		
		for(int i=0; i<m; i++) {
			String[] str2 = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(str2[j]);
			}
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					q.add(new edge(i,j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			edge tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for(int i=0; i<4; i++) {
				int x2 = x + dx[i];
				int y2 = y + dy[i];
				
				if(x2>=0 && x2<m && y2>=0 && y2<n && arr[x2][y2] == 0 && visited[x2][y2] == false) {
					visited[x2][y2] = true;
					arr[x2][y2] = arr[x][y] + 1;
					q.add(new edge(x2, y2));
					print();
				}
			}
		}
		
		if(check()) {
			System.out.println(findMaxValue()-1);
		} else {
			System.out.println(-1);
		}
 	}
	
	static boolean check() {
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static int findMaxValue() {
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(max < arr[i][j]) {
					max = arr[i][j];
				}
			}
		}
		
		return max;
	}
	
	static void print() {
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}

class edge {
	int x;
	int y;
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
