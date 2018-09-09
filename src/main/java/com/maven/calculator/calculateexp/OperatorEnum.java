package com.maven.calculator.calculateexp;

public enum OperatorEnum {

	POWER{
		@Override
		public String toString() {
			return "^";
		}
	},
	DIVIDE{
		@Override
		public String toString() {
			return "/";
		}
	},
	MULTIPLY{
		@Override
		public String toString() {
			return "*";
		}
	},
	ADD{
		@Override
		public String toString() {
			return "+";
		}
	},
	SUBTRACT{
		@Override
		public String toString() {
			return "-";
		}
	};
}
