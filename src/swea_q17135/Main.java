package swea_q17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int n,m,d;
	static int[][] map;
	static int res = 0;
	static int max_res = Integer.MIN_VALUE;
	static archer[] archers = new archer[3];
	static ArrayList<enemy> enemys = new ArrayList<enemy>();
	static ArrayList<enemy> origin_enemys = new ArrayList<enemy>();
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 궁수 3명 배치 (조합)
		 * 2. 시나리오 진행
		 * 3. 최솟값 찾기
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					origin_enemys.add(new enemy(i,j));
				}
			}
		}
		
		int[] comb = new int[m];
		Arrays.fill(comb, 1);
		
		Collections.sort(origin_enemys, new Comparator<enemy>() {

			@Override
			public int compare(enemy arg0, enemy arg1) {
				if(arg0.x > arg1.x) {
					return -1;
				} else if(arg0.x < arg1.x) {
					return 1;
				} else {
					if(arg0.y < arg1.y) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		
		for(int i=0; i<3; i++) {
			comb[i] = 0;
		}
		
		do {
			enemys = new ArrayList<enemy>();
			int index = 0;
			res = 0;
			
			//comb = new int[] {0,1,0,1,0};
			
			
			for(int i=0; i<origin_enemys.size(); i++) {
				enemy t = origin_enemys.get(i);
				enemys.add(new enemy(t.x, t.y));
			}
			
			for(int i=0; i<comb.length; i++) {
				if(comb[i] == 0) {
					map[n][i] = 2;
					archers[index] = new archer(n,i,d);
					index++;
				}
			}
			
			
			loop: for(int q=0; q<n; q++) {
				for(int k=0; k<3; k++) {
					archer tmp = archers[k];
					
					int[] enemy_distance = new int[enemys.size()];
					int tmp_min = Integer.MAX_VALUE;
					
					for(int j=0; j<enemys.size(); j++) {
						
						if(enemys.get(j).isDeath) {
							continue;
						}
						
						int distance = Math.abs(tmp.x - enemys.get(j).x) + Math.abs(tmp.y - enemys.get(j).y);
						
						
						if(distance > d) {
							continue;
						}
						
						enemy_distance[j] = distance;
						
						
						if(distance <= d && !enemys.get(j).isDeath) {
							if(tmp_min > distance) {
								tmp_min = distance;
							}
						}
					}
					
					ArrayList<enemy> distance_arr = new ArrayList<enemy>();
					
					for(int j=0; j<enemys.size(); j++) {
						if(tmp_min == enemy_distance[j]) {
							distance_arr.add(enemys.get(j));
						}
					}
					
					Collections.sort(distance_arr, new Comparator<enemy>() {

						@Override
						public int compare(enemy arg0, enemy arg1) {
							if(arg0.y > arg1.y) {
								return 1;
							} else if(arg0.y < arg1.y){
								return -1;
							} else {
								if(arg0.x > arg1.x) {
									return 1;
								} else {
									return -1;
								}
							}
						}
					});
					
					if(distance_arr.size() != 0) {
						enemy attack = distance_arr.get(0);
						
						if(!attack.preDeath) {
							res++;
							attack.preDeath = true;
						}
					}
					
				}
				
				for(int j=0; j<enemys.size(); j++) {
					enemy tmptmp = enemys.get(j);
					
					if(tmptmp.preDeath) {
						tmptmp.isDeath = true;
					}
					
					tmptmp.x++;
					
					if(tmptmp.x >= n){
						tmptmp.isDeath = true;
					}
					
				}
				boolean flag = true;
				loop3: for(int j=0; j<enemys.size(); j++) {
					if(!enemys.get(j).isDeath) {
						break loop3;
					}
					
					if(j == enemys.size() - 1) {
						flag = false;
					}
				}
				
				if(!flag) {
					break loop;
				}
				
			}
			
			if(max_res < res) {
				max_res = res;
			}
			
		} while(next_permutation(comb));
		
		System.out.println(max_res);
		
		
	}

	static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		int j = arr.length-1;
		
		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length -1;
		
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

class archer {
	int x;
	int y;
	int d;
	
	public archer(int x,int y,int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

class enemy {
	int x;
	int y;
	boolean isDeath = false;
	boolean preDeath = false;
	
	public enemy(int x,int y) {
		this.x = x;
		this.y = y;
	}


}