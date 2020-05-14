package q1316;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String[] str = new String[n];
		
		for(int i=0; i<str.length; i++) {
			str[i] = br.readLine();
			if(check(str[i])) count++;;
		}
		
		System.out.println(count);
	}
	
	public static boolean check(String str) {
		String[] tmp = str.split("");
		
		for(int i=0; i<tmp.length-1; i++) {
			int j;
			for(j=i+1; j<tmp.length; j++) {
				if(!tmp[i].equals(tmp[j])) {
					break;
				}
			}
			
			for(int k=j; k<tmp.length; k++) {
				if(tmp[i].equals(tmp[k])) {
					return false;
				}
			}
		}
		
		return true;
	}
}
