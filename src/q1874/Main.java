package q1874;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;



public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Integer> s = new Stack<>();
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int tmp = 0;
		int tmp2 = 1;
		s.push(tmp2);
		sb.append("+\n");
		
		while(true) {
			if(tmp2 > n) {
				break;
			}
			
			if(s.isEmpty() && tmp==n) break;
			
			if(!s.isEmpty() && s.peek() == arr[tmp]) {
				s.pop();
				sb.append("-\n");
				tmp++;
			} else {
				s.push(++tmp2);
				sb.append("+\n");
			}
		}
		
		if(tmp2 > n) {
			System.out.println("NO");
		} else {
		System.out.println(sb);
		}
	}

}
