package q3987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//U R D L 순
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int n,m;
	static char[][] map;
	static int[] result = new int[4];
	static int tmp;
	static int max_value = Integer.MIN_VALUE;
	static edge start;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 참고: '/' 면 (U,R),(D,L), '\' 면 (U,L),(D,R)이 pair
		 * 1. U,R,D,L 순으로 레이저 발사. 4개의 값을 저장할 배열 하나 만든다.
		 * 2. 각 값 저장해준다. (혹시 URDL 중에 이동 횟수가 n*m 을 넘어서면 무한히 도는 걸로 간주)
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			char[] arr = br.readLine().toCharArray();
			
			map[i] = arr;
		}
		
		st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		
		start = new edge(x,y);
		
		for(int i=0; i<4; i++) {
			tmp = 0;
			
			bfs(start, i);
		}
		
		char result_dir = 'x';
		int result_value = -1;
		
		for(int i=0; i<4; i++) {
			if(result[i] == max_value) {
				result_dir = getDir(i);
				result_value = (result[i]==Integer.MAX_VALUE)?Integer.MAX_VALUE:result[i];
				break;
			}
		}
		
		System.out.println(result_dir);
		if(result_value == Integer.MAX_VALUE) {
			System.out.println("Voyager");
		} else {
			System.out.println(result_value);
		}
	}
	
	static char getDir(int i) {
		switch(i) {
		case 0:
			return 'U';
		case 1:
			return 'R';
		case 2:
			return 'D';
		case 3:
			return 'L';
		}
		
		return 'x';
	}
	
	static void bfs(edge e, int d) {
		Queue<edge> q = new LinkedList<edge>();
		q.add(e);
		
		int dir = d;
		
		while(!q.isEmpty()) {
			edge v = q.poll();
			
			int x = v.x + dx[dir];
			int y = v.y + dy[dir];
			tmp++;
			
			if(tmp > n*m) {
				result[d] = Integer.MAX_VALUE;
				max_value = Math.max(max_value, result[d]);
				return;
			}
			
			//항성계 밖으로 나간 순간
			if(x<0 || x>=n || y<0 || y>=m) {
				result[d] = tmp;
				max_value = Math.max(max_value, result[d]);
				return;
			}
			
			if(map[x][y] == '.') {
				q.add(new edge(x,y));
			} else if(map[x][y] == 'C') {
				result[d] = tmp;
				max_value = Math.max(max_value, result[d]);
				return;
			} else {
				q.add(new edge(x,y));
				dir = changeDir(map[x][y], dir);
			}
			
		}
	}
	
	static int changeDir(char ch, int dir) {
		// URDL 순
		// '/' 면 (U,R),(D,L), '\' 면 (U,L),(D,R)이 pair

		if(ch == '/') {
			if(dir==0) {
				return 1;
			} else if(dir==1) {
				return 0;
			} else if(dir==2) {
				return 3;
			} else {
				return 2;
			}
		} else {
			if(dir==0) {
				return 3;
			} else if(dir==1) {
				return 2;
			} else if(dir==2) {
				return 1;
			} else {
				return 0;
			}
		}
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
