package ch.holo;

import java.util.ArrayList;
import java.util.Scanner;

import ch.holo.Parser.ParseResult;
import ch.holo.tokens.Token;

public class TruthTable {
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		Interpreter interpreter = new Interpreter();
		
		if(args.length != 0) {
			String sequence = "";
			for(String s:args)
				sequence+=s+" ";
			process(sequence, parser, interpreter);
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		while(running) {
			String sequence = scanner.nextLine();
			if(sequence.equals("stop")) {
				running = false;
				continue;
			}
			
			process(sequence, parser, interpreter);
		}
		
		scanner.close();
	}
	
	public static void process(String sequence, Parser parser, Interpreter interpreter) {
		ArrayList<Token> tokens = Lexer.process(sequence);
		
		ParseResult result = parser.process(tokens);
		
		Display.header(result.variables);
		Display.display(interpreter, result);
	}
	
}
