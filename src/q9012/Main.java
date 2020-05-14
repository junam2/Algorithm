package q9012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		boolean[] flag = new boolean[n];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			Stack<Character> s = new Stack<>();
			int k = 0;
			
			while(true) {
				char tmp = str.charAt(k);
				
				if(tmp == '(') {
					s.push(tmp);
				} 
				else {
					if(s.isEmpty()) {
						break;
					} else {
						s.pop();
					}
				}
				
				k++;
				
				if(k==str.length() && s.isEmpty()) {
					flag[i] = true;
					break;
				} else if(k==str.length()) {
					break;
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			if(flag[i]) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
