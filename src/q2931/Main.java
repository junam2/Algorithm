package q2931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int n,m;
	static char[][] map;
	static pipe empty_pipe;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i=0; i<n; i++) {
			char[] arr = br.readLine().toCharArray();
			
			map[i] = arr;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] != '.' && map[i][j] != 'M' && map[i][j] != 'Z') {
					check(i,j);
				}
			}
		}
		
		setType();
		
		System.out.println((empty_pipe.x+1) + " " + (empty_pipe.y+1) + " " + empty_pipe.type);
	}
	
	static void setType() {
		int count = 0;
		
		for(int i=0; i<empty_pipe.dir.length; i++) {
			if(empty_pipe.dir[i]) count++;
		}
		
		// 0:©Л 1:го 2:аб 3:╩С
		if(count == 4) {
			empty_pipe.type = '+';
		} else {
			if(empty_pipe.dir[1] && empty_pipe.dir[3]) {
				empty_pipe.type = '|';
			} else if(empty_pipe.dir[0] && empty_pipe.dir[2]) {
				empty_pipe.type = '-';
			} else if(empty_pipe.dir[0] && empty_pipe.dir[1]) {
				empty_pipe.type = '1';
			} else if(empty_pipe.dir[0] && empty_pipe.dir[3]) {
				empty_pipe.type = '2';
			} else if(empty_pipe.dir[2] && empty_pipe.dir[3]) {
				empty_pipe.type = '3';
			} else if(empty_pipe.dir[1] && empty_pipe.dir[2]) {
				empty_pipe.type = '4';
			}
		}
	}
	
	static void findPipe(int x,int y, ArrayList<Integer> arr) {
			for(int i=0; i<arr.size(); i++) {
				int nx = x + dx[arr.get(i)];
				int ny = y + dy[arr.get(i)];
				
				if(nx>=0 && nx<n && ny>=0 && ny<m) {
					if(map[nx][ny] == '.') {
						if(empty_pipe == null) {
							empty_pipe = new pipe(nx,ny);
						}
						empty_pipe.dir[(arr.get(i)+2)%4] = true;				
					}
				}
			}

	}
	
	static void check(int x,int y) {
		char tmp = map[x][y];
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		// 0:©Л 1:го 2:аб 3:╩С
		switch(tmp) {
		case '|':
			arr.add(1); arr.add(3);
			break;
		case '-':
			arr.add(0); arr.add(2);
			break;
		case '+':
			arr.add(0); arr.add(1); arr.add(2); arr.add(3);
			break;
		case '1':
			arr.add(0); arr.add(1);
			break;
		case '2':
			arr.add(0); arr.add(3); 
			break;
		case '3':
			arr.add(2); arr.add(3);
			break;
		case '4':
			arr.add(2); arr.add(1);
			break;
		}
		
		findPipe(x,y,arr);
	}
}

class pipe {
	int x;
	int y;
	char type;
	
	boolean[] dir = new boolean[4];
	
	public pipe(int x,int y) {
		this.x = x;
		this.y = y;
	}
}