package mq.demo.extentreporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReport {

	@Test
	public void test01() throws IOException {
		
		ExtentReports er = new ExtentReports("/Users/mquraishi/Documents/workspace/extentreporting/report/index.html");
		ExtentTest test = er.startTest("test01");
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.barnesandnoble.com");
		String title = driver.getTitle();
		
		try{
		Assert.assertEquals("modon", title);
		}catch(AssertionError ae){
			System.out.println("Assertion error happend");
			test.log(LogStatus.FAIL, test.getTest().getName() + "  is Failed");
			test.log(LogStatus.FAIL, ae.getMessage());
			
			
			TakesScreenshot screenShot = ((TakesScreenshot)driver);
			File f = screenShot.getScreenshotAs(OutputType.FILE);
			
			
			//Copying binary file using JDK. You also can use apache.util package 
			FileInputStream fin = new FileInputStream(f);
			File fou = new File("screenshot.jpeg");
			FileOutputStream fout = new FileOutputStream(fou);
			byte[] buffer = new byte[1028];
			
			while(true){
				int bytesRead = fin.read(buffer);
				if(bytesRead==-1){
					break;
				}
				fout.write(buffer, 0,bytesRead);
			}
			
			fout.close();
			fin.close();
			
			//Add the full path of image file location. if you add only the file name it does not look the file in the project directory. You must give the full path
			test.log(LogStatus.FAIL,"Title Verification Failed "+ test.addScreenCapture("/Users/mquraishi/Documents/workspace/extentreporting/screenshot.jpeg"));
			
			
			
			Assert.fail();
		}finally{
			
			driver.quit();
			er.endTest(test);
			er.flush();
			
		}
		
		
		
		

	}

}
