package q2504;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Stack<String> s = new Stack<>();
		int total = 0;
		
		try {
			for(int i=0; i<str.length(); i++) {
				String tmp = String.valueOf(str.charAt(i));
				
				if(tmp.equals("(")) {
					s.push(tmp);
				}
				else if(tmp.equals("[")) {
					s.push(tmp);
				}
				else if(tmp.equals(")")) {
					if(s.isEmpty()) {
						break;
					}
					
					if(s.peek().equals("(")) {
						s.pop();
						s.push("2");
					} else {
						int a = 0;
						while(!s.peek().equals("(")) {
							a += Integer.parseInt(s.pop());
						}
						s.pop();
						s.push(String.valueOf(2*a));
					}
				}
				else if(tmp.equals("]")) {
					if(s.isEmpty()) {
						break;
					}
					
					if(s.peek().equals("[")) {
						s.pop();
						s.push("3");
					} else {
						int a = 0;
						while(!s.peek().equals("[")) {
							a += Integer.parseInt(s.pop());
						}
						s.pop();
						s.push(String.valueOf(3*a));
					}
				}			
			}
			
			if(s.contains("(") || s.contains("[")) {
				System.out.println(0);
			} else {
				while(!s.isEmpty()) {
					total += Integer.parseInt(s.pop());
				}
				System.out.println(total);
			}
		} catch (Exception e) {
			System.out.println(0);
		}
	}
}
