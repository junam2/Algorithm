package q6593;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //static 변수와 이동에 대한 변수인 m (move)을 설정해준다.
	static int L;
	static int R;
	static int C;
	static char[][][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int m = 0;
    static int end_i, end_j, end_k;
    static int my_end_i = -1; 
    static int my_end_j = -1;
    static int my_end_k = -1;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		while(true) {
			int start_i = 0;
			int start_j = 0;
			int start_k = 0;
			String str = br.readLine();
			if(str.length() == 0) str = br.readLine();
			String[] LRC = str.split(" ");
			L = Integer.parseInt(LRC[0]);
			R = Integer.parseInt(LRC[1]);
			C = Integer.parseInt(LRC[2]);
			
			if(L==0 && R==0 && C==0) {
				break;
			}

			map = new char[L][R][C];
			visited = new boolean[L][R][C];

			for(int k=0; k<map.length; k++) {
				for(int i=0; i<map[0].length; i++) {
					char[] arr = br.readLine().toCharArray();
					if(arr.length == 0) arr = br.readLine().toCharArray();
					for(int j=0; j<map[0][0].length; j++) {
						map[k][i][j] = arr[j];
                        //'S'이면 시작점을 정해준다. 입력을 해주면서 설정해야 불필요한 연산이 없다.
						if(map[k][i][j] == 'S') {
							start_i = k;
							start_j = i;
							start_k = j;
						} else if(map[k][i][j] == 'E') {
                            end_i = k;
                            end_j = i;
                            end_k = j;
                        }
					}
				}
			}
			
			bfs(new dot(start_i, start_j,start_k));
	
            
            if(my_end_i == end_i && my_end_j == end_j && my_end_k == end_k) {
                System.out.println("Escaped in "+ (m-1) +" minute(s).");
            } else {
                System.out.println("Trapped!");
            }
            
            my_end_i = 0;
            my_end_j = 0;
            my_end_k = 0;
			m = 0;
		}

	}
	
	static void bfs(dot d) {
		Queue<dot> q = new LinkedList<dot>();
		q.add(d);
		visited[d.i][d.j][d.k] = true;
		
		loop: while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				dot t = q.remove();
				
				if(map[t.i][t.j][t.k] == 'E') {
                    m++;
                    my_end_i = t.i;
                    my_end_j = t.j;
                    my_end_k = t.k;
					break loop;
				}
				
				for(int j=0; j<6; j++) {
					int i2 = t.i + dz[j];
					int j2 = t.j + dx[j];
					int k2 = t.k + dy[j];
					
					if(i2>=0 && i2<L && j2>=0 && j2<R && k2>=0 && k2<C &&
							map[i2][j2][k2] != '#' && !visited[i2][j2][k2]) {
						q.add(new dot(i2,j2,k2));
						visited[i2][j2][k2] = true;
					}
					
				}
			}
			m++;
		}
	}

}

class dot {
	int i;
	int j;
	int k;
	
	public dot(int i,int j, int k) {
		this.i = i;
		this.j = j;
		this.k = k;
	}
}
