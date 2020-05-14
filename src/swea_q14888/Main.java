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
	    // i�� �������� ���� ���� �� ���̻� �ε���
	    int i = array.length - 1;
	    while (i > 0 && array[i - 1] >= array[i]) {
	        i--;            
	    }
	 
	    // ������ ������ �Ǿ��� �� 4321�� ��� i�� ���� ���̻� �ε����� ���ϴ� �������� -1�� ��.
	    if (i <= 0)
	        return false;
	 
	    // array[i - 1] ��Ҹ� �ǹ����� ����.
	    // ���� �ǹ��� ������ ���� �ǹ��� �ʰ��� ���� ū ������ ��Ҹ� ã��.
	    int j = array.length - 1;
	    while (array[j] <= array[i - 1]) {
	        j--;            
	    }
	    // array[j] <= array[i - 1] ������ ������.
	    // array[j]��  array[i - 1]���� �׻� Ŀ���ϱ� �����̴�. 
	    // => ���� ������ ��� ����� ���� ������ϱ� ����.
	    // array[i - 1] �� array[j]�� �̿��Ͽ� ���ο� �ǹ� ����.
	    // array[j] ��Ұ� ���ο� �ǹ��� �ȴ�.
	    // Assertion: j >= i
	 
	    // �ǹ� 2���� ���� ���ҿ� �̿��ϱ� ���� ����.
	    // ���ο� �ǹ��� �̿��Ͽ� ����.
	    int temp = array[i - 1];
	    array[i - 1] = array[j];
	    array[j] = temp;
	    
	    // ���� ������ �Ͼ���� ���������� ���� ����.
	    // ���̻� �ε����� Ȱ���Ͽ� �ݴ�� ������ش�.
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
