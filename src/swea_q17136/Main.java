package swea_q17136;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][] = new int[10][10];
	static int[] paperIndex = {0,5,5,5,5,5};
	static int paperCount = 0;
	static int minRes = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {	
		/*
		 * 0. ������ ���� ���ֱ�
		 * 1. ������ ū �ͺ��� ���� ������ ���δ�. ū�� �Ǹ� ���� �͵� �翬�� �Ǵ� �� ����
		 * 2. ó���� ���� ���� �� �� �����ϱ�
		 * 2-1. ������ �� �ٿ��µ� �� ������ �׳� �ѱ�ų� �ϴ°�..
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int i=0; i<10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] use = new int[5];
		
		dfs(0,use);
		
		
		if(minRes == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minRes);
		}
	}
	
	static void dfs(int index, int[] use) {
		
		if(allCover()) {
			int cnt = 0;
			
			for(int i=0; i<use.length; i++) {
				cnt += use[i];
			}
			
			if(minRes > cnt) {
				minRes = cnt;
			}
			return;
		}
		
		for(int i=index; i<10; i++) {
			for(int j=0; j<10; j++) {
				if(map[i][j] == 1) {
					for(int k=4; k>=0; k--) {
						if(use[k] < 5 && canCover(i,j,k)) {
							use[k]++;
							cover(i,j,k);
							dfs(i, use);
							uncover(i,j,k);
							use[k]--;
						}
					}
					return;
				}
			}
		}
	}
	
	static boolean canCover(int y, int x, int delta) {
        int ny, nx;
        for(int dy = 0 ; dy <= delta ; dy++) {
            for(int dx = 0 ; dx <= delta; dx++) {
                ny = y + dy;
                nx = x + dx;
                if(!(ny>=0 && ny < 10 && nx >= 0 && nx < 10))
                    return false;
                if(map[ny][nx] == 0)
                    return false;
            }
        }
        return true;
    }
	
    static void cover(int y, int x, int delta) {
        int ny, nx;
        for(int dy = 0 ; dy <= delta ; dy++) {
            for(int dx = 0 ; dx <= delta; dx++) {
                ny = y + dy;
                nx = x + dx;
                map[ny][nx] = 0;
            }
        }
    }
    
    static void uncover(int y, int x, int delta) {
        int ny, nx;
        for(int dy = 0 ; dy <= delta ; dy++) {
            for(int dx = 0 ; dx <= delta; dx++) {
                ny = y + dy;
                nx = x + dx;
                map[ny][nx] = 1;
            }
        }
    }
    
    static boolean allCover() {
        for(int i = 0 ; i < 10 ; i++) {
            for(int j = 0 ; j < 10 ; j++) {
                if(map[i][j] == 1)
                    return false;
            }
        }
        return true;
    }
}
