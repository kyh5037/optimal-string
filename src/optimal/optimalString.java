package optimal;

import java.util.Scanner;
import java.util.Stack;

public class optimalString {
	
	int f_len, s_len;
	int costI, costD, costC;
	public optimalString(char[] f_array, char[] s_array) {
		Scanner sc = new Scanner(System.in);
		if(f_array == s_array) return;
		
		f_len = f_array.length + 1;
		s_len = s_array.length + 1;
		
		int cost[][] = new int[f_len][s_len];
		String edit[][] = new String[f_len][s_len];
		String string[][] = new String[f_len][s_len];
		cost[0][0] = 0;
		edit[0][0] = "-";
		string[0][0] = String.valueOf(f_array);
		String s_string = String.valueOf(s_array);
		System.out.print("삽입: ");
		int insert = sc.nextInt();
		System.out.print("삭제: ");
		int delete = sc.nextInt();
		System.out.print("수정: ");
		int modify = sc.nextInt();
		
		for(int i = 1; i <= s_len-1; i++) {
			cost[0][i] = cost[0][i-1] + insert;
			edit[0][i] = "I";
			String temp = s_string.substring(0, i);
			string[0][i] = temp + string[0][0];
		}
		
		for(int i = 1; i <= f_len-1; i++) {
			cost[i][0] = cost[i-1][0] + delete;
			edit[i][0] = "D";
			string[i][0] = string[i-1][0].substring(1);
		}
		
		for(int i = 1; i <= f_len-1; i++) {
			for(int j = 1; j <= s_len-1; j++) {
				String temp = s_string.substring(0, j);
				string[i][j] = temp + string[i][0];
			} 
		}
		
		for(int i = 1; i <= f_len-1; i++) {
			for(int j = 1; j <= s_len-1; j++) {
				
				costI = cost[i][j-1] + insert; 
				costD = cost[i-1][j] + delete;
				costC = cost[i-1][j-1] + modify;
				
				if(f_array[i-1] == s_array[j-1]) {
					cost[i][j] = cost[i-1][j-1];
					edit[i][j] = "C";
				} else {
					
					if(costI <= costD && costI <= costC) {
						edit[i][j] = "I";
					} else if (costD <= costC && costD <= costI) {
						edit[i][j] = "D";
					} else if (costC <= costD && costC <= costI) {
						edit[i][j] = "C";
					}
					
					cost[i][j] = min_function(costI, costD, costC);
				}
			}
		}
		
		print_cost(cost);
		System.out.println();
		print_edit(edit);
		System.out.println();
		optimal(edit, string);
	}
	
	public int min_function(int I, int D, int C) {
		int min = I;
		if (min > D)
			min = D;
		if (min > C)
			min = C;
		return min;
	}
	
	public void print_cost(int[][] cost) {
		System.out.println("======= cost(i,j) 표 =======");
		System.out.println();
		System.out.print("  ");
		for(int i = 0; i <= s_len-1; i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		System.out.println();
		for(int i =0 ; i <= f_len-1; i++) {
			System.out.print(i + " ");
			for(int j = 0; j <= s_len-1; j++) {
				System.out.printf("%4d", cost[i][j]);
			}
			System.out.println();
		}
	}
	
	public void print_edit(String[][] edit) {
		System.out.println("======= edit(i,j) 표 =======");
		System.out.println();
		System.out.print("  ");
		for(int i = 0; i <= s_len-1; i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		System.out.println();
		for(int i =0 ; i <= f_len-1; i++) {
			System.out.print(i + " ");
		for(int j = 0; j <= s_len-1; j++) {
			System.out.printf("%4s", edit[i][j]);
		}
		System.out.println();
	}
}
	
	public void optimal(String[][] edit, String[][] string) {
		Stack num_stack = new Stack();
		Stack string_stack = new Stack();
		int length_x = f_len - 1;
		int length_y = s_len - 1;
		
		while(length_x - 1 >= 0 && length_y >= 0) {
				num_stack.push(length_x+"-"+length_y);
				string_stack.push(string[length_x][length_y]);
				if(edit[length_x][length_y]=="C") {
					length_x--;
					length_y--;
				} else if(edit[length_x][length_y] == "D") { 
					length_x--;
				}
				else {
					length_y--;
				}
				
		}
		string_stack.push(string[0][0]);
		System.out.println("최적의 편집 순서열");
		while(!num_stack.isEmpty()) {
			System.out.print(num_stack.pop() + " ");
		}
		System.out.println();
		System.out.println("최적의 편집 문자열");
		while(!string_stack.isEmpty()) {
			System.out.print(string_stack.pop() + " ");
		}
	}
}
