package swea_q16235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0,0,1,-1,1,1,-1,-1};
	static int[] dy = {1,-1,0,0,-1,1,1,-1};
	static int n,m,k;
	static int[][] food;
	static ground[][] grounds;
	
	public static void main(String[] args) throws Exception{
		/*
		 * �� - ��ġ / ��� /����ִ� ����  ArrayList
		 * ���� - ����
		 * 
		 * 1. ����ִ� ���� ���� -> ��� ���̱� -> ���� ���� 
		 * 1-1. ���� �����̴� ������ �ִٸ� �� ������ ������ ��� �״´�.
		 * 1-2. ���� ������ ���ٰ� ���� �� ���̸� ��� ���� ��Ų��. (���� ����)
		 * 2. ���� ���� (���̰� 5�� ����� �����鸸 ����)
		 * 3. ��� �߰�
		 * 4. �ݺ�
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		food = new int[n+1][n+1];
		grounds = new ground[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
				grounds[i][j] = new ground(i, j, 5);
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			grounds[x][y].trees.add(new tree(x,y,age));
		}
		
		//���� (k�� ���� �ݺ�)
		for(int t=0; t<k; t++) {
			//��
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n ;j++) {
					ArrayList<tree> tmp = grounds[i][j].trees;
					
					if(tmp.size() > 0) {
						tmp.sort(new Comparator<tree>() {
							@Override
							public int compare(tree arg0, tree arg1) {
								return (arg0.age>arg1.age)?1:-1;
							}
						});
						int stop_index = -1;
						int plus_manure = 0;
						
						loop: for(int k=0; k<tmp.size(); k++) {
							tree tr = tmp.get(k);
							
							if(grounds[i][j].manure>=tr.age) {
								grounds[i][j].manure -= tr.age;
								tr.age++;
							} else {
								stop_index = k;
								break loop;
							}
						}
						
						//�״� ������ ���� ���
						if(stop_index != -1) {
							int count = tmp.size() - stop_index;
							
							for(int k=0; k<count; k++) {
								if(tmp.size() == 0) {
									break;
								}
								
								plus_manure += tmp.get(stop_index).age/2;
								tmp.remove(stop_index);
							}
						}
						
						//����
						grounds[i][j].manure += plus_manure;
						
						
					}
				}
			}
			
			//����
			breeding();
			//�ܿ�
			plusManure();
		}
		
		//���
		int result = 0;
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				result += grounds[i][j].trees.size();
			}
		}
		
		System.out.println(result);
	}
	static void plusManure() {
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				grounds[i][j].manure += food[i][j];
			}
		}
	}
	
	static void breeding() {
		for(int a=1; a<=n; a++) {
			for(int b=1; b<=n; b++) {
				ArrayList<tree> arr = grounds[a][b].trees;
				
				for(int i=0; i<arr.size(); i++) {
					tree tr = arr.get(i);
					
					if(tr.age != 0 && tr.age % 5 == 0) {
						for(int j=0; j<8; j++) {
							int x = tr.x + dx[j];
							int y = tr.y + dy[j];
							
							if(x>=1 && x<=n && y>=1 && y<=n) {
								grounds[x][y].trees.add(new tree(x,y,1));
							}
						}
					}
				}
			}
		}
		
	}

}

class ground {
	int x;
	int y;
	int manure;
	ArrayList<tree> trees = new ArrayList<tree>();
	
	public ground(int x,int y,int manure) {
		this.x = x;
		this.y = y;
		this.manure = manure;
	}
}

class tree {
	int x;
	int y;
	int age;
	
	public tree(int x,int y,int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}
}
