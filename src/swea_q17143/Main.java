package swea_q17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static shark[] arr;
	static int r,c,m;
	static int[][] map;
	static int total = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		arr = new shark[m+1];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y] = i+1;
			arr[i+1] = new shark(x,y,s,d,z);
		}
		
		for(int i=0; i<c; i++) {
			fishing(i);
			moving();
		}
		
		
		System.out.println(total);
	}

	static void fishing(int index) {
		for(int i=0; i<r; i++) {
			if(map[i][index] != 0) {
				total += arr[map[i][index]].z;
				arr[map[i][index]] = null;
				map[i][index] = 0;
				break;
			}
		}
	}
	
	static void moving() {
		map = new int[r][c];
		for(int j=0; j<arr.length; j++) {
			if(arr[j] != null) {
				shark s = arr[j];
				
				int new_x = s.x;
				int new_y = s.y;
				
				int loop = 0;
				int speed = s.s;
				if(s.d < 3) 
					loop = 2 * (r - 1);
				else
					loop = 2 * (c - 1);
				if(speed >= loop) speed %= loop;
				
				for(int i=0; i<speed; i++) {
					int dt = s.d;
					switch(dt) {
					case 1:
						new_x += dx[dt-1];
						
						if(new_x < 0) {
							new_x += 2;
							s.d = 2;
						}
						break;
					case 2:
						new_x += dx[dt-1];
						
						if(new_x > r-1) {
							new_x -= 2;
							s.d = 1;
						}
						break;			
					case 3:
						new_y += dy[dt-1];
						
						if(new_y > c-1) {
							new_y -= 2;
							s.d = 4;
						}
						break;
					case 4:
						new_y += dy[dt-1];
						
						if(new_y < 0) {
							new_y += 2;
							s.d = 3;
						}
						break;
					}
				}
				
				s.x = new_x;
				s.y = new_y;
				
				if(map[s.x][s.y] != 0) {
					shark temp = arr[map[s.x][s.y]];
					
					if(temp.z > s.z) {
						arr[j] = null;
					} else {
						arr[map[s.x][s.y]] = null;
						map[s.x][s.y] = j;
					}
				} else {
					map[s.x][s.y] = j;
				}
			}
		}
	}
	
	static void sharkMoving(shark s) {
		int origin_x = s.x;
		int origin_y = s.y;
		int new_x = s.x;
		int new_y = s.y;
		
		for(int i=0; i<s.s; i++) {
			int dt = s.d;
			switch(dt) {
			case 1:
				new_x += dx[dt-1];
				
				if(new_x < 0) {
					new_x += 2;
					s.d = 2;
				}
				break;
			case 2:
				new_x += dx[dt-1];
				
				if(new_x > r-1) {
					new_x -= 2;
					s.d = 1;
				}
				break;			
			case 3:
				new_y += dy[dt-1];
				
				if(new_y > c-1) {
					new_y -= 2;
					s.d = 4;
				}
				break;
			case 4:
				new_y += dy[dt-1];
				
				if(new_y < 0) {
					new_y += 2;
					s.d = 3;
				}
				break;
			}
		}
		
	}

}

class shark {
	int x;
	int y;
	int s;
	int d;
	int z;
	
	public shark(int x,int y,int s,int d,int z) {
		this.x = x;
		this.y = y;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}