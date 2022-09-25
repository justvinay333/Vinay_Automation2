package sep21;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class AppTest {
	WebDriver driver;
  @BeforeTest
  public void setUp() {
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  }
  @Test(dataProvider = "dp")
  public void VerifyLogin(String user, String pass) {
	  driver.get("http://orangehrm.qedgetech.com/symfony/web/index.php/auth/login");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.name("txtUsername")).sendKeys(user);
	  driver.findElement(By.name("txtPassword")).sendKeys(pass);
	  driver.findElement(By.name("Submit")).submit();
	  String expected = "dashboard";
	  String actual = driver.getCurrentUrl();
	  if(actual.contains(expected)) {
		  Reporter.log("Login success"+expected+"   "+actual);
	  }
	  else {
		  Reporter.log("Login Fail"+expected+"   "+actual);
	  }
  }
  @DataProvider
  public Object[][] dp() {
	  Object login[][]=new Object[5][2];
	  login[0][0]="Admin";
	  login[0][1]="Qedge123!@#";
	  login[1][0]="test";
	  login[1][1]="Qedeg123!@#";
	  login[2][0]="Admin";
	  login[2][1]="Qedeg123!@#";
	  login[3][0]="test1";
	  login[3][1]="Qedeg123";
	  login[4][0]="vinay";
	  login[4][1]="Qedeg123";
	return login;
	  
  }
  
  @AfterTest
  public void tearDown() {
	  driver.quit();
  }

}
