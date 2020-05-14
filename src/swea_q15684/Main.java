package swea_q15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,m,h;
	static int[][] originMap;
	static ArrayList<line> possibleLine = new ArrayList<line>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		originMap = new int[h][n];
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			originMap[a-1][b-1] = 1;
			originMap[a-1][b] = 1;
		}
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<n; j++) {
				if(j==0 && originMap[i][j] == 0) {
					if(j+1<n-1 && originMap[i][j+1] == 0 && originMap[i][j+2] == 0) {
						possibleLine.add(new line(i,j));
					} else if(j+1 == n-1 && originMap[i][j+1] == 0) {
						possibleLine.add(new line(i,j));
					}
				} else if(j-1>=0 && originMap[i][j-1] == 0 && originMap[i][j] == 0) {
					if(j<n-2) {
						if(originMap[i][j+2] == 0) {
							possibleLine.add(new line(i,j));
						}
					} else if(j==n-2) {
						if(originMap[i][j+1] == 0) {
							possibleLine.add(new line(i,j));
						}
					}
				}
			}
		}
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(originMap[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i=0; i<possibleLine.size(); i++) {
			System.out.println("x: " + possibleLine.get(i).x  + ", y: " + possibleLine.get(i).y);
		}
		
		int[] comb;
		int count = 2;
		boolean flag = true;
		
		loop: while(true) {
			if(count > 3) {
				flag = false;
				break loop;
			}
			comb = new int[possibleLine.size()];
			Arrays.fill(comb, 1);
			
			for(int i=0; i<count; i++) {
				comb[i] = 0;
			}
			
			do {
				ArrayList<line> arr = new ArrayList<line>();
				
				for(int i=0; i<comb.length; i++) {
					if(comb[i] == 0) {
						arr.add(possibleLine.get(i));
					}
				}
				
				setLine(arr);
				
				int counting = 0;
				
				for(int i=0; i<n; i++) {
					if(isPossible(i)) {
						counting++;
					}
				}
				
				if(counting == n) {
					flag = true;
					break loop;
				}
				
				cleanLine(arr);
				
				
			} while(next_permutation(comb));
			
			count++;
		}
		
		if(flag) {
			System.out.println(count);
		} else {
			System.out.println(-1);
		}
	}
	
	static boolean isPossible(int index) {
		int nx = 0;
		int ny = index;
		
		while(true) {
			if(nx == h-1) {
				break;
			}
			
			if(originMap[nx][ny] == 1) {
				if(ny == 0) {
					if(originMap[nx][ny+1] == 1) {
						ny++;
					}
				} else if(ny < n-1) {
					if(originMap[nx][ny-1] == 1) {
						ny--;
					} else if(originMap[nx][ny+1] == 1) {
						ny++;
					}
				} else {
					if(originMap[nx][ny-1] == 1) {
						ny--;
					}
				}
				
			} 
			
			nx++;

		}
		
		if(ny == index) {
			return true;
		} else {
			return false;
		}
	}
	
	static void setLine(ArrayList<line> arr) {
		for(int i=0; i<arr.size(); i++) {
			line tmp = arr.get(i);
			
			originMap[tmp.x][tmp.y] = 1;
			originMap[tmp.x][tmp.y+1] = 1;
		}
	}
	
	static void cleanLine(ArrayList<line> arr) {
		for(int i=0; i<arr.size(); i++) {
			line tmp = arr.get(i);
			
			originMap[tmp.x][tmp.y] = 0;
			originMap[tmp.x][tmp.y+1] = 0;
			}
	}
	
	static boolean next_permutation(int[] arr) {
		int i = arr.length-1;
		
		while(i>0 && arr[i-1]>=arr[i]) {
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

class line {
	int x;
	int y;
	
	public line(int x,int y) {
		this.x = x;
		this.y = y;
	}
}