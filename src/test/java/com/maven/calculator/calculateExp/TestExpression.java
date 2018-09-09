package com.maven.calculator.calculateExp;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.maven.calculator.calculateexp.ExpressionResolver;
import com.maven.calculator.calculateexp.ExpressionUtil;
import com.maven.calculator.exception.ExpressionException;

public class TestExpression {

	ExpressionResolver expRes = new ExpressionResolver();
	
	/**
	 * Input test cases
	 * 
	 * @throws ExpressionException
	 */
	@Test
	public void testExpression() throws ExpressionException {
		
		 assertEquals("158", expRes.findResult("7+(6*5^2+3-4/2)"));  
	     assertEquals("Invalid Expression",  expRes.findResult("7+(67(56*2))"));
	     assertEquals("Invalid Expression",  expRes.findResult("8*+7"));
	     assertEquals("-3",  expRes.findResult("(8*5/8)-(3/1)-5"));
	}
	
	@Test
	public void testHandleOf() throws ExpressionException {
		
		 assertEquals("30", expRes.handleBrackets("(6*5)"));
		 
	}

	/**
	 * Test Divide by Zero scenario
	 * 
	 * @throws ExpressionException
	 */
	@Test(expected = ExpressionException.class)
	public void testOperate() throws ExpressionException {
		ExpressionUtil expUtil = new ExpressionUtil();
		
		ArrayList<String> subject = new ArrayList<String>();
		subject.add("12");
		subject.add("/");
		subject.add("0");
		
		assertEquals("144", expUtil.operate(subject, "/"));
		
	}
	
}
