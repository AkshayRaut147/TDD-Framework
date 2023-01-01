package utility;

import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.TestBase;

public class ListnerSetup extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) 
	{
		logger.info("Test Execution started for  : "+result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		logger.info("Test execution was successful for : "+result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		logger.info("Test execution failed for : "+result.getName());
		UtilityClass ut =new UtilityClass();
		ut.screenshot(result.getName());
		logger.info("Screenshot captured & saved for failed test as : "+result.getName()+".png in screenshots folder");
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		logger.info("This test was skipped : "+result.getName());
	}

	
}
