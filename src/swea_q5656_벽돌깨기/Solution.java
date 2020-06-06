package swea_q5656_��������;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static int w,h,n;
	static block[][] origin_map;
	static block[][] map;
	static boolean[][] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		/*
		 * �ߺ����� (���� �Ű���ҵ� ) ���
		 * 1. w �߿� n �� ����.
		 * 2. ���������� ���� ����� ������ ã�´�.
		 * 3. �� ���� �������� �ı���Ų��.
		 * 4. ��� ��� ���鼭 ���ó�� Ǯ�� ��
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new block[h][w];
			origin_map = new block[h][w];
			result = Integer.MAX_VALUE;
			
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					int size = Integer.parseInt(st.nextToken());
					origin_map[i][j] = new block(i,j,size);
					map[i][j] = new block(i,j,size);
				}
			}
			
			LinkedList<Integer> perm = new LinkedList<Integer>();
			
			//w�� �߿� n�� ����.
			rePermutation(w,n,perm);
			
			System.out.println("#" + (t+1) + " " + result);
		}
		
	}

	
	static void rePermutation(int w, int n, LinkedList<Integer> perm) {
		if(perm.size() == n) {
			//���� �ƴ� ���� ���ϱ�
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					map[i][j].size = origin_map[i][j].size;
				}
			}
			
			visited = new boolean[h][w];
			
			for(int i=0; i<perm.size(); i++) {
				int nh = getH(perm.get(i));
				
				if(nh == -1) continue;

				//����
				explosion(perm.get(i),nh);
				
				//��������
				mapDown();
			}
			
			int tmp = 0;
			
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j].size != 0) {
						tmp++;
					}
				}
			}
			
			result = Math.min(result, tmp);
			return;
		}
		
		for(int i=0; i<w; i++) {
			perm.add(i);
			rePermutation(w,n,perm);
			perm.removeLast();
		}
	}
	
	static void mapDown() {
		//�� y��ǥ�� ���ؼ�
		for(int i=0; i<w; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			
			for(int j=h-1; j>=0; j--) {
				if(map[j][i].size > 0) q.add(map[j][i].size);
			}
			
			for(int j=h-1; j>=0; j--) {
				if(!q.isEmpty()) {
					map[j][i].size = q.poll();
				} else {
					map[j][i].size = 0;
				}
			}
		}
	}
	
	static void explosion(int nw,int nh) {
		//if(visited[nh][nw]) return;
		
		visited[nh][nw] = true;
		block b = map[nh][nw];
		int size = b.size;
		
		if(size > 1) {
			for(int i=1; i<size; i++) {
				for(int j=0; j<4; j++) {
					int x = b.x + dx[j]*i;
					int y = b.y + dy[j]*i;
					
					if(x<0 || x>=h || y<0 || y>=w) continue;
					
					if(map[x][y].size != 0) {
						block b1 = map[x][y];
						int size2 = b1.size;
						
						if(size2 > 1 && !visited[b1.x][b1.y]) {
							explosion(b1.y,b1.x);
						}
						
						map[x][y].size = 0;
					}
				}
			}
		}
		
		map[b.x][b.y].size = 0;;
	}
	
	static int getH(int w) {		
		for(int i=0; i<h; i++) {
			if(map[i][w].size != 0) {
				return i;
			}
		}
		
		return -1;
	}
}

class block {
	int x;
	int y;
	int size;
	
	public block(int x,int y,int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
}