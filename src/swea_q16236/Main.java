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
		
		//�ּ� �Ÿ� ���ϱ�
		for(int i=0; i<arr.size(); i++) {
			distance.add(tmp_map[arr.get(i).x][arr.get(i).y]);
		}
		
		int min_distance = Collections.min(distance);
		
		//�ּ� �Ÿ��� x,y ��ǥ ���ϱ�
		for(int i=0; i<arr.size(); i++) {
			if(tmp_map[arr.get(i).x][arr.get(i).y] == min_distance) {
				minDistanceDot_x.add(arr.get(i).x);
				minDistanceDot_y.add(arr.get(i).y);
			}
		}
		
		//���� ���� x��ǥ ���ϱ�
		int min_x = Collections.min(minDistanceDot_x);
		int min_y = Integer.MAX_VALUE;
		
		//���� ���� x��ǥ�� ���� ������ x��ǥ ���ϱ�
		for(int i=0; i<minDistanceDot_x.size(); i++) {
			if(minDistanceDot_x.get(i) == min_x) {
				if(min_y > minDistanceDot_y.get(i)) {
					min_y = minDistanceDot_y.get(i);
				}
			}
		}
		
		//min_y index�� �ִ� minDot���ϱ�.
		dot minDot = new dot(min_x, min_y);
		
		//�Ʊ�� ���̸� �԰� �̵�
		map[s_x][s_y] = 0;
		s_x = minDot.x;
		s_y = minDot.y;
		map[s_x][s_y] = 9;
		//���̸� ���� �� üũ�ؼ� shark_size�� ������ shark_size �����ϰ�  ���� count�� �ٽ� 0����
		count++;
		if(count == shark_size) {
			shark_size++;
			count = 0;
		}
		//�ּ� �Ÿ� �ð���ŭ �����ֱ�
		time += min_distance;
		
		return minDot;
		
	}
	
	static void solve(dot d) {
		//�� ó�� d�� �־��ְ� arr ������ üũ (0�̶�� ����)
		//�ƴ϶�� whoEat() ����
		//whoEat()���� �����ϸ� visited�� �Ÿ��� �����ϴ� tmp_map �ʱ�ȭ
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