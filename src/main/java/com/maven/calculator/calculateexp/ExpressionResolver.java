package com.maven.calculator.calculateexp;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.maven.calculator.exception.ExpressionException;

public class ExpressionResolver {
	ArrayList<String> contents;
	String item;

	/**
	 * 
	 * @param expression
	 * @return
	 * @throws ExpressionException
	 */
	public String findResult(String expression) throws ExpressionException {

		try {
			expression = expression.replaceAll(" ", "");

			if(!StringUtils.hasText(expression) || !ExpressionUtil.isValidExpression(expression)){
				throw new ExpressionException("Invalid Expression");
			}

			expression = this.handleBrackets(expression);
		}catch(ExpressionException exception){
			expression = exception.getMessage();
		}

		return expression;
	}
	
	public String handleBrackets(String expression) throws ExpressionException {
		
		while (expression.contains(Character.toString('(')) || expression.contains(Character.toString(')'))) {
			
			for (int o = 0; o < expression.length(); o++) {
				
				/**
				 * search for a closing bracket
				 */
				if (expression.charAt(o) == ')') {
					for (int i = o; i >= 0; i--) {
						
						/**
						 * if closing bracket met
						 * search previous opening bracket
						 */
						
						if (expression.charAt(i) == '(') {
							String in = expression.substring(i + 1, o);
							in = this.evaluate(in);
							expression = expression.substring(0, i) + in + expression.substring(o + 1);
							i = o = 0;
						}
					}
				}
			}
			
			/**
			 * If simplified expression again contains any bracket
			 * then expression is not valid
			 * Assuming () condition
			 */
			if (expression.contains(Character.toString('(')) || expression.contains(Character.toString(')'))
					|| expression.contains(Character.toString('(')) || expression.contains(Character.toString(')'))) {
				
				throw new ExpressionException("Invalid Expression");
			}
		}
		
		expression = this.evaluate(expression);
		return expression;
	}

	public String evaluate(String exp) throws ExpressionException {
		
		ExpressionUtil expUtil = new ExpressionUtil();

		contents = new ArrayList<String>();
		item = "";
		
		for (int i = exp.length() - 1; i >= 0; i--) {
			
			if (Character.isDigit(exp.charAt(i))) {
				item = exp.charAt(i) + item;
				if (i == 0) {
					item = expUtil.put(item, contents);
				}
			} else {
				if (exp.charAt(i) == '-'
						&& (i == 0 || (!Character.isDigit(exp.charAt(i - 1))))) {
					item = exp.charAt(i) + item;
					item = expUtil.put(item, contents);
				} else {
					item = expUtil.put(item, contents);
					item += exp.charAt(i);
					item = expUtil.put(item, contents);
				}
			}
		}
		
		/**
		 * Assuming () in addition to below are the only operators
		 */
		for (OperatorEnum op : OperatorEnum.values()) { 
			expUtil.operate(contents, op.toString());
        }
		
		return (contents != null && !contents.isEmpty()) ? contents.get(0) : "Invalid Expression";
	}

}
