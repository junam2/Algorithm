package swea_q14891;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static Gear[] Gears;
	static boolean[] visited;
	static int[] score = {1,2,4,8};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Gears = new Gear[4];
		for(int i=0; i<4; i++) {
			String[] str = br.readLine().split("");
			int[] tooth = new int[8];
			for(int j=0; j<str.length; j++) {
				tooth[j] = Integer.parseInt(str[j]);
			}
			
			Gears[i] = new Gear(i, tooth);
		}
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String[] str = br.readLine().split(" ");
			int num = Integer.parseInt(str[0]) - 1;
			int dir = Integer.parseInt(str[1]);
			visited = new boolean[4];
			start(num, dir);
		}
		int total = 0;
		for(int i=0; i<4; i++) {
			if(Gears[i].tooth[0] == 1) {
				total += score[i];
			}
		}
		
		System.out.println(total);
		
	}
	//2번, 6번 확인
	public static void start(int num, int dir) {
		Gear g = Gears[num];
		int g_right = g.tooth[2];
		int g_left = g.tooth[6];
		rotate(num, dir);
		visited[num] = true;
		switch(num) {
		case 0:
			if(Gears[num+1].tooth[6] != g_right && !visited[num+1]) {
				start(num+1, dir*-1);
			}
			break;
		case 1:
			if(Gears[num-1].tooth[2] != g_left && !visited[num-1]) {
				start(num-1, dir*-1);
			}
			
			if(Gears[num+1].tooth[6] != g_right && !visited[num+1]) {
				start(num+1, dir*-1);
			}
			break;
		case 2:
			if(Gears[num-1].tooth[2] != g_left && !visited[num-1]) {
				start(num-1, dir*-1);
			}
			
			if(Gears[num+1].tooth[6] != g_right && !visited[num+1]) {
				start(num+1, dir*-1);
			}
			break;
		case 3:
			if(Gears[num-1].tooth[2] != g_left && !visited[num-1]) {
				start(num-1, dir*-1);
			}
			break;
		}
		
	}
	
	public static void rotate(int num, int dir) {
		Gear g = Gears[num];
		
		if(dir == 1) {
			int tmp = g.tooth[7];
			for(int i=g.tooth.length-1; i>0; i--) {
				g.tooth[i] = g.tooth[i-1];
			}
			g.tooth[0] = tmp;
		} else {
			int tmp = g.tooth[0];
			for(int i=0; i<g.tooth.length-1; i++) {
				g.tooth[i] = g.tooth[i+1];
			}
			g.tooth[7] = tmp;
		}
	}

}

class Gear {
	int[] tooth = new int[8];
	int num;
	
	public Gear(int num, int[] tooth) {
		this.num = num;
		this.tooth = tooth;
	}
}