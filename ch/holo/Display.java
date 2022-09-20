package ch.holo;

import java.util.ArrayList;

import ch.holo.Parser.ParseResult;
import ch.holo.tokens.Token;

public class Display {

	private static final char STARTS_WITH_TRUE = '0';
	private static final char STARTS_WITH_FALSE = '1';
	
	private static final char STARTS_WITH = STARTS_WITH_TRUE;
	
	public static void header(ArrayList<Token> vars) {
		String str = "";
		for(Token t:vars)
			str+=t.value+" ";
		str+=" | table";
		
		System.out.println(str);
	}
	
	public static void display(Interpreter interpreter, ParseResult result) {
		boolean[][] table = generateValues(result.variables.size());
		
		for(int i = 0; i < table.length; i++) {
			boolean[] section = table[i];
			for(int j = 0; j < result.variables.size(); j++) {
				System.out.print((section[j]?"T":"F") + " ");
			}
			System.out.print(" | ");
			System.out.print("  "+ (interpreter.process(result, section)?"T":"F") + "\n");
		}
	}
	
	public static boolean[][] generateValues(int n) {
		boolean[][] table = new boolean[(int) Math.pow(2, n)][n];
		for(int i = 0; i < table.length; i++) {
			String binary = complete0s(Integer.toBinaryString(i), n);
			for(int c = 0; c < binary.length(); c++) {
				table[i][c] = binary.charAt(c)==STARTS_WITH;
			}
		}
		return table;
	}
	
	public static String complete0s(String s, int n) {
		if(s.length() == n)
			return s;
		for(int i = s.length(); i < n; i++)
			s = "0"+s;
		return s;
	}
	
}