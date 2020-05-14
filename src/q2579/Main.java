package q2579;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] D = new int[n+1][3];
		int[] arr = new int[n+1];
		
		for(int i=1; i<arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if(n==1) {
			System.out.println(arr[1]);
			System.exit(0);
		}
		
		D[1][1] = arr[1]; D[1][2] = 0;
		D[2][1] = arr[2]; D[2][2] = arr[1] + arr[2];
		
		for(int i=3; i<D.length; i++) {
			D[i][1] = Math.max(D[i-2][1], D[i-2][2]) + arr[i];
			D[i][2] = D[i-1][1] + arr[i];
		}
		
		System.out.println(Math.max(D[n][1], D[n][2]));
		
	}

}
