package swea_q5648_����_�Ҹ�_�ùķ��̼�;

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
		 * ����. �Ҽ��������� �浹�� �� �����Ƿ� *2 �Ͽ� ���� �� �ִ� ��Ȳ�� ��� ������ ������ش�.
		 * count�� �浹 ���� �迭�� ����� count�� 2 �̻��̸� �浹�� �ؼ� ���߿� �� �濡 ó��
		 * �߿��� ��: count�� visited �迭 �ʱ�ȭ ��� ���ָ� �ð��ʰ� ����.
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
			
			//���� �� ��� ���ڰ� ���� ����� �ִ� �̵� �Ÿ�
			int numOfAtom = n;
			
			for(int i=0; i<4006; i++) {
				//�� ���ڸ� ���鼭 ���� ����� ���ְ�, �ƴϸ� �̵���Ų��.
				//�� �� �浹�ϴ� ��Ȳ�� ���� visited flag �ٲ��ش�.
				for(int j=0; j<numOfAtom; j++) {
					atom tmp = atoms[j];
					
					count[tmp.x][tmp.y]--;
					
					int nx = tmp.x + dx[tmp.dir];
					int ny = tmp.y + dy[tmp.dir];
					
					//���� ���� ���� ��� ���ڸ� ���ֹ�����.
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
				
				//�浹�� �͵鿡 ���� ó�����ش�.
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
