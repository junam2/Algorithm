package q5397;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		

		for(int i=0; i<t; i++) {
			char[] str = br.readLine().toCharArray();	
			LinkedList<Character> arr = new LinkedList<Character>();
			
			int cursor = 0;
			
			for(int j=0; j<str.length; j++) {
				switch(str[j]) {
				case '<':
					if(cursor != 0) {
						cursor--;
					}
					break;
				case '>':
					if(cursor != arr.size()) {
						cursor++;
					}
					break;
				case '-':
					if(cursor > 0 && arr.size() > 0) {
						arr.remove(--cursor);
					}
					break;
				default:
					arr.add(cursor, str[j]);
					cursor++;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int k=0; k<arr.size(); k++) {
				sb.append(arr.get(k));
			}
			
			System.out.println(sb);
			
		}
	}
}
