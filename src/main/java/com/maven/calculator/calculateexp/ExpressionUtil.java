package com.maven.calculator.calculateexp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.maven.calculator.exception.ExpressionException;

public class ExpressionUtil {
	
	
	public String put(String item, ArrayList<String> contents) {

		if (!item.equals("")) {
			contents.add(0, item);
		}

		return "";
	}

	public ArrayList<String> operate(ArrayList<String> arrayList, String operator) throws ExpressionException {

		int scale = 10;
		BigDecimal result = BigDecimal.ZERO;

		for (int c = 0; c < arrayList.size(); c++) {

			if (arrayList.get(c).equals(operator)) {

				try {
					if (arrayList.get(c).equals(OperatorEnum.POWER.toString())) {
						result = new BigDecimal(arrayList.get(c - 1)).pow(Integer.parseInt(arrayList.get(c + 1)));
					} else if (arrayList.get(c).equals(OperatorEnum.MULTIPLY.toString())) {
						result = new BigDecimal(arrayList.get(c - 1)).multiply(new BigDecimal(arrayList.get(c + 1)));
					} else if (arrayList.get(c).equals(OperatorEnum.DIVIDE.toString())) {
						result = new BigDecimal(arrayList.get(c - 1)).divide(new BigDecimal(arrayList.get(c + 1)), scale, BigDecimal.ROUND_DOWN);
					} else if (arrayList.get(c).equals(OperatorEnum.ADD.toString())) {
						result = new BigDecimal(arrayList.get(c - 1)).add(new BigDecimal(arrayList.get(c + 1)));
					} else if (arrayList.get(c).equals(OperatorEnum.SUBTRACT.toString())) {
						result = new BigDecimal(arrayList.get(c - 1)).subtract(new BigDecimal(arrayList.get(c + 1)));
					}

					arrayList.set(c, (result.setScale(scale, RoundingMode.HALF_DOWN).stripTrailingZeros().toPlainString()));
					arrayList.remove(c + 1);
					arrayList.remove(c - 1);
				} catch (Exception exception) {
					
					throw new ExpressionException("Invalid Expression");
				}
			} else {
				continue;
			}
			c = 0;
		}
		return arrayList;
	}

	public static boolean isValidExpression(String expression) {

		List<Character> operatorList = new ArrayList<Character>();
		operatorList.add('^');
		operatorList.add('/');
		operatorList.add('*');
		operatorList.add('+');
		operatorList.add('-');
		operatorList.add('(');
		operatorList.add(')');
		
		for (int o = 0; o < expression.length(); o++) {
			
			if(!Character.isDigit(expression.charAt(o)) && !operatorList.contains(expression.charAt(o))) {
				return false;
			}			
			if (expression.charAt(o) == '(' && (!(o-1 < 0) && Character.isDigit(expression.charAt(o - 1)))) {

				return false;
			}
		}
		
		return true;
	}
	
}
