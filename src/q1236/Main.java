package q1236;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;
	static int n,m;
	static String[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		
		map = new String[n][m];
		
		for(int i=0; i<n; i++) {
			str = br.readLine().split("");
			for(int j=0; j<m; j++) {
				map[i][j] = str[j];
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!map[i][j].equals("X")) {
					cal(i,j,0);
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!map[i][j].equals("X")) {
					cal(i,j,1);
				}
			}
		}
		
		System.out.println(count);
		
	}
	
	public static void cal(int a, int b, int time) {
		boolean flag1 = true;
		boolean flag2 = true;
		
		if(time == 0) {	
			for(int i=0; i<n; i++) {
				if(map[i][b].equals("X")) {
					flag1 = false;
				}
			}
			
			for(int i=0; i<m; i++) {
				if(map[a][i].equals("X")) {
					flag2 = false;
				}
			}
			
			if(flag1 && flag2) {		
				map[a][b] = "X";
				count++;
			}
		}
		
		if(time == 1) {		
			for(int i=0; i<n; i++) {
				if(map[i][b].equals("X")) {
					flag1 = false;
				}
			}
			
			if(flag1) {
				map[a][b] = "X";
				count++;
			}
			
			for(int i=0; i<m; i++) {
				if(map[a][i].equals("X")) {
					flag2 = false;
				}
			}
			
			if(flag2) {		
				map[a][b] = "X";
				count++;
			}
		}
		

	}
}