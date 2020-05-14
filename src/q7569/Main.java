package q7569;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[] dz = {0,0,0,0,1,-1};
	static int[] dx = {-1,1,0,0,0,0};
	static int[] dy = {0,0,-1,1,0,0};
 	static int x,y,z;
	static int[][][] map;
	static boolean[][][] visited;
	static Queue<dot> tomato = new LinkedList<dot>();
	static int day = 0;
	static boolean flag = true;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		
		y = Integer.parseInt(str[0]);
		x = Integer.parseInt(str[1]);
		z = Integer.parseInt(str[2]);
		
		map = new int[z][x][y];
		visited = new boolean[z][x][y];
		
		for(int i=0; i<z; i++) {
			for(int j=0; j<x; j++) {
				str = br.readLine().split(" ");
				for(int k=0; k<y; k++) {
					map[i][j][k] = Integer.parseInt(str[k]);
					
					if(map[i][j][k] == 1) {
						tomato.add(new dot(j,k,i));
					}
				}
			}
		}
		while(true) {		
			bfs(tomato);
			if(!flag) {
				break;
			}
			day++;
		}
		check();
	}
	
	static void bfs(Queue<dot> tomato) {
		int size = tomato.size();
		
		for(int i=0; i<size; i++) {
			dot d = tomato.poll();
			int x1 = d.x;
			int y1 = d.y;
			int z1 = d.z;
			
			for(int j=0; j<6; j++) {
				int x2 = x1 + dx[j];
				int y2 = y1 + dy[j];
				int z2 = z1 + dz[j];
				
				if(z2>=0 && z2<z && x2>=0 && x2<x && y2>=0 && y2<y && map[z2][x2][y2] == 0) {
					map[z2][x2][y2] = 1;
					tomato.add(new dot(x2,y2,z2));
				}
			}
		}
		
		if(tomato.size() == 0) {
			flag = false;
			return;
		}
	}
	
	public static void check() {
		for(int i=0; i<z; i++) {
			for(int j=0; j<x; j++) {
				for(int k=0; k<y; k++) {
					if(map[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(day);
	}
	
	public static void print() {
		for(int i=0; i<z; i++) {
			for(int j=0; j<x; j++) {
				for(int k=0; k<y; k++) {
						System.out.print(map[i][j][k] + " ");
				}
				System.out.println();
			}
		}
	}

}

class dot {
	int x;
	int y;
	int z;
	
	public dot(int x,int y,int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
