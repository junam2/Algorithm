package q1938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1,1,-1,1,-1};
	static int[] dy = {1,-1,0,0,-1,1,1,-1};
	static char[] command = {'U','D','L','R','T'};
	static int n;
	static tree start,end;
	static char[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		/*
		 * bfs로다가 계속 추가하면서 이동한다.
		 * 방문 여부는 중심점에서의 가로인지 세로인지로 판단
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		map = new char[n][n];
		//0:가로  1:세로
		visited = new boolean[2][n][n];
		start = new tree(0);
		end = new tree(0);
		
		
		for(int i=0; i<n; i++) {
			char[] ch = br.readLine().toCharArray();
			map[i] = ch;
			for(int j=0; j<n; j++) {
				if(map[i][j] == 'B') {
					start.arr.add(new edge(i,j));
				} else if(map[i][j] == 'E') {
					end.arr.add(new edge(i,j));
				}
			}
		}
		
		start = setCenterType(start);
		end = setCenterType(end);
		
		bfs(start);
		
		System.out.println(0);
	}
	
	static void bfs(tree t) {
		visited[t.type][t.centerX][t.centerY] = true;
		Queue<tree> q = new LinkedList<tree>();
		q.add(t);
		
		while(!q.isEmpty()) {
			tree v = q.poll();
			
			if(v.centerX == end.centerX && v.centerY == end.centerY && v.type == end.type) {
				System.out.println(v.cnt);
				System.exit(0);
			}
			
			for(int i=0; i<5; i++) {
				char cmd = command[i];
				int x = 0;
				int y = 0;
				if(isAvailCmd(cmd, v)) {
					//명령별 시작
					switch(cmd) {
					case 'U':
						x = -1;
						break;
					case 'D':
						x = 1;
						break;
					case 'L':
						y = -1;
						break;
					case 'R':
						y = 1;
						break;
					case 'T':
						x = 1;
						y = 1;
						break;
					}
					
					tree tmp = new tree(v.cnt+1);
					tmp.centerX = v.centerX;
					tmp.centerY = v.centerY;
					for(int j=0; j<v.arr.size(); j++) {
						tmp.arr.add(new edge(v.arr.get(j).x, v.arr.get(j).y));
					}
					
					tmp.type = v.type;
					
					if(cmd != 'T') {
						for(int j=0; j<tmp.arr.size(); j++) {
							tmp.arr.get(j).x += x;
							tmp.arr.get(j).y += y;
						}
					} else {
						if(tmp.type==0) {
							tmp.arr.get(0).x -= x;
							tmp.arr.get(0).y += y;
							tmp.arr.get(2).x += x;
							tmp.arr.get(2).y -= y;
						} else {
							tmp.arr.get(0).x += x;
							tmp.arr.get(0).y += y;
							tmp.arr.get(2).x -= x;
							tmp.arr.get(2).y -= y;
						}
						
						tmp.arr.sort(new Comparator<edge>() {

							@Override
							public int compare(edge arg0, edge arg1) {
								if(arg0.x > arg1.x) {
									return 1;
								} else if(arg0.x < arg1.x) {
									return -1;
								} else {
									if(arg0.y > arg0.y) {
										return 1;
									} else {
										return -1;
									}
								}
							}
						});
						
						tmp.type = (tmp.type==1)?0:1;
					}
					tmp.centerX = tmp.arr.get(1).x;
					tmp.centerY = tmp.arr.get(1).y;
					visited[tmp.type][tmp.arr.get(1).x][tmp.arr.get(1).y] = true;
					q.add(tmp);
				}
			}
		}
		
	}
	
	static boolean isAvailCmd(char cmd, tree t) {
		int x = 0;
		int y = 0;
		
		switch(cmd) {
		case 'U':
			x = -1;
			break;
		case 'D':
			x = 1;
			break;
		case 'L':
			y = -1;
			break;
		case 'R':
			y = 1;
			break;
		case 'T':
			x = 1;
			y = 1;
			break;
		}
		
		if(cmd != 'T') {
			for(int i=0; i<t.arr.size(); i++) {
				edge e = t.arr.get(i);
				
				int nx = e.x + x;
				int ny = e.y + y;
				
				if(nx<0 || nx>=n || ny<0 || ny>=n) return false;
				if(map[nx][ny] == '1') return false;
				if(i==1) {
					if(visited[t.type][nx][ny]) return false;
				}
			}
		} else {
			int oppositeType = (t.type==1)?0:1;
			if(visited[oppositeType][t.centerX][t.centerY]) return false;
			
			for(int i=0; i<dx.length; i++) {
				int nx = t.centerX + dx[i];
				int ny = t.centerY + dy[i];
				
				if(nx<0 || nx>=n || ny<0 || ny>=n) return false;
				if(map[nx][ny] == '1') return false;
				
			}
		}
		
		return true;
	}
	
	static tree setCenterType(tree t) {
		t.centerX = t.arr.get(1).x;
		t.centerY = t.arr.get(1).y;
		
		edge first = t.arr.get(0);
		edge center = t.arr.get(1);
		//0:가로  1:세로
		if(first.x == center.x) {
			t.type = 0;
		} else {
			t.type = 1;
		}
		
		return t;
	}

}

class tree {
	int centerX;
	int centerY;
	//0:가로  1:세로
	int type;
	ArrayList<edge> arr = new ArrayList<edge>();
	int cnt;
	
	public tree(int cnt) {
		this.cnt = cnt;
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