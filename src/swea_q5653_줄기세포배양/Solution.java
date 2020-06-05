package swea_q5653_줄기세포배양;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int n,m,k,time;
	static int[][] map;
	static boolean[][] visited;
	static Queue<cell> q;
	static PriorityQueue<cell> pq;
	
	public static void main(String[] args) throws Exception {
		/*  Priority Queue 사용
		 *  번식이 가능한 애들은 pq에 넣어준다. 한 번이라도 번식한 애들은 활성화 시간만 지날 뿐 더 이상 번식되지 않는다. (상하좌우로 번식해서)
		 *  동시에 만나느 애들은 pq가 알아서 처리해준다.
		 *  
		 *  1. 일단 모든 애들 q에 넣어준다.
		 *  2. 활성화가 되면 pq에 넣어준다.
		 *  3. 번식하고 활성화하는 기간 동안은 다시 q에 넣어준다.
		 *  4. k 시간 후 q 사이즈 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[1000][1000];
			visited = new boolean[1000][1000];
			time = 0;
			
			q = new LinkedList<cell>();
			pq = new PriorityQueue<>();
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					map[500+i][500+j] = Integer.parseInt(st.nextToken());
					if(map[500+i][500+j] > 0) {
						visited[500+i][500+j] = true;
						q.add(new cell(500+i,500+j,map[500+i][500+j],map[500+i][500+j],map[350+i][350+j]*2));
					}
				}
			}
			
			for(int time=1; time<=k; ++time){
				int size = q.size();
				
				for(int i=0; i<size; i++) {
					cell v = q.poll();
					
					//활성화되고 1초 지나면 pq에 넣고, 비활성이면 그냥 넣고, 활성이도 그냥 넣어
					if(v.start >= time) {
						q.add(v);
					} else if(v.start + 1 == time) {
						pq.add(v);
					} else if(v.start < time && v.end > time) {
						q.add(v);
					}
				}
				
				while(!pq.isEmpty()) {
					cell vv = pq.poll();
					
					if(time < vv.end) {
						q.add(vv);
					}
					
					for(int i=0; i<4; i++) {
						int x = vv.x + dx[i];
						int y = vv.y + dy[i];
												
						if(!visited[x][y]) {
							visited[x][y] = true;
							map[x][y] = vv.power;
							q.add(new cell(x,y,vv.power,time+vv.power, time+2*vv.power));
						}
					}
				}
			}
			
			System.out.println("#" + (t+1) + " "+ q.size());
		}
		
		
	}

}

class cell implements Comparable<cell>{
	int x;
	int y;
	int power;
	int start;
	int end;
	
	public cell(int x, int y, int power, int start, int end) {
		this.x = x;
		this.y = y;
		this.power = power;
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(cell arg0) {
		return (this.power >= arg0.power)?-1:1;
	}
	
	
}
