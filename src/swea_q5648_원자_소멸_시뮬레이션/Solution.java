package swea_q5648_원자_소멸_시뮬레이션;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n, result;
	static int[][] count;
	static boolean[][] visited;
	static atom[] atoms;
	static int mapPadding = 1000;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 참고. 소수점에서도 충돌할 수 있으므로 *2 하여 만날 수 있는 상황을 모두 정수로 만들어준다.
		 * count와 충돌 여부 배열을 만들어 count가 2 이상이면 충돌로 해서 나중에 한 방에 처리
		 * 중요한 점: count랑 visited 배열 초기화 계속 해주면 시간초과 난다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int test = Integer.parseInt(st.nextToken());
		
		count = new int[4001][4001];
		visited = new boolean[4001][4001];
		
		for(int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			atoms = new atom[n];
			result = 0;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = (mapPadding + Integer.parseInt(st.nextToken()))*2;
				int y = (mapPadding + Integer.parseInt(st.nextToken()))*2;
				int dir = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				atoms[i] = new atom(x,y,dir,power);
				count[x][y]++;
			}
			
			//구간 내 모든 원자가 범위 벗어나는 최대 이동 거리
			int numOfAtom = n;
			
			for(int i=0; i<4006; i++) {
				//각 원자를 돌면서 범위 벗어나면 없애고, 아니면 이동시킨다.
				//그 중 충돌하는 상황이 오면 visited flag 바꿔준다.
				for(int j=0; j<numOfAtom; j++) {
					atom tmp = atoms[j];
					
					count[tmp.x][tmp.y]--;
					
					int nx = tmp.x + dx[tmp.dir];
					int ny = tmp.y + dy[tmp.dir];
					
					//범위 벗어 났을 경우 원자를 없애버린다.
					if(nx<0 || nx>=4001 || ny<0 || ny>=4001) {
						atoms[j] = atoms[numOfAtom-1];
						numOfAtom--;
						j--;
					} else {
						count[nx][ny]++;
						tmp.x = nx;
						tmp.y = ny;
						
						if(count[nx][ny]>=2) {
							visited[nx][ny] = true;
						}
					}
				}
				
				//충돌한 것들에 대해 처리해준다.
				for(int j=0; j<numOfAtom; j++) {
					atom tmp = atoms[j];
					
					if(visited[tmp.x][tmp.y]) {
						result += tmp.power;
						
						if(count[tmp.x][tmp.y] == 1) {
							visited[tmp.x][tmp.y] = false;
						}
						
						count[tmp.x][tmp.y]--;
						atoms[j] = atoms[numOfAtom-1];
						numOfAtom--;
						j--;
					}
				}
				
				if(numOfAtom == 0) {
					break;
				}
			}
			
			System.out.println("#" + (t+1) + " " + result);
			
		}
	}

}

class atom {
	int x;
	int y;
	int dir;
	int power;
	
	public atom(int x,int y, int dir, int power) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.power = power;
	}
}
