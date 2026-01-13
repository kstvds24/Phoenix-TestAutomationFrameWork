package com.api.retry;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzer.class);
	private static final int MAX_ATTEMPTS=2;
	private int count = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		LOGGER.info("Checking if the {} test can be re-executed",result.getName());
		if(count <= MAX_ATTEMPTS)
		{
			LOGGER.info("Executing the {} test, Current-Attempt: {}/{}",result.getName(),count,MAX_ATTEMPTS);
			count++;
			return true;
		}
		return false;
	}

}
