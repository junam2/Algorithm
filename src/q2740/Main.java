package q2740;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int[][] map1 = new int[a][b];
		
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				map1[i][j] = sc.nextInt();
			}
		}
		
		b = sc.nextInt();
		int c = sc.nextInt();
		
		int[][] map2 = new int[b][c];
		
		for(int i=0; i<b; i++) {
			for(int j=0; j<c; j++) {
				map2[i][j] = sc.nextInt();
			}
		}
		
		int[][] result = new int[a][c];
		
		for(int i=0; i<a; i++) {
			for(int j=0; j<c; j++) {
				for(int k=0; k<b; k++) {
					result[i][j] += map1[i][k]*map2[k][j];
				}
			}
		}
		
		
		for(int i=0; i<a; i++) {
			for(int j=0; j<c; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}

}
