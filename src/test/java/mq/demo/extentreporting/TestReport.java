package mq.demo.extentreporting;

import junit.framework.ComparisonFailure;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReport {

	@Test
	public void test01() {
		
		ExtentReports er = new ExtentReports("/Users/mquraishi/Documents/workspace/extentreporting/report/index.html");
		ExtentTest txt = er.startTest("test01");
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.barnesandnoble.com");
		String title = driver.getTitle();
		
		try{
		Assert.assertEquals("modon", title);
		}catch(AssertionError ae){
			System.out.println("Assertion error happend");
			txt.log(LogStatus.FAIL, txt.getTest().getName() + "  is Failed");
			Assert.fail();
		}finally{
			driver.quit();
			er.endTest(txt);
			er.flush();
		}
		
		
		
		

	}

}
