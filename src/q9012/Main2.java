package q9012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		for(int t=0; t<n; t++) {
			String str = br.readLine();
			
			Stack<Character> stack = new Stack<Character>();
			for(int i=0; i<str.length(); i++) {
				char tmp = str.charAt(i);
				
				if(tmp == '(') {
					stack.push(tmp);
				} else {
					if(stack.size() == 0) {
						System.out.println("NO");
						break;
					} else {
						stack.pop();
					}
				}
				
				if(i == str.length()-1) {
					if(stack.size() != 0) {
						System.out.println("NO");
					} else {
						System.out.println("YES");
					}
				}
			}
		}
	}

}
