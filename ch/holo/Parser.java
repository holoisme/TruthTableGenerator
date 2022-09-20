package ch.holo;

import java.util.ArrayList;

import ch.holo.tokens.Token;
import ch.holo.tokens.TokenType;

public class Parser {
	
	public static class ParseResult {
		public Object node;
		public ArrayList<Token> variables = new ArrayList<>();
		public String toString() {
			return "ParseResult "+node;
		}
	}
	
	public static class VarNode {
		public Token token;
		public VarNode(Token token) {
			this.token = token;
		}
		public String toString() {
			return token.value;
		}
	}
	
	public static class NotNode {
		public Object node;
		public NotNode(Object node) {
			this.node = node;
		}
		public String toString() {
			return "!"+node;
		}
	}
	
	public static class AndNode {
		public Object a, b;
		public AndNode(Object a, Object b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return a+" & "+b;
		}
	}
	
	public static class OrNode {
		public Object a, b;
		public OrNode(Object a, Object b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return a+" | "+b;
		}
	}
	
	public static class XorNode {
		public Object a, b;
		public XorNode(Object a, Object b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return a+" + "+b;
		}
	}
	
	public static class ImplicationNode {
		public Object a, b;
		public ImplicationNode(Object a, Object b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return a+" -> "+b;
		}
	}
	
	public static class BiconditionalNode {
		public Object a, b;
		public BiconditionalNode(Object a, Object b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return a+" <-> "+b;
		}
	}
	
	public static class ParContentNode {
		public Object node;
		public ParContentNode(Object node) {
			this.node = node;
		}
		public String toString() {
			return "("+node+")";
		}
	}
	
	public boolean alreadyContainsVariable(Token tok) {
		for(Token t:main.variables) {
			if(tok.value.equalsIgnoreCase(t.value)) return true;
		}
		return false;
	}
	
	public Object particle() {
		if(currentToken.is(TokenType.VAR)) {
			if(!alreadyContainsVariable(currentToken))
				main.variables.add(currentToken);
			Object node = new VarNode(currentToken);
			advance();
			return node;
		} else if(currentToken.is(TokenType.LPAR)) {
			advance();
			Object node = biconditional();
			advance();
			return new ParContentNode(node);
		}
		return null;
	}
	
	public Object not() {
		if(currentToken.is(TokenType.NOT)) {
			advance();
			return new NotNode(particle());
		} else return particle();
	}
	
	public Object and() {
		Object node = not();
		while(currentToken.is(TokenType.AND)) {
			advance();
			Object b = not();
			node = new AndNode(node, b);
		}
		return node;
	}
	
	public Object or() {
		Object node = and();
		while(currentToken.is(TokenType.OR) || currentToken.is(TokenType.XOR)) {
			Token savedToken = currentToken;
			advance();
			Object b = and();
			if(savedToken.is(TokenType.OR))
				node = new OrNode(node, b);
			if(savedToken.is(TokenType.XOR))
				node = new XorNode(node, b);
		}
		return node;
	}
	
	public Object implication() {
		Object node = or();
		while(currentToken.is(TokenType.IMPLICATION)) {
			advance();
			Object b = or();
			node = new ImplicationNode(node, b);
		}
		return node;
	}
	
	public Object biconditional() {
		Object node = implication();
		while(currentToken.is(TokenType.BICONDITIONAL)) {
			advance();
			Object b = implication();
			node = new BiconditionalNode(node, b);
		}
		return node;
	}
	
	private ParseResult main;
	private ArrayList<Token> tokens;
	private int index;
	private Token currentToken;
	public ParseResult process(ArrayList<Token> tokens) {
		this.main = new ParseResult();
		this.tokens = tokens;
		this.index = 0;
		this.currentToken = tokens.get(index);
		
		Object node = biconditional();
		main.node = node;
		
		return main;
	}
	
	public void advance() {
		index++;
		if(index >= tokens.size())
			return;
		currentToken = tokens.get(index);
	}
	
}