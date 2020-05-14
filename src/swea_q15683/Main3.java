package swea_q15683;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static int n,m;
	static int[][] map;
	static int res = Integer.MAX_VALUE;
	static ArrayList<cctv> arr = new ArrayList<cctv>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			
				if(map[i][j] != 0 && map[i][j] != 6) {
					arr.add(new cctv(i,j,map[i][j]));
				}
			}
		}
		
		start(0, map);
		System.out.println(res);
		
	}
	
	static void start(int index, int[][] prev) {
		int[][] tmp_map = new int[n][m];
		if(index == arr.size()) {
			int tmp = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(prev[i][j] == 0) {
						tmp++;
					}
				}
			}
			
			if(res > tmp) {
				res = tmp;
			}
		} else {
			cctv tmp = arr.get(index);
			
			switch(tmp.type) {
			case 1:
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						tmp_map[j] = Arrays.copyOf(prev[j], m);
					}
					
					detect(tmp.x, tmp.y, i, tmp_map);
					start(index + 1, tmp_map);
				}
				break;
			case 2:
				for(int i=0; i<2; i++) {
					for(int j=0; j<n; j++) {
						tmp_map[j] = Arrays.copyOf(prev[j], m);
					}
					
					detect(tmp.x, tmp.y, i, tmp_map);
					detect(tmp.x, tmp.y, i+2, tmp_map);
					start(index+1, tmp_map);
				}
				break;
			case 3:
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						tmp_map[j] = Arrays.copyOf(prev[j], m);
					}
					
					detect(tmp.x, tmp.y, i, tmp_map);
					detect(tmp.x, tmp.y, (i+1)%4, tmp_map);
					start(index + 1, tmp_map);
				}				
				break;
			case 4:
				for(int i=0; i<4; i++) {
					for(int j=0; j<n; j++) {
						tmp_map[j] = Arrays.copyOf(prev[j], m);
					}
					
					detect(tmp.x, tmp.y, i, tmp_map);
					detect(tmp.x, tmp.y, (i+1)%4, tmp_map);
					detect(tmp.x, tmp.y, (i+2)%4, tmp_map);
					start(index + 1, tmp_map);
				}
				break;
			case 5:
				for(int j=0; j<n; j++) {
					tmp_map[j] = Arrays.copyOf(prev[j], m);
				}
				
				detect(tmp.x, tmp.y, 0, tmp_map);
				detect(tmp.x, tmp.y, 1, tmp_map);
				detect(tmp.x, tmp.y, 2, tmp_map);
				detect(tmp.x, tmp.y, 3, tmp_map);
				start(index + 1, tmp_map);
				break;
			}
					
		}
	}
	
	static void detect(int x,int y,int num, int[][] visited) {
		switch(num) {
		case 0:
			for(int i=x; i>=0; i--) {
				if(map[i][y] == 6) {
					break;
				}
				visited[i][y] = 7;
			}
			break;
		case 1:
			for(int i=y; i<m; i++) {
				if(map[x][i] == 6) {
					break;
				}
				visited[x][i] = 7;
			}
			break;
		case 2:
			for(int i=x; i<n; i++) {
				if(map[i][y] == 6) {
					break;
				}
				visited[i][y] = 7;
			}
			break;
		case 3:
			for(int i=y; i>=0; i--) {
				if(map[x][i] == 6) {
					break;
				}
				visited[x][i] = 7;
			}
			break;
		}
	}

}

class cctv {
	int x;
	int y;
	int type;
	
	public cctv(int x,int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
