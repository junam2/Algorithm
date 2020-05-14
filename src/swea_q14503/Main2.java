package swea_q14503;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	static cleaner c;
	static int n,m;
	static int clean_spot = 0;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());
		c = new cleaner(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		c.arrow = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start(0);
		
		System.out.println(clean_spot);
	}
	
	static void start(int count) {
		
		if(count == 4) {
			int back_x = c.x + dx[(c.arrow+3)%4];
			int back_y = c.y + dy[(c.arrow+3)%4];
			
			if(back_x>= 0 && back_x<n && back_y>=0 && back_y<m) {
				if(map[back_x][back_y] == 1) {
					return;
				} else {
					c.x = back_x;
					c.y = back_y;
					start(0);
					return;
				}
			} else {
				return;
			}
		}
		
		int x = c.x;
		int y = c.y;
		
		if(map[c.x][c.y] == 0) {
			clean_spot++;
			map[c.x][c.y] = -1;
		}
		
		
		int left_x = dx[c.arrow];
		int left_y = dy[c.arrow];
		
		int tmp_x = x + left_x;
		int tmp_y = y + left_y;
		
		if(tmp_x>= 0 && tmp_x<n && tmp_y>=0 && tmp_y<m) {
			if(map[tmp_x][tmp_y] == 0) {
				c.x = tmp_x;
				c.y = tmp_y;
				c.arrow = (c.arrow + 3)%4;
				start(0);
			} else {
				c.arrow = (c.arrow + 3)%4;
				start(count+1);
			}
		} else {
			c.arrow = (c.arrow + 3)%4;
			start(count+1);
		}
 	}
}

class cleaner {
	int x;
	int y;
	int arrow;
	
	public cleaner(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
