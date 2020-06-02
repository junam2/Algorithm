package swea_2382_�̻���_�ݸ�;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	static int n,m,k;
	static int[][] map;
	static int[][] count;
	static ArrayList<microbe> arr;
	
	public static void main(String[] args) throws Exception{
		/*
		 * 1. �ϴ� �������� �̵� ��Ų��.
		 * 2. �̵� ��Ű�鼭 ���� �ڸ��� �ٴٸ��� �̻��� �� �̰� ���� �ٲٱ�
		 * 3. �� �̵� ��Ű�� �� ĭ�� �������� �� �� ���� ����
		 * 3-1. count[][] �迭 ����Ͽ� count ���ֱ�
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new ArrayList<microbe>();
			
			for(int i=0; i<k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				arr.add(new microbe(x,y,cnt,dir, true));
			}
			
			for(int i=0; i<m; i++) {
				count = new int[n][n];
				//���� �̵�
				move();
				
				//�̵� �� �� ĭ�� ���� ���� �ִ� �� ó��
				for(int j=0; j<n; j++) {
					for(int k=0; k<n; k++) {
						if(count[j][k] >= 2) {
							sumMicrobe(j,k);
						}
					}
				}
			}
			
			int result = 0;
			
			for(int i=0; i<arr.size(); i++) {
				if(arr.get(i).isAlive) {
					result += arr.get(i).cnt;
				}
			}
			
			System.out.println("#" + (t+1) + " " + result);
		}
	}
	
	static void sumMicrobe(int j,int k) {
		ArrayList<microbe> tmp = new ArrayList<>();
	
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i).isAlive && arr.get(i).x == j && arr.get(i).y == k) {
				tmp.add(arr.get(i));
			}
		}
		
		tmp.sort(new Comparator<microbe>() {

			@Override
			public int compare(microbe arg0, microbe arg1) {
				return (arg0.cnt>arg1.cnt)?-1:1;
			}
		});
		
		int total = 0;
		
		for(int i=0; i<tmp.size(); i++) {
			total += tmp.get(i).cnt;
			
			if(i!=0) {
				tmp.get(i).isAlive = false;
			}
		}
		
		tmp.get(0).cnt = total;
	}
	
	static void move() {
		for(int i=0; i<arr.size(); i++) {
			microbe m = arr.get(i);
			
			if(m.isAlive) {
				int x = m.x + dx[m.dir];
				int y = m.y + dy[m.dir];
				
				if(x<0 || x>=n || y<0 || y>=n) continue;
				
				if(isSide(x,y)) {
					int ncnt = m.cnt/2;
					
					if(ncnt == 0) {
						m.isAlive = false;
						continue;
					} else {
						m.x = x;
						m.y = y;
						m.cnt = ncnt;
						m.dir = getOpposite(m.dir);
						count[x][y]++;
					}
				} else {
					m.x = x;
					m.y = y;
					count[x][y]++;
				}
			}
		}
	}
	
	static int getOpposite(int dir) {
		if(dir == 1) {
			return 2;
		} else if(dir == 2) {
			return 1;
		} else if(dir == 3) {
			return 4;
		} else {
			return 3;
		}
	}
	
	static boolean isSide(int x,int y) {
		if(x==0 || x==n-1 || y==0 || y==n-1) return true;
		
		return false;
	}

}

class microbe {
	int x;
	int y;
	int cnt;
	int dir;
	boolean isAlive;
	
	public microbe(int x,int y,int cnt,int dir, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.dir = dir;
		this.isAlive = isAlive;
	}
}