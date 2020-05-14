package swea_q14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map;
	static int n;
	static ArrayList<int[]> total_team = new ArrayList<int[]>();
	static ArrayList<int[]> team1 = new ArrayList<int[]>();
	static ArrayList<int[]> team2 = new ArrayList<int[]>();
	static ArrayList<Integer> sum = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] tmp_team = new int[n/2];
		doCombination(tmp_team, 0, n, n/2, 0);
		
		for(int i=0; i<total_team.size()/2; i++) {
			team1.add(total_team.get(i));
			team2.add(total_team.get((total_team.size()-1)-i));
		}
		
		for(int i=0; i<team1.size(); i++) {
			/*
			 * team1, team2 permutation 구한다.
			 * 각각 배열의 합을 구해서 차이를 새로운 배열에 넣어준다.
			 */
			ArrayList<int[]> team1_permu = new ArrayList<int[]>();
			ArrayList<int[]> team2_permu = new ArrayList<int[]>();
			
			doPermutation(team1_permu, team1.get(i), 0, n/2, 2);
			doPermutation(team2_permu, team2.get(i), 0, n/2, 2);

			int team1_score = scoreSum(team1_permu);
			int team2_score = scoreSum(team2_permu);
			
			sum.add(Math.abs(team1_score - team2_score));
		}
		
		System.out.println(Collections.min(sum));
		
	}
	
	public static void doCombination(int[] tmp_team, int index, int n, int r, int target) {
		if(r == 0) {
			int[] tmp = new int[n/2];
			
			for(int i=0; i<n/2; i++) {
				tmp[i] = tmp_team[i];
			}
			total_team.add(tmp);
		} else if(target == n) {
			return;
		} else {
			tmp_team[index] = target;
			
			doCombination(tmp_team, index+1, n, r-1, target+1);
			doCombination(tmp_team, index, n, r, target+1);
		}
	}
	
	public static void doPermutation(ArrayList<int[]> tmp, int[] arr, int depth, int n, int k) {
		if(depth == k) {
			int[] tmp_arr = new int[k];
			for(int i=0; i<k; i++) {
				tmp_arr[i] = arr[i];
			}
			
			tmp.add(tmp_arr);
			return;
		}
		
		for(int i=depth; i<n; i++) {
			swap(arr, i, depth);
			doPermutation(tmp, arr, depth+1, n, k);
			swap(arr, i, depth);
		}
		
	}
	
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static int scoreSum(ArrayList<int[]> arr) {
		int sum = 0;
		
		for(int i=0; i<arr.size(); i++) {
			sum += map[arr.get(i)[0]][arr.get(i)[1]];
		}
		
		return sum;
	}
}
