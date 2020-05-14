package q18809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int[][] origin_map;
	static water[][] water_map;
	static water[][] water_red_map;
	static water[][] water_green_map;
	static int n,m,r,g;
	static int max_flower = 0;
	static ArrayList<edge> good_soil = new ArrayList<edge>();

	public static void main(String[] args) throws Exception {
		/*
		 * TODO 
		 * 1. 황토색 땅 중 R+G개 고른다.
		 * 2. 그 중에서 R개를 고르고 나머지는 G로 설정
		 * 3. 각각 시간 map 만들어서 bfs돌린다.
		 * 4. 배열 비교하면서 같은 시간 있는지 확인
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		origin_map = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				origin_map[i][j] = Integer.parseInt(st.nextToken());
			
				if(origin_map[i][j] == 2) {
					good_soil.add(new edge(i,j));
				}
			}
		}
		
		int[] red_comb = new int[good_soil.size()];
		Arrays.fill(red_comb, 1);
		
		for(int i=0; i<r; i++) {
			red_comb[i] = 0;
		}
		
		do {
			LinkedList<edge> tmp_good_soil = new LinkedList<edge>();
			Queue<water> waters_red = new LinkedList<water>();
			ArrayList<water> waters_arr_red = new ArrayList<water>();
			for(int i=0; i<good_soil.size(); i++) {
				tmp_good_soil.add(new edge(good_soil.get(i).x, good_soil.get(i).y));
			}
			
			for(int i=0; i<red_comb.length; i++) {
				if(red_comb[i] == 0) {
					edge tmp = tmp_good_soil.get(i);
					water tmp_tmp_water = new water(tmp.x, tmp.y, "red", 0);
					waters_red.add(tmp_tmp_water);
					waters_arr_red.add(tmp_tmp_water);
//					waters_red.add(new water(tmp.x, tmp.y, "red", 0));
//					waters_arr_red.add(new water(tmp.x, tmp.y, "red", 0));
				}
			}
			
			int remove_count = 0;
			for(int i=0; i<red_comb.length; i++) {
				if(red_comb[i] == 0) {
					tmp_good_soil.remove(i-remove_count);
					remove_count++;
				}
			}
			
			int[] green_comb = new int[tmp_good_soil.size()];
			Arrays.fill(green_comb, 1);
			
			for(int i=0; i<g; i++) {
				green_comb[i] = 0;
			}
			
			do {
				Queue<water> waters = new LinkedList<water>();
				ArrayList<water> waters_arr = new ArrayList<water>();
				water_map = new water[n][m];
				int tmp_flower = 0;

				for(int i=0; i<waters_arr_red.size(); i++) {
					water red_tmp = waters_arr_red.get(i);
					waters.add(red_tmp);
					waters_arr.add(red_tmp);
					//waters.add(new water(red_tmp.x, red_tmp.y, red_tmp.color, red_tmp.time));
					//waters_arr.add(new water(red_tmp.x, red_tmp.y, red_tmp.color, red_tmp.time));
				}
				
				for(int i=0; i<green_comb.length; i++) {
					if(green_comb[i] == 0) {
						edge tmp = tmp_good_soil.get(i);
						water tmp_water = new water(tmp.x, tmp.y, "green", 0);
						waters.add(tmp_water);
						waters_arr.add(tmp_water);
						//waters.add(new water(tmp.x, tmp.y, "green", 0));
						//waters_arr.add(new water(tmp.x, tmp.y, "green", 0));
					}
				}
				
				for(int i=0; i<waters.size(); i++) {
					water tmp2 = waters_arr.get(i);
					water_map[tmp2.x][tmp2.y] = tmp2;
					//water_map[tmp2.x][tmp2.y] = new water(tmp2.x, tmp2.y, tmp2.color, tmp2.time);
				}
				
				while(true) {
					int size = waters.size();
					
					if(size == 0) {
						break;
					}
					
					for(int i=0; i<size; i++) {
						water v = waters.poll();
						
						if(v.flower) {
							continue;
						}
						
						for(int j=0; j<4; j++) {
							int x = v.x + dx[j];
							int y = v.y + dy[j];
							
							if(x>=0 && x<n && y>=0 && y<m && origin_map[x][y] != 0) {
								if(water_map[x][y] == null) {
									water tmp = new water(x, y, water_map[v.x][v.y].color, water_map[v.x][v.y].time + 1);
									waters.add(tmp);
									water_map[x][y] = tmp;
								} else {
									water tmp = new water(x, y, water_map[v.x][v.y].color, water_map[v.x][v.y].time + 1);
									
									if(!tmp.color.equals(water_map[x][y].color)) {
										if(tmp.time == water_map[x][y].time && !water_map[x][y].flower) {
											water_map[x][y].flower = true;
											tmp_flower++;
										}
									}
								}
							}
						}
					}
					
				}
				
				max_flower = Math.max(max_flower, tmp_flower);
				
			} while(next_permutation(green_comb));
			
			
			
		} while(next_permutation(red_comb));
		
		
		System.out.println(max_flower);
		
	}
	
	static boolean check(water[][] water_map) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(origin_map[i][j] != 0 && water_map[i][j] == null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		int j = arr.length - 1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length - 1;
		
		while(i<j) {
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
		
		return true;
	}

}

class water {
	int x;
	int y;
	String color;
	int time;
	boolean flower = false;
	
	public water(int x, int y, String color, int time) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.time = time;
	}
}

class edge {
	int x;
	int y;
	
	public edge(int x,int y) {
		this.x = x;
		this.y = y;
	}
}
