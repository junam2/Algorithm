package swea_q15685;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	//x°¡ °¡·Î y°¡ ¼¼·Î
	static int[][] map = new int[101][101];
	static int square_count = 0;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		for(int t=0; t<n; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int type = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());
			
			dragonCurve(x,y,type,gen);
		}
		
		
		checkSquare();
		
		System.out.println(square_count);
	}
	
	/*
	 * type
	 * 0: xÁÂÇ¥ Áõ°¡ (¿ì) 
	 * 1: yÁÂÇ¥ °¨¼Ò (»ó)
	 * 2: xÁÂÇ¥ °¨¼Ò (ÁÂ)
	 * 3: yÁÂÇ¥ Áõ°¡ (ÇÏ)
	 */
	public static void dragonCurve(int x,int y, int type, int gen) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		int tmp_type = type;
		arr.add(tmp_type);
		for(int i=1; i<=gen; i++) {
			for(int j=arr.size()-1; j>=0; j--) {
				tmp_type = (arr.get(j) + 1)%4;
				arr.add(tmp_type);
			}
		}
		
		int tmp_x = x;
		int tmp_y = y;
		
		for(int i=0; i<arr.size(); i++) {
			map[tmp_y][tmp_x] = 1;
			tmp_x += dx[arr.get(i)];
			tmp_y += dy[arr.get(i)];
		}
		
		map[tmp_y][tmp_x] = 1;
	}
	
	public static void checkSquare() {
		int[] dx = {1,0,1};
		int[] dy = {0,1,1};
		int check_count = 0;
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == 1) {
					for(int k=0; k<3; k++) {
						int tmp_i = i + dy[k];
						int tmp_j = j + dx[k];
						
						if(tmp_i>=0 && tmp_i<101 && tmp_j>=0 && tmp_j<101 && map[tmp_i][tmp_j] == 1) {
							check_count++;
						}
						
					}
					if(check_count == 3) {
						square_count++;
					}
					
					check_count = 0;
				}
			}
		}
	}
	
	public static void show() {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

