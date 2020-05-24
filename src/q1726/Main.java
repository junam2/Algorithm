package q1726;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m;
	static int[][] map;
	static boolean[][][] visited;
	static robot start, end;
	static int min_command = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[4][n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		start = new robot(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);
		st = new StringTokenizer(br.readLine());
		end = new robot(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);
		
		
		bfs(start);
				
	}
	
	static boolean dircheck(int a,int b) {
		if((a==0 && b==1) || (a==1 && b==0)) {
			return true;
		}
		
		if((a==2 && b==3) || (a==3 && b==2)) {
			return true;
		}
		
		return false;
	}
	
	static void bfs(robot r) {
		/*
		 * 1. bfs 활용
		 * 2. 방향 이동 필요 체크
		 * 3. 이동 시작 (한 방향으로 이동할 경우 robot.cnt + 1만 해주고 q에 넣어준다.)
		 */
		
		Queue<robot> q = new LinkedList<robot>();
		q.add(r);
		visited[r.dir][r.x][r.y] = true;
		
		
		while(!q.isEmpty()) {
			robot v = q.poll();
			
			//end 도착
			if(v.x == end.x && v.y == end.y && v.dir == end.dir) {
				System.out.println(v.cnt);
				return;
			}
			
			//현재 방향에서 1~3칸 이동
			for(int i=1; i<=3; i++) {
				int x = v.x + dx[v.dir]*i;
				int y = v.y + dy[v.dir]*i;
				
				if(x<0 || x>=n || y<0 || y>=m) break;
				
				/*
				 * 이 부분 주의해야한다.
				 * 만약 if문안에 map과 visited 체크 모두하면 문제 생김
				 * (123456 이렇게 있으면 1에서 1,2,3칸 갈 수 있는데 3에 방문되있으면 아예 break 되버림) 
				 */
				if(map[x][y] != 1) {
					if(!visited[v.dir][x][y]) {
						q.add(new robot(x,y,v.dir,v.cnt+1));
						visited[v.dir][x][y] = true;
					}
				} else {
					break;
				}
			}
			
			//현재 방향에서 회전하기
			for(int i=0; i<4; i++) {
				if(!visited[i][v.x][v.y] && v.dir != i) {
					visited[i][v.x][v.y] = true;
					
					if(dircheck(v.dir, i)) {
						q.add(new robot(v.x,v.y,i,v.cnt+2));
					} else {
						q.add(new robot(v.x,v.y,i,v.cnt+1));
					}
				}
			}
			
		}
		
	}
}

class robot {
	int x;
	int y;
	int dir;
	int cnt;
	
	public robot(int x,int y,int dir, int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
}