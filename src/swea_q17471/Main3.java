package swea_q17471;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
	static int N;
	static int[] people;
	static int[][] map;
	static int total_people;
	static boolean[] region;
	static boolean[] visited;
	static int connect_count;
	static int connect_count2;
	static int min_count = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		/*
		 * 1. 1~N/2개 까지 선거구 선택
		 * 2. dfs사용해서 연결되었는지 확인
		 * 3. 연결되어있다면, 인구 수 차이 비교.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		people = new int[N];
		map = new int[N][N];
		
		for(int i=0; i<people.length; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			total_people += people[i];
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<k; j++) {
				int c = Integer.parseInt(st.nextToken());
				map[i][c-1] = 1;
				map[c-1][i] = 1;
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		int region1 = 1;
		
		while(region1 <= N/2) {
			int[] comb = new int[N];
			Arrays.fill(comb, 1);
			
			for(int i=0; i<region1; i++) {
				comb[i] = 0;
			}
			
			do {
				region = new boolean[N];
				visited = new boolean[N];
				connect_count = 1;
				connect_count2 = 1;
				int tmp_people = 0;
				int tmp_count = 0;
				
				for(int i=0; i<N; i++) {
					if(comb[i] == 0) {
						region[i] = true;
					}
				}
			
				
				dfs(0);
				
				for(int i=0; i<people.length; i++) {
					if(region[i] == region[0]) {
						tmp_people += people[i];
						tmp_count++;
					}
				}
				
				loop: for(int i=1; i<N; i++) {
					if(region[0] != region[i]) {
						dfs2(i);
						break loop;
					}
				}
				
				if(tmp_count == connect_count && (N-tmp_count) == connect_count2) {
					int diff = Math.abs(total_people - 2*tmp_people);
					
					if(min_count > diff) {
						min_count = diff;
					}
				}
				
				
			} while(next_permutation(comb));
			
			region1++;
		}
		
		if(min_count == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min_count);
		}
		
	}
	
	static void dfs(int r) {
		boolean flag = region[r];
		visited[r] = true;
		
		for(int i=0; i<N; i++) {
			if(region[i] == flag && map[r][i] == 1 && !visited[i]) {
				connect_count++;
				dfs(i);
			}
		}
	}
	
	static void dfs2(int r) {
		boolean flag = region[r];
		visited[r] = true;
		
		for(int i=0; i<N; i++) {
			if(region[i] == flag && map[r][i] == 1 && !visited[i]) {
				connect_count2++;
				dfs2(i);
			}
		}
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
