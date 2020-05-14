package q10775;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * dfs·Î ÇØº¸·Á´Ù°¡ Å»Å» ÅÐ¸² ¤¾
	 */
	
	
	static int g,p;
	static int[] gates;
	static int[] airplanes;
	static int res = 0;
	static int maxRes = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		g = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
	
		gates = new int[g];
		airplanes = new int[p];
		
		for(int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine());
			int airplane = Integer.parseInt(st.nextToken());
			airplanes[i] = airplane;
		}
		
		dfs(0, 0);
		
		System.out.println(maxRes);
	}
	
	static void dfs(int index, int res) {
		if(index < 0 || res < 0) {
			return;
		}
		
		if(index >= g) {
			return;
		}
		
		
		for(int i=index; i<airplanes.length; i++) {
			for(int j=0; j<airplanes[i]; j++) {
				if(gates[j] == -1) continue;
				gates[j] = -1;
				dfs(index++, res++);
				gates[j] = 0;
				dfs(index--, res--);
			}
			
			if(i == airplanes.length-1) {
				if(maxRes < res) {
					maxRes = res;
				}
			}
		}
	}
}
