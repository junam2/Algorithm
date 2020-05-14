package swea_q17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static int[][] region;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<n; j++) {
//				setXY(i,j);
//			}
//		}
		
		setXY(1,3);
	}
	
	static void setXY(int x, int y) {
//		for(int i=1; i<n; i++) {
//			for(int j=1; j<n; j++) {
//				start(x,y,i,j);
//			}
//		}
		
		start(x,y,2,2);
	}
	
	static void start(int x,int y,int d1,int d2) {
		if(!confirm(x,y,d1,d2)) {
			return;
		}
		
		region = new int[n][n];
		
		for(int i=0; i<n; i++) {
			Arrays.fill(region[i], 5);
		}
		
		int x1_start = 0; int x1_end = x+d1;
		int y1_start = 0; int y1_end = y;
		int x2_start = 0; int x2_end = x+d2;
		int y2_start = y+1; int y2_end = n-1;
		int x3_start = x+d1; int x3_end = n-1;
		int y3_start = 0; int y3_end = y-d1+d2;
		int x4_start = x+d2+1; int x4_end = n-1;
		int y4_start = y-d1+d2; int y4_end = n-1;
		
//		if(
//		confirm2(x1_start, x1_end-1) &&
//		confirm2(y1_start, y1_end) &&
//		confirm2(x2_start, x2_end) &&
//		confirm2(y2_start+1, y2_end) &&
//		confirm2(x3_start, x3_end) &&
//		confirm2(y3_start, y3_end-1) &&
//		confirm2(x4_start+1, x4_end) && 
//		confirm2(y4_start, y4_end) 
//		) {
//			return;
//		}
		
		for(int i=x1_start; i<x1_end-1; i++) {
			for(int j=y1_start; j<=y1_end-1; j++) {
				region[i][j] = 1;
			}
		}
		
		for(int i=x2_start; i<=x2_end-1; i++) {
			for(int j=y2_start; j<=y2_end-1; j++) {
				region[i][j] = 2;
			}
		}
		
		for(int i=x3_start; i<=x3_end-1; i++) {
			for(int j=y3_start; j<y3_end-1; j++) {
				region[i][j] = 3;
			}
		}
		
		for(int i=x4_start; i<=x4_end-1; i++) {
			for(int j=y4_start; j<=y4_end-1; j++) {
				region[i][j] = 4;
			}
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(region[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static boolean confirm2(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=n) {
			return false;
		}
		
		return true;
	}
	
	static boolean confirm(int x,int y,int d1,int d2) {
		if(x+d1+d2 > n || y-d1 < 0 || y+d2 > n) {
			return false;
		}
		
		return true;
	}
}
