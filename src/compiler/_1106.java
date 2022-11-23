package compiler;

public class _1106 {
    public static boolean parseBoolExpr(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        Analyser analyser = new Analyser(tokenizer);
        return analyser.analyseExpression();
    }

    public static void main(String[] args) {
        String expression = "|(f,t)";
        parseBoolExpr(expression);
    }
}


enum TokenType {
    // !
    NOT_LW,
    // &
    AND_KW,
    // |
    OR_KW,
    // (
    LEFT_BRACE,
    // )
    RIGHT_BRACE,
    // ,
    COMMA,
    // t或者f
    BOOLEAN_LITERAL,
    // EOF
    EOF,
    // unknown
    UNKNOWN
}

class Token {
    private TokenType tokenType;
    private Object tokenValue;

    public Token(TokenType tokenType, Object tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Object getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(Object tokenValue) {
        this.tokenValue = tokenValue;
    }
}

/**
 *  expr = "t" | "f"
 *  expr = !(expr)
 *  expr = &(expr, expr, ....)
 *  expr = |(expr, expr, ....)
 */

class Tokenizer {
    //处理的字符串
    private String expression;
    //表示已处理到的index
    private int index;

    public Tokenizer(String expression) {
        this.expression = expression;
        this.index = -1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getExpression() {
        return expression;
    }

    public Token nextToken() {
        //到达词法分析末尾
        if (index == expression.length() - 1) {
            return new Token(TokenType.EOF, "");
        }
        char peek = peekChar();
        if (peek == 't' || peek == 'f') {
            return lexBooleanLiteral();
        } else if (peek == '(' || peek == ')' || peek == ',') {
            return lexOther();
        } else {
            return lexKeyWord();
        }
    }

    private Token lexBooleanLiteral() {
        if (peekChar() == 't') {
            nextChar();
            return new Token(TokenType.BOOLEAN_LITERAL, true);
        } else if (peekChar() == 'f') {
            nextChar();
            return new Token(TokenType.BOOLEAN_LITERAL, false);
        }
        return new Token(TokenType.UNKNOWN, "");
    }

    private Token lexOther() {
        if (peekChar() == '(') {
            return new Token(TokenType.LEFT_BRACE, nextChar());
        } else if (peekChar() == ')') {
            return new Token(TokenType.RIGHT_BRACE, nextChar());
        } else if (peekChar() == ',') {
            return new Token(TokenType.COMMA, nextChar());
        }
        return new Token(TokenType.UNKNOWN, "");
    }

    private Token lexKeyWord() {
        if (peekChar() == '!') {
            return new Token(TokenType.NOT_LW, nextChar());
        } else if (peekChar() == '&') {
            return new Token(TokenType.AND_KW, nextChar());
        } else if (peekChar() == '|') {
            return new Token(TokenType.OR_KW, nextChar());
        }
        return new Token(TokenType.UNKNOWN, "");
    }

    /**
     * return the next char without moving the index
     * @return next char, if not exist, return 0
     */
    private char peekChar() {
        return index < expression.length() - 1 ? expression.charAt(index + 1) : 0;
    }

    private char nextChar() {
        if (index < expression.length() - 1) {
            char next = expression.charAt(index+1);
            index = index + 1;
            return next;
        } else {
            return 0;
        }
    }
}



class Analyser {
    private Tokenizer tokenizer;
    private Token peekedToken = null;

    public Analyser (Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public boolean analyseExpression() {
        if (check(TokenType.BOOLEAN_LITERAL)) {
            Token token = nextIf(TokenType.BOOLEAN_LITERAL);
            return (boolean)(token.getTokenValue());
        } else if (check(TokenType.NOT_LW)) {
            return analyseNotExpr();
        } else if (check(TokenType.AND_KW)) {
            return analyseAndExpr();
        } else {
            return analyseOrExpr();
        }
    }

    private boolean analyseNotExpr() {
        nextIf(TokenType.NOT_LW);
        nextIf(TokenType.LEFT_BRACE);
        boolean value = analyseExpression();
        nextIf(TokenType.RIGHT_BRACE);
        return !value;
    }

    private boolean analyseAndExpr() {
        nextIf(TokenType.AND_KW);
        nextIf(TokenType.LEFT_BRACE);
        boolean value = true;
        while (true) {
            value = analyseExpression() && value;
            if (check(TokenType.RIGHT_BRACE)) {
                nextIf(TokenType.RIGHT_BRACE);
                break;
            } 
            nextIf(TokenType.COMMA);
        }
        return value;
    }

    private boolean analyseOrExpr() {
        nextIf(TokenType.OR_KW);
        nextIf(TokenType.LEFT_BRACE);
        boolean value = false;
        while (true) {
            value =  analyseExpression() || value;
            if (check(TokenType.RIGHT_BRACE)) {
                nextIf(TokenType.RIGHT_BRACE);
                break;
            } 
            nextIf(TokenType.COMMA);
        }
        return value;
    }

    private Token peek() {
        if (peekedToken == null) {
            peekedToken = tokenizer.nextToken();
        }
        return peekedToken;
    }

    private Token next() {
        if (peekedToken != null) {
            Token next = peekedToken;
            peekedToken = null;
            return next;
        } else {
            return tokenizer.nextToken();
        }
    }

    private boolean check(TokenType tt) {
        Token token = peek();
        return token.getTokenType() == tt;
    }

    private Token nextIf(TokenType tt) {
        Token token = peek();
        if (token.getTokenType() == tt) {
            return next();
        } else {
            return null;
        }
    }
}
