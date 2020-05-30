package q18808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m,k;
	static int[][] map;
	static int[][] sticker;
	static boolean flag;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 스티커 부착 시작
		 * 2. 만약 스티커의 크기가 현재 위치에서 맞다면 (시작은 0,0) 붙일 수 있나 확인
		 * 3. 가능하다면 붙이고 다음 스티커로 넘어감 / 아니라면 3번 회전
		 * 4. 회전해서도 안되면 버리고 다음 스티커
		 * 5. 다 끝난 후 스티커가 붙은 공간 확인
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int s=0; s<k; s++) {
			st = new StringTokenizer(br.readLine());
			
			int nn = Integer.parseInt(st.nextToken());
			int mm = Integer.parseInt(st.nextToken());
			
			sticker = new int[nn][mm];
			
			for(int i=0; i<nn; i++) {
				st = new  StringTokenizer(br.readLine());
				for(int j=0; j<mm; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
				
			}
			
			int count = 1;
			flag = false;
			
			loop: while(count <= 4 && !flag) {
				nn = sticker.length;
				mm = sticker[0].length;
				
				for(int i=0; i<n; i++) {
					for(int j=0; j<m; j++) {
						int endX = i + nn - 1;
						int endY = j + mm - 1;
						int x = 0;
						int y = 0;
						boolean flag2 = true;
						if(endX>=n || endY>=m) continue;
												
						loop2: for(int k=i; k<=endX; k++) {
							for(int l=j; l<=endY; l++) {
								if(sticker[x][y] == 1) {
									if(map[k][l] == 1) {
										flag2 = false;
										break loop2;
									}
								}
								y++;
							}
							x++;
							y = 0;
						}
						
						//i,j에 k,l 스티커 붙이는게 가능하면
						if(flag2) {
							flag = true;
							x=0; y=0;
							for(int k=i; k<=endX; k++) {
								for(int l=j; l<=endY; l++) {
									if(sticker[x][y] == 1) {
										map[k][l] = sticker[x][y];
									}
									y++;
								}
								x++;
								y = 0;
							}					
							break loop;
						}
					}
				}
				
				//스티커 회전
				if(!flag) {
					rotate();
					
				}
				count++;
				
				if(count > 4) {
					break loop;
				}
				
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 1) {
					result++;
				}
			}
		}
		System.out.println(result);
	}
	
	static void rotate() {
		int[][] rotateSticker = new int[sticker[0].length][sticker.length];
		
		for(int i=0; i<sticker[0].length; i++) {
			int a = 0;
			for(int j=sticker.length-1; j>=0; j--) {
				rotateSticker[i][a] = sticker[j][i];
				a++;
			}
		}
		
		sticker = rotateSticker;
	}
}
