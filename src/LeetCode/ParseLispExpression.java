package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class ParseLispExpression {

    public static void main(String[] args) {
        String exp = "(let x 2 (add (let x 3 (let x 4 x)) x))";
        var tem = new ParseLispExpression();
        tem.evaluate(exp);
    }
    /*
     * expression -> letExp | addExp | multExp | num | Ident
     * assignExp -> var expression
     * assignStatment -> assignExp assignStatement | ''
     * lexExp -> ('let' assignStatement expression)
     * addExp -> ('add' expression1 expression2)
     * multExp -> ('mult' expression1 expression2)
     */
    public int evaluate(String expression) {
        Tokenizer tokenizer = new Tokenizer(expression);
        Analyser analyser = new Analyser(tokenizer);
        return analyser.analyseExpression();
    }
}

/**
 * 词法分析得到的结果
 */
class Token {
    private TokenType tokenType;
    private Object value;
    public Token(TokenType tokenType, Object value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

enum TokenType {
    //let
    LET_KW,
    //add
    ADD_KW,
    //mult
    MULT_KW,
    //有符号整数
    INT_LITERAL,
    //(
    LEFT_BRACE,
    //)
    RIGHT_BRACE,
    //标识符
    IDENT,
    //unknown
    UNKNOWN,
    //结束符
    EOF
}

class Tokenizer {
    private String expression;
    //表示已经处理到的index
    private int index;
    //表示调用nextToken前的index
    private int prevIndex;
    public Tokenizer(String expression) {
        this.expression = expression;
        this.index = -1;
        this.prevIndex = -1;
    }

    public void setIndex(int index) {
        this.index = index;
    } 

    public int getPrevIndex() {
        return this.prevIndex;
    }

    public Token nextToken() {
        prevIndex = index;
        //跳过空白符
        while (index != expression.length() - 1 && Character.isWhitespace(peekChar())) {
            nextChar();
        }
        //已经到词法分析的末尾,结束
        if (index == expression.length() - 1) {
            return new Token(TokenType.EOF, "");
        }
        char peek = peekChar();
        if (peek == '-' || Character.isDigit(peek)) {
            return lexInt();
        } else if (Character.isAlphabetic(peek)) {
            return lexIdentOrKeyWord();
        } else{
            return lexOther();
        }
    }

    /*
     * 处理有符号整型常量
     */
    private Token lexInt() {
        StringBuilder sb = new StringBuilder();
        if (peekChar() == '-') {
            sb.append(nextChar());
        }
        while (Character.isDigit(peekChar())) {
            sb.append(nextChar());
        }
        return new Token(TokenType.INT_LITERAL, Integer.parseInt(sb.toString()));
    }

    /*
     * 处理标识符和关键字
     * Ident = [a-z][0-9a-z]
     */
    private Token lexIdentOrKeyWord() {
        StringBuilder sb = new StringBuilder();
        //首字母是小写字母
        if (Character.isAlphabetic(peekChar())) {
            sb.append(nextChar());
        } 
        while (Character.isAlphabetic(peekChar()) || Character.isDigit(peekChar())) {
            sb.append(nextChar());
        }
        if (sb.toString().equals("let")) {
            return new Token(TokenType.LET_KW, "let");
        } else if (sb.toString().equals("add")) {
            return new Token(TokenType.ADD_KW, "add");
        } else if (sb.toString().equals("mult")) {
            return new Token(TokenType.MULT_KW, "mult");
        } else {
            return new Token(TokenType.IDENT, sb.toString());
        }
    }

    /*
     * 处理括号
     */
    private Token lexOther() {
        if (peekChar() == '(') {
            nextChar();
            return new Token(TokenType.LEFT_BRACE, "(");
        } else if (peekChar() == ')') {
            nextChar();
            return new Token(TokenType.RIGHT_BRACE, ")");
        } 
        return new Token(TokenType.UNKNOWN, "");
    }

    /**
     * 返回下一个char，如果下一个char不存在，则返回ch = 0;
     * @return
     */
    private char peekChar() {
        return index < expression.length() - 1 ? expression.charAt(index + 1) : 0;
    }

    /*
     * 返回下一个char,并且下移
     */
    private char nextChar() {
        if (index < expression.length() - 1) {
            char next = expression.charAt(index + 1);
            index++;
            return next;
        } else {
            return 0;
        }
    }
}

class SymbolTable {
    //存储变量值
    private HashMap<String, Integer> map = new HashMap<>();
    private SymbolTable upperSymbolTable;
    public SymbolTable() {
        this.upperSymbolTable = null;
    }
    public SymbolTable(SymbolTable uppSymbolTable) {
        this.upperSymbolTable = uppSymbolTable;
    }
    public HashMap<String, Integer> getMap() {
        return this.map;
    }
    public SymbolTable getUpperSymbolTable() {
        return this.upperSymbolTable;
    }
}
class Analyser {
    private Tokenizer tokenizer;
    private Token peekedToken = null;
    private Token previousPeekedToken = null;
    private Stack<SymbolTable> stack = new Stack<>();
    public Analyser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }
    public int analyseExpression() {
        //如果直接是一个整型字面量
        //expression -> letExp | addExp | multExp | num | Ident
        if (check(TokenType.INT_LITERAL)) {
            var token = nextIf(TokenType.INT_LITERAL);
            return (Integer)token.getValue();
            //如果不是数字，则必是左括号
        } else if (check(TokenType.IDENT)) {
            var token = nextIf(TokenType.IDENT);
            SymbolTable variableTable = stack.peek();
            while (!variableTable.getMap().containsKey((String)token.getValue())) {
                variableTable = variableTable.getUpperSymbolTable();
            }
            return variableTable.getMap().get((String)token.getValue());
        } else {
            //每当进入一个括号，要创建当前作用域的栈
            //此时没有upperSymbolTable
            SymbolTable symbolTable;
            if (stack.isEmpty()) {
                symbolTable = new SymbolTable();
            } else {
                symbolTable = new SymbolTable(stack.peek());
            }
            stack.add(symbolTable);
            nextIf(TokenType.LEFT_BRACE);
            if (check(TokenType.LET_KW)) {
                return analyseLetExpression();
            } else if (check(TokenType.ADD_KW)) {
                return analyseAddExpression();
            } else if (check(TokenType.MULT_KW)) {
                return analyseMultExpression();
            }
            //一般来说不会走到这
            return -1;
        }
    }
    // * assignExp -> var expression
    // * assignStatment -> assignExp assignStatement | ''
    // * lexExp -> ('let' assignStatement expression)
    // * addExp -> ('add' expression1 expression2)
    // * multExp -> ('mult' expression1 expression2)
    private int analyseLetExpression() {
        nextIf(TokenType.LET_KW);
        analyseAssignStat();
        int value = analyseExpression();
        nextIf(TokenType.RIGHT_BRACE);
        stack.pop();
        return value;
    }

    private void analyseAssignStat() {
        if (!check(TokenType.IDENT)) {
            return;
        }
        previousPeekedToken = peekedToken;
        var variable = nextIf(TokenType.IDENT);
        int prevIndex = tokenizer.getPrevIndex();
        //说明此时是表达式
        if (check(TokenType.RIGHT_BRACE)) {
            tokenizer.setIndex(prevIndex);
            peekedToken = previousPeekedToken;
            return;
        }
        int value = analyseExpression();
        stack.peek().getMap().put((String)variable.getValue(), value);
        analyseAssignStat();
    }

    private int analyseAddExpression() {
        nextIf(TokenType.ADD_KW);
        int leftValue = analyseExpression();
        int rightValue = analyseExpression();
        nextIf(TokenType.RIGHT_BRACE);
        stack.pop();
        return leftValue + rightValue;
    }

    private int analyseMultExpression() {
        nextIf(TokenType.MULT_KW);
        int leftValue = analyseExpression();
        int rightValue = analyseExpression();
        nextIf(TokenType.RIGHT_BRACE);
        stack.pop();
        return leftValue * rightValue;
    }

    private Token peek() {
        if (peekedToken == null) {
            peekedToken = tokenizer.nextToken();
        }
        return peekedToken;
    }

    private Token next() {
        if (peekedToken != null) {
            var token = peekedToken;
            peekedToken = null;
            return token; 
        } else {
            return tokenizer.nextToken();
        }
    }

    private boolean check(TokenType tt) {
        var token = peek();
        return token.getTokenType() == tt;
    }

    private Token nextIf(TokenType tt) {
        var token = peek();
        if (token.getTokenType() == tt) {
            return next();
        } else {
            return null;
        }
    }
}
