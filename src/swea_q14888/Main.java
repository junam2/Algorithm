package swea_q14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
	static int n;
	static int[] number;
	static int[] operator;
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		number = new int[n];
		int[] tmp = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int total = 0;
		
		for(int i=0; i<4; i++) {
			tmp[i] = Integer.parseInt(st.nextToken());
			total += tmp[i];
		}
		
		operator = new int[total];
		int index = 0;
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<tmp[i]; j++) {
				operator[index] = i;
				index++;
			}
		}
		
		do {
			int temp = cal(number[0], number[1], operator[0]);
			for(int i=2; i<number.length; i++) {
				temp = cal(temp, number[i], operator[i-1]);
			}
			
			if(max < temp) {
				max = temp;
			}
			
			if(min > temp) {
				min = temp;
			}
			
		} while(nextPermutation(operator));
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static boolean nextPermutation(int[] array) {
	    // i는 증가하지 않은 가장 긴 접미사 인덱스
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i]) {
	        i--;            
	    }
	 
	    // 마지막 변경이 되었을 때 4321일 경우 i는 위의 접미사 인덱스를 구하는 과정에서 -1이 됨.
	    if (i <= 0)
	        return false;
	 
	    // array[i - 1] 요소를 피벗으로 정함.
	    // 위의 피벗과 스왑할 위의 피벗을 초과한 가장 큰 오른쪽 요소를 찾음.
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1]) {
	        j--;            
	    }
	    // array[j] <= array[i - 1] 조건인 이유는.
	    // array[j]는  array[i - 1]보다 항상 커야하기 때문이다. 
	    // => 사전 순으로 모든 경우의 수를 들려야하기 때문.
	    // array[i - 1] 와 array[j]를 이용하여 새로운 피벗 구함.
	    // array[j] 요소가 새로운 피벗이 된다.
	    // Assertion: j >= i
	 
	    // 피벗 2개는 서로 스왑에 이용하기 위해 사용됨.
	    // 새로운 피벗을 이용하여 스왑.
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // 위의 과정이 일어나더라도 사전순으로 되지 않음.
	    // 접미사 인덱스를 활용하여 반대로 만들어준다.
	    j = array.length - 1;
	    while (i < j) {
	        temp = array[i];
	        array[i] = array[j];
	        array[j] = temp;
	        i++;
	        j--;
	    }	 
	    return true;
	}

	public static int cal(int a, int b, int mode) {
		switch(mode) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		case 3:
			if(a<0 && b>0) {
				return -1*((-1*a)/b);
			} else {
				return a/b;
			}
		default:
			return 0;
		}
	}
}
