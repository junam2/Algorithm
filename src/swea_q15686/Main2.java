package swea_q15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<house> houses = new ArrayList<house>();
	static ArrayList<edge> chicks = new ArrayList<edge>();
	static int total_chick = 0;
	static int total_min_distance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) {
					houses.add(new house(i,j));
				} else if(map[i][j] == 2) {
					chicks.add(new edge(i,j));
					total_chick++;
				}
			}
		}
		
		int[] comb = new int[total_chick];
		Arrays.fill(comb, 1);
		
		for(int i=0; i<m; i++) {
			comb[i] = 0;
		}
		
		do {
			ArrayList<edge> selected_chicks = new ArrayList<edge>();
			int tmp_min_distance = 0;;
			
			for(int i=0; i<comb.length; i++) {
				if(comb[i] == 0) {
					selected_chicks.add(chicks.get(i));
				}
			}
			
			for(int i=0; i<houses.size(); i++) {
				int min_distance = Integer.MAX_VALUE;
				for(int j=0; j<selected_chicks.size(); j++) {
					int tmp_distance = Math.abs(houses.get(i).x - selected_chicks.get(j).x) + Math.abs(houses.get(i).y - selected_chicks.get(j).y);
				
					if(min_distance > tmp_distance) {
						min_distance = tmp_distance;
					}
				}
				tmp_min_distance += min_distance;
			}
			
			if(total_min_distance > tmp_min_distance) {
				total_min_distance = tmp_min_distance;
			}
			
		} while(next_permutation(comb));
		
		System.out.println(total_min_distance);
	}
	
	static boolean next_permutation(int[] arr) {
		int i = arr.length - 1;
		
		while(i>0 && arr[i-1]>=arr[i]) {
			i--;
		}
		
		if(i<=0) return false;
		
		int j = arr.length - 1;
		
		while(arr[i-1]>=arr[j]) {
			j--;
		}
		
		int temp = arr[i-1];
		arr[i-1] = arr[j];
		arr[j] = temp;
		
		j = arr.length-1;
		
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

class house {
	int x;
	int y;
	int chick_distance = 0;
	
	public house(int x,int y) {
		this.x = x;
		this.y = y;
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
