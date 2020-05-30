package swea_q17837;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	static int n,k;
	static floor[][] map;
	static int result = 0;
	static boolean flag = true;
	static ArrayList<horse> horses = new ArrayList<horse>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new floor[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j] = new floor(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int nx = Integer.parseInt(st.nextToken());
			int ny = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			horses.add(new horse((i+1),nx,ny,dir));
			map[nx][ny].arr.add(horses.get(i));
		}
		
		loop: while(result<=1000 && flag) {
			if(result>1000) {
				break loop;
			}
			
			result++;
			
			
			for(int i=0; i<horses.size(); i++) {
				horse tmp = horses.get(i);
				move(tmp);
				
				if(!flag) {
					break loop;
				}
			}
			
		}
		
		if(result>1000) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}
	
	static void move(horse h) {
		/*
		 * 1. 흰,빨,파 확인  (범위 벗어나면 파랑색)
		 * 2. arr에서 위에 있는 것 같은 위치로 바꾸기 
		 * 3. 옮긴 위치 floor 말 개수 확인
		 */
		int origin_x = h.x;
		int origin_y = h.y;
		int nx = h.x + dx[h.dir];
		int ny = h.y + dy[h.dir];
		
		if((nx<1 || nx>n || ny<1 || ny>n) || map[nx][ny].color == 2) {
			//파랑
			int newDir = getOpposition(h.dir);
			int nnx = origin_x + dx[newDir];
			int nny = origin_y + dy[newDir];
			h.dir = newDir;
			
			if(nnx<1 || nnx>n || nny<1 || nny>n) {
				return;
			}
			
			if(map[nnx][nny].color != 2) {
				if(map[nnx][nny].color == 0) {
					int index = 0;
					for(int i=0; i<map[origin_x][origin_y].arr.size(); i++) {
						if(map[origin_x][origin_y].arr.get(i).num == h.num) {
							index = i;
							break;
						}
					}
					
					int count = map[origin_x][origin_y].arr.size() - index;
					int size = map[origin_x][origin_y].arr.size();
					
					for(int i=index; i<size; i++) {
						horse t = map[origin_x][origin_y].arr.get(i);
						t.x = nnx;
						t.y = nny;
						
						map[nnx][nny].arr.add(t);
					}
					
					if(map[nnx][nny].arr.size() >= 4) {
						flag = false;
						return;
					}
					
					for(int i=0; i<count; i++) {
						map[origin_x][origin_y].arr.remove(index);
					}
				} else if(map[nnx][nny].color == 1) {
					int index = 0;
					for(int i=0; i<map[origin_x][origin_y].arr.size(); i++) {
						if(map[origin_x][origin_y].arr.get(i).num == h.num) {
							index = i;
							break;
						}
					}
					
					int count = map[origin_x][origin_y].arr.size() - index;
					int size = map[origin_x][origin_y].arr.size();

					ArrayList<horse> inverse = new ArrayList<horse>();
					
					for(int i=index; i<size; i++) {
						horse t = map[origin_x][origin_y].arr.get(i);
						t.x = nnx;
						t.y = nny;
						
						inverse.add(t);
					}
					
					for(int i=inverse.size()-1; i>=0; i--) {
						map[nnx][nny].arr.add(inverse.get(i));
					}
					
					if(map[nnx][nny].arr.size() >= 4) {
						flag = false;
						return;
					}
					
					for(int i=0; i<count; i++) {
						map[origin_x][origin_y].arr.remove(index);
					}
				}
			}
			
		} else if(map[nx][ny].color == 0) {
			//흰색
			int index = 0;
			for(int i=0; i<map[origin_x][origin_y].arr.size(); i++) {
				if(map[origin_x][origin_y].arr.get(i).num == h.num) {
					index = i;
					break;
				}
			}
			
			int count = map[origin_x][origin_y].arr.size() - index;
			int size = map[origin_x][origin_y].arr.size();
			
			for(int i=index; i<size; i++) {
				horse t = map[origin_x][origin_y].arr.get(i);
				t.x = nx;
				t.y = ny;
				
				map[nx][ny].arr.add(t);
			}
			
			if(map[nx][ny].arr.size() >= 4) {
				flag = false;
				return;
			}
			
			for(int i=0; i<count; i++) {
				map[origin_x][origin_y].arr.remove(index);
			}
		} else if(map[nx][ny].color == 1) {
			//빨강
			int index = 0;
			for(int i=0; i<map[origin_x][origin_y].arr.size(); i++) {
				if(map[origin_x][origin_y].arr.get(i).num == h.num) {
					index = i;
					break;
				}
			}
			
			int count = map[origin_x][origin_y].arr.size() - index;
			int size = map[origin_x][origin_y].arr.size();
			
			ArrayList<horse> inverse = new ArrayList<horse>();
			
			for(int i=index; i<size; i++) {
				horse t = map[origin_x][origin_y].arr.get(i);
				t.x = nx;
				t.y = ny;
				
				inverse.add(t);
			}
			
			for(int i=inverse.size()-1; i>=0; i--) {
				map[nx][ny].arr.add(inverse.get(i));
			}
			
			if(map[nx][ny].arr.size() >= 4) {
				flag = false;
				return;
			}
			
			for(int i=0; i<count; i++) {
				map[origin_x][origin_y].arr.remove(index);
			}
		}
		
	}
	
	static int getOpposition(int dir) {
		if(dir == 1) {
			return 2;
		} else if(dir == 2) {
			return 1;
		} else if(dir == 3) {
			return 4;
		} else {
			return 3;
		}
	}
}

class floor {
	int color;
	ArrayList<horse> arr = new ArrayList<horse>();
	
	public floor(int color) {
		this.color = color;
	}
}

class horse {
	int num;
	int x;
	int y;
	int dir;
	
	public horse(int num,int x,int y, int dir) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}