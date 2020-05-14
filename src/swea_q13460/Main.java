package swea_q13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static char[][] map;
	static dot red, blue, end;
	static boolean[][] visited;
	static int result;
	static boolean end_flag = false;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new char[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			char[] c = br.readLine().toCharArray();
			for(int j=0; j<m; j++) {
				map[i][j] = c[j];
				
				if(map[i][j] == 'R') {
					red = new dot(i,j);
				} else if(map[i][j] == 'B') {
					blue = new dot(i,j);
				} else if(map[i][j] == 'O') {
					end = new dot(i,j);
				}
			}
		}

		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}
	
	public static void dfs(int a) {
		boolean red_flag = true;
		boolean blue_flag = true;
		
		if(a>10) {
			result = -1;
			return;
		}
		//print();

		if(blue.x == end.x && blue.y == end.y) {
			return;
		}
		
		//¼º°ø
		if(red.x == end.x && red.y == end.y) {
			if(min > a) {
				min = a;
			}
			return;
		}
		
		for(int i=0; i<4; i++) {
			int origin_red_x = red.x;
			int origin_red_y = red.y;
			int origin_blue_x = blue.x;
			int origin_blue_y = blue.y;
			
			while(true) {
				if(red.x>=0 && red.x<n && red.y>=0 && red.y<m) {
					if(map[red.x+dx[i]][red.y+dy[i]] == '#' || map[red.x+dx[i]][red.y+dy[i]] == 'B') {
						red_flag = false;
					}  else if( map[red.x+dx[i]][red.y+dy[i]] == 'O'){
						red.x += dx[i];
						red.y += dy[i];
						red_flag = false;
					} else {
						red.x += dx[i];
						red.y += dy[i];
					}
				}
				
				if(blue.x>0 && blue.x<n-1 && blue.y>0 && blue.y<m-1) {
					if(map[blue.x+dx[i]][blue.y+dy[i]] == '#' || map[blue.x+dx[i]][blue.y+dy[i]] == 'R') {
						blue_flag = false;
					}  else if( map[blue.x+dx[i]][blue.y+dy[i]] == 'O'){
						blue.x += dx[i];
						blue.y += dy[i];
						blue_flag = false;
					} else {
						blue.x += dx[i];
						blue.y += dy[i];
					}
				}
				
				if(!red_flag && !blue_flag) {
					map[origin_red_x][origin_red_y] = '.';
					map[red.x][red.y] = 'R';
					map[origin_blue_x][origin_blue_y] = '.';
					map[blue.x][blue.y] = 'B';
					print();
					break;
				}
			}
		}
		
	}
	
	public static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
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
