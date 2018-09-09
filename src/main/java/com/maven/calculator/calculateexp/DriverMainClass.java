package com.maven.calculator.calculateexp;

import com.maven.calculator.exception.ExpressionException;

/**
 * DriverMainClass:  Utility Class created for test
 *
 */
public class DriverMainClass {

	public static void main(String[] args){

		String expression = "7+(6*5^2+3-4/2)";
		//expression = "8*+7";
		//expression = "7+(67(56*2))";
		//expression = "(8*5/8)-(3/1)-5";

		expression = expression.replaceAll(" ", "");
		
		ExpressionResolver expRes = new ExpressionResolver();
		
		try {
			expression = expRes.findResult(expression);
		}catch(ExpressionException exception){
			expression = exception.getMessage();
		}
		
		System.out.println("Result: " + expression);
	}

}