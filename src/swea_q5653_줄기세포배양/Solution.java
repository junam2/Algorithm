package swea_q5653_�ٱ⼼�����;

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
		/*  Priority Queue ���
		 *  ������ ������ �ֵ��� pq�� �־��ش�. �� ���̶� ������ �ֵ��� Ȱ��ȭ �ð��� ���� �� �� �̻� ���ĵ��� �ʴ´�. (�����¿�� �����ؼ�)
		 *  ���ÿ� ������ �ֵ��� pq�� �˾Ƽ� ó�����ش�.
		 *  
		 *  1. �ϴ� ��� �ֵ� q�� �־��ش�.
		 *  2. Ȱ��ȭ�� �Ǹ� pq�� �־��ش�.
		 *  3. �����ϰ� Ȱ��ȭ�ϴ� �Ⱓ ������ �ٽ� q�� �־��ش�.
		 *  4. k �ð� �� q ������ ���
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
					
					//Ȱ��ȭ�ǰ� 1�� ������ pq�� �ְ�, ��Ȱ���̸� �׳� �ְ�, Ȱ���̵� �׳� �־�
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
