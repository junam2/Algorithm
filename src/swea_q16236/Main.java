package swea_q16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[][] tmp_map;
	static int count = 0;
	static int s_x, s_y;
	static int shark_size = 2;
	static int tmp_food = 0;
	static ArrayList<dot> arr = new ArrayList<dot>();
	static int time = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		tmp_map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				
				if(map[i][j] == 9) {
					s_x = i;
					s_y = j;
				}
			}
		}
		
		solve(new dot(s_x, s_y));		
		System.out.println(time);
		
	}
	
	static void find(dot d) {
		arr = new ArrayList<dot>();
		Queue<dot> q = new LinkedList<dot>();
		visited[d.x][d.y] = true;
		q.add(d);
		
		while(!q.isEmpty()) {
			dot t = q.remove();
			int x = t.x;
			int y = t.y;
			
			if(map[x][y] < shark_size && map[x][y] >= 1 && map[x][y] <=6) {
				arr.add(new dot(x,y));
			}
			
			for(int i=0; i<4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				
				if(isRange(x1,y1) && !visited[x1][y1] && tmp_map[x1][y1] == 0 && map[x1][y1] <= shark_size) {
					q.add(new dot(x1,y1));
					tmp_map[x1][y1] = tmp_map[x][y] + 1;
					visited[x1][y1] = true;
					
				}
			}
		}

	}
	
	static boolean isRange(int x,int y) {
		if(x>=0 && x<n && y>=0 && y<n) {
			return true;
		} else {
			return false;
		}
	}
	
	
	static dot whoEat() {
		ArrayList<Integer> distance = new ArrayList<Integer>();
		ArrayList<Integer> minDistanceDot_x = new ArrayList<Integer>();
		ArrayList<Integer> minDistanceDot_y = new ArrayList<Integer>();
		
		//최소 거리 구하기
		for(int i=0; i<arr.size(); i++) {
			distance.add(tmp_map[arr.get(i).x][arr.get(i).y]);
		}
		
		int min_distance = Collections.min(distance);
		
		//최소 거리의 x,y 좌표 구하기
		for(int i=0; i<arr.size(); i++) {
			if(tmp_map[arr.get(i).x][arr.get(i).y] == min_distance) {
				minDistanceDot_x.add(arr.get(i).x);
				minDistanceDot_y.add(arr.get(i).y);
			}
		}
		
		//가장 위의 x좌표 구하기
		int min_x = Collections.min(minDistanceDot_x);
		int min_y = Integer.MAX_VALUE;
		
		//가장 위의 x좌표중 가장 왼쪽의 x좌표 구하기
		for(int i=0; i<minDistanceDot_x.size(); i++) {
			if(minDistanceDot_x.get(i) == min_x) {
				if(min_y > minDistanceDot_y.get(i)) {
					min_y = minDistanceDot_y.get(i);
				}
			}
		}
		
		//min_y index에 있는 minDot구하기.
		dot minDot = new dot(min_x, min_y);
		
		//아기상어가 먹이를 먹고 이동
		map[s_x][s_y] = 0;
		s_x = minDot.x;
		s_y = minDot.y;
		map[s_x][s_y] = 9;
		//먹이를 먹은 수 체크해서 shark_size와 같으면 shark_size 증가하고  먹은 count를 다시 0으로
		count++;
		if(count == shark_size) {
			shark_size++;
			count = 0;
		}
		//최소 거리 시간만큼 더해주기
		time += min_distance;
		
		return minDot;
		
	}
	
	static void solve(dot d) {
		//맨 처음 d를 넣어주고 arr 사이즈 체크 (0이라면 종료)
		//아니라면 whoEat() 실행
		//whoEat()까지 실행하면 visited와 거리를 측정하는 tmp_map 초기화
		dot tmp = d;
		while(true) {
			find(tmp);
			if(arr.size()==0) {
				break;
			}
			tmp = whoEat();
			
			visited = new boolean[n][n];
			tmp_map = new int[n][n];
			
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