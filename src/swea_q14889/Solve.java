package swea_q14889;

import java.util.Scanner;

public class Solve {
	static int[][] map = new int[21][21];
	static boolean[] visited = new boolean[21];
	static int n, ans = Integer.MAX_VALUE;
	 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

	    n = sc.nextInt();
	 
	    for (int i = 1; i <= n; i++) {
	        for (int j = 1; j <= n; j++) {
	            map[i][j] = sc.nextInt();
	        }
	    }
	    dfs(0, 0);
	    System.out.println(ans);
	}
	 
	public static void dfs(int v, int len) {
	    if (n / 2 == len) {
	        divideTeam();
	    } else {
	        for (int i = v + 1; i <= n; i++) {
	            if (!visited[i]) {
	                visited[i] = true;
	                dfs(i, len + 1);
	            }
	        }
	    }
	    // backtracking
	    visited[v] = false;
	}
	 
	public static void divideTeam() {
	    int[] a = new int[n / 2 + 1];
	    int[] b = new int[n / 2 + 1];
	    int ai = 1, bi = 1;
	    for (int i = 1; i <= n; i++) {
	        if (visited[i]) {
	            a[ai++] = i;
	        } else {
	            b[bi++] = i;
	        }
	    }
	 
	    int aStat = getStat(a);
	    int bStat = getStat(b);
	    int diff = Math.abs(aStat - bStat);
	 
	    if (ans > diff) {
	        ans = diff;
	    }
	}
	 
	public static int getStat(int[] array) {
	    int result = 0;
	    int len = n / 2;
	 
	    for (int i = 1; i <= len; i++) {
	        for (int j = i + 1; j <= len; j++) {
	            result += map[array[i]][array[j]];
	            result += map[array[j]][array[i]];
	        }
	    }
	    return result;
	}
}
