package optimal;

import java.util.Scanner;

public class execution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("두 문자열의 길이를 입력: ");
		int f_len = sc.nextInt();
		int s_len = sc.nextInt();
		sc.nextLine();
		
		String f_string = null, s_string = null;
		System.out.print("X 문자열을 입력: ");
		f_string = sc.next();
		System.out.print("Y 문자열을 입력: ");
		s_string = sc.next();
		
		char[] f_array = new char[f_len];
		char[] s_array = new char[s_len];
		for(int i = 0; i <= f_len - 1; i++) {
			f_array[i] = (f_string.charAt(i));
		} 
		for(int i = 0; i <= s_len - 1; i++) {
			s_array[i] = (s_string.charAt(i));
		} 
		
		optimalString os = new optimalString(f_array, s_array);
	}
}
