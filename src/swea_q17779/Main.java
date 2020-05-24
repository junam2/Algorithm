package swea_q17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int x,y,d1,d2;
	static int[][] people;
	static int[][] region;
	static boolean[][] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		people = new int[n+1][n+1];
		region = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];
		
		//인구수 및 지역구 default 5로 설정
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
				region[i][j] = 5;
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				
				for(int d1=1; d1<=n; d1++) {
					for(int d2=1; d2<=n; d2++) {
						if(!check(i,j,d1,d2)) {
							break;
						}
						
						for(int k=1; k<n+1; k++) {
							Arrays.fill(visited[k], false);
							Arrays.fill(region[k], 5);
						}
						
						//경계선 정하기
						int l1=0;
						for(int k=i; k<=i+d1; k++) {
							if(l1>d1) break;
							
							visited[k][j-l1] = true;
							l1++;
						}
						
						int l2=0;
						for(int k=i; k<=i+d2; k++) {
							if(l2>d2) break;
							visited[k][j+l2] = true;
							l2++;
						}			
						
						int l3=0;
						for(int k=i+d1; k<=i+d1+d2; k++) {
							if(l3>d2) break;
							visited[k][j-d1+l3] = true;
							l3++;
						}	
						
						int l4=0;
						for(int k=i+d2; k<=i+d2+d1; k++) {
							if(l4>d1) break;
							visited[k][j+d2-l4] = true;
							l4++;
						}
						
						//내부도 visited 체크하기
						for(int k=i+1; k<=i+d1+d2-1; k++) {
							int count = 0;
							for(int l=1; l<=n; l++) {
								if(visited[k][l]) {
									count++;
								}
								
								if(count == 2) {
									break;
								}
								
								if(count == 1) {					
									visited[k][l] = true;
								}
							}
						}
						
						//1~4번 선거구 번호 체크하기 (범위 중 visited 아닌 경우)
						for(int k=1; k<=n; k++) {
							for(int l=1; l<=n; l++) {
								if(!visited[k][l]) {
									int tmp = getRegion(i,j,k,l,d1,d2);
									region[k][l] = tmp;
								}
							}
						}
						
						//인구수 찾기
						int[] total_region_people = new int[6];
						
						for(int k=1; k<=n; k++) {
							for(int l=1; l<=n; l++) {
								total_region_people[region[k][l]] += people[k][l];
							}
						}
						Arrays.sort(total_region_people);					
						int tmp2 = total_region_people[5] - total_region_people[1];
						result = Math.min(result, tmp2);
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	static int getRegion(int x,int y,int r,int c,int d1,int d2) {
		
		if(r>=1 && r<x+d1 && c>=1 && c<=y) {
			return 1;
		} else if(r>=1 && r<=x+d2 && c>y && c<=n) {
			return 2;
		} else if(r>=x+d1 && r<=n && c>=1 && c<y-d1+d2) {
			return 3;
		} else if(r>x+d2 && r<=n && c>=y-d1+d2 && c<=n) {
			return 4;
		}
		
		return 5;
	}
	
	static boolean check(int x,int y,int d1,int d2) {
		if(x+d1+d2>n || y-d1<1 || y+d2>n) {
			return false;
		}
		
		return true;
	}
}
