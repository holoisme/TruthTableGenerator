package ch.holo.tokens;

public class Token {
	
	public String value;
	public TokenType type;
	
	public Token(String value, TokenType type) {
		this.value = value;
		this.type = type;
	}
	
	public Token(TokenType type) {
		this.value = null;
		this.type = type;
	}
	
	public boolean is(TokenType t) {
		return type == t;
	}
	
	public String toString() {
		return type+(value!=null?":"+value:"");
	}
	
}