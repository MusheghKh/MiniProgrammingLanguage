package com.max.proglang.parser;

import com.max.proglang.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

import static com.max.proglang.parser.ast.ConditionalExpression.*;

/**
 *
 * @author aNNiMON
 */
public final class Parser {
    
    private static final Token EOF = new Token(TokenType.EOF, "");

    private final List<Token> tokens;
    private final int size;
    
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }
    
    public List<Statement> parse() {
        final List<Statement> result = new ArrayList<>();
        while (!match(TokenType.EOF)) {
            result.add(statement());
        }
        return result;
    }

    private Statement statement(){
        if (match(TokenType.PRINT)){
            return new PrintStatement(expression());
        }
        if (match(TokenType.IF)){
            return ifElse();
        }
        return assignmentStatement();
    }

    private Statement assignmentStatement(){
        // WORD EQ
        final  Token current = get(0);
        if (match(TokenType.WORD) && get(0).getType() == TokenType.EQ){
            final String variable = current.getText();
            consume(TokenType.EQ);
            return new AssignmentStatement(variable, expression());
        }
        throw new RuntimeException("Unknown Statement");
    }

    private Statement ifElse(){
        final Expression condition = expression();
        final Statement ifStatment = statement();
        final Statement elseStatment;

        if (match(TokenType.ELSE)){
            elseStatment = statement();
        }else {
            elseStatment = null;
        }
        return new IfStatement(condition, ifStatment, elseStatment);
    }

    private Expression expression() {
        return logicalOR();
    }

    private Expression logicalOR(){
        Expression result = logicalAnd();

        while (true){
            if (match(TokenType.BARBAR)){
                result = new ConditionalExpression(OPERATOR.OR, result, logicalAnd());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression logicalAnd(){
        Expression result = equality();

        while (true){
            if (match(TokenType.AMPAMP)){
                result = new ConditionalExpression(OPERATOR.AND, result, equality());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression equality(){
        Expression result = conditional();

        if (match(TokenType.EQEQ)) {
            return new ConditionalExpression(OPERATOR.EQUALS, result, conditional());
        }
        if (match(TokenType.EXCLEQ)) {
            return new ConditionalExpression(OPERATOR.NOT_EQUALS, result, conditional());
        }
        return result;
    }

    private Expression conditional(){
        Expression result = additive();

        while (true) {
            if (match(TokenType.LT)) {
                result = new ConditionalExpression(OPERATOR.LT, result, additive());
                continue;
            }
            if (match(TokenType.LTEQ)) {
                result = new ConditionalExpression(OPERATOR.LTEQ, result, additive());
                continue;
            }
            if (match(TokenType.GT)) {
                result = new ConditionalExpression(OPERATOR.GT, result, additive());
                continue;
            }
            if (match(TokenType.GTEQ)) {
                result = new ConditionalExpression(OPERATOR.GTEQ, result, additive());
                continue;
            }
            break;
        }

        return result;
    }
    
    private Expression additive() {
        Expression result = multiplicative();
        
        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression multiplicative() {
        Expression result = unary();
        
        while (true) {
            // 2 * 6 / 3 
            if (match(TokenType.STAR)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.SLASH)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }
        
        return result;
    }
    
    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }
    
    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER)) {
            return new ValueExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.HEX_NUMBER)) {
            return new ValueExpression(Long.parseLong(current.getText(), 16));
        }
        if (match(TokenType.WORD)){
            return new VariableExpression(current.getText());
        }
        if (match(TokenType.TEXT)){
            return new ValueExpression(current.getText());
        }
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private Token consume(TokenType type){
        final Token current = get(0);
        if (type != current.getType()){
            throw new RuntimeException("Token " + current + " does not match " + type);
        }
        pos++;
        return current;
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }
    
    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }
}
