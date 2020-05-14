package swea_q14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static boolean visited[][];
	static int wall = 3;
	static int x,y;
	static ArrayList<int[]> comb_arr = new ArrayList<int[]>();
	static ArrayList<Integer> secure_area = new ArrayList<Integer>(); 
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		map = new int[x][y];
		visited = new boolean[x][y];
		
		for(int i=0; i<x; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<y; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int tmp_zero_index = 0;
		int zero_count = 0;
		ArrayList<Integer> zero_index = new ArrayList<Integer>(); 
		
		//map을 돌면서 0인것의 번호 찾기.-> 인덱스는 번호/배열의 길이 번호%배열의 길이로 좌표 구한다.
		for(int i=0; i<x; i++) {
			for(int j=0; j<y; j++) {
				if(map[i][j] == 0) {
					zero_index.add(tmp_zero_index);
					zero_count++;
				}
				tmp_zero_index++;
			}
		}
		
		//0인 것의 번호 중 앞에서부터 3개 뽑기 (조합)
		//-1을 넣어서 마지막꺼 까지 들어갈 수 있게 만들었다. 후에 사이즈에 신경써야한다.
		int[] arr = new int[wall];
		zero_index.add(-1);
		doCombination(arr, 0, -1, 3, zero_index, 0);
		
		for(int i=0; i<comb_arr.size(); i++) {
			int[][] map_clone = deepCopy(map);
			
			for(int j=0; j<wall; j++) {
				int tmp_x = comb_arr.get(i)[j] / y;
				int tmp_y = comb_arr.get(i)[j] % y;
				map_clone[tmp_x][tmp_y] = 1;
			}
						
			for(int a=0; a<x; a++) {
				for(int b=0; b<y; b++) {
					if(!visited[a][b] && map_clone[a][b] == 2) {
						bfs(map_clone, a, b);
					}
				}
			}
			
			secure_area.add(countZero(map_clone));
			for(int k=0; k<x; k++) {
				Arrays.fill(visited[k], false);
			}
		}
		
		System.out.println(Collections.max(secure_area));
	}
	
	public static void show(int[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
    public static void doCombination(int[] arr, int index, int n, int r, ArrayList<Integer> zero_arr , int target){
         
        if(r == 0){
        	int[] tmp = new int[wall];
        	for(int i=0; i<wall; i++) {
        		tmp[i] = arr[i];
        	}
        	comb_arr.add(tmp);
        } else if(zero_arr.get(target) == n){ 
            return;
        } else{
            arr[index] = zero_arr.get(target);
  
            doCombination(arr, index+1, n, r-1, zero_arr, target+1);
            doCombination(arr, index, n, r, zero_arr, target+1);
        }
    }
    
    public static void bfs(int[][] arr, int a , int b) {
    	Queue<dot> q = new LinkedList<dot>();
    	visited[a][b] = true;
    	q.add(new dot(a,b));
    	
    	while(!q.isEmpty()) {
    		dot t = q.remove();
    		int x1 = t.x;
    		int y1 = t.y;
    		
    		for(int i=0; i<4; i++) {
    			int x2 = x1 + dx[i];
    			int y2 = y1 + dy[i];
    			
    			if(x2>=0 && x2<x && y2>=0 && y2<y && !visited[x2][y2] && arr[x2][y2] == 0) {
    				q.add(new dot(x2,y2));
    				visited[x2][y2] = true;
    				arr[x2][y2] = 2;
    			}
    		}
    	}
    }
    
    public static int countZero(int[][] arr) {
    	int zero_count = 0;
    	
    	for(int i=0; i<x; i++) {
    		for(int j=0; j<y; j++) {
    			if(arr[i][j] == 0) {
    				zero_count++;
    			}
    		}
    	}
    	
    	return zero_count;
    }
    
    public static int[][] deepCopy(int[][] original) {
    	if(original == null) return null;
    	
    	int[][] result = new int[original.length][original[0].length];
    	
    	for(int i=0; i<original.length; i++) {
    		System.arraycopy(original[i], 0, result[i], 0, original[0].length);
    	}
    	
    	return result;
    }
}


class dot {
	int x;
	int y;
	
	public dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}