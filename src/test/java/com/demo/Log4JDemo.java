package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JDemo {
	private static Logger logger = LogManager.getLogger(Log4JDemo.class);

	public static void main(String[] args) {
		logger.info("Inside main method");
		int a = 10;
		logger.info("Value of a is {}", a);
		int b = 0;
		if (b == 0) {
			logger.warn("Value of b is {}", b);
		} else {
			logger.info("Value of b is {}", b);
		}
		try {
			int result = a / b;
		} catch (ArithmeticException ex) {
			logger.error("Operation cannot happen", ex);
		}
		// System.out.println("Result is: " + result);
	}

}
