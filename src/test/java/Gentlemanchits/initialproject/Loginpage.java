package Gentlemanchits.initialproject;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Loginpage
{
	ChromeDriver driver;
	SoftAssert SoftAssert;
	@BeforeTest
	public void testexample() 
	{
		System.out.println("Initializing browser and accessing the URL");
		driver = new ChromeDriver();
		driver.get("https://www.wahylab.com/gendemo/");
		driver.manage().window().maximize();

	}
	@Test(priority=1)
	public void testTitle() 
	{
		System.out.println("Verifying the login page title");
		String expectedTitle = "Gentleman | Welcome";
		String actualTitle = driver.getTitle();
		System.out.println(driver.getTitle());
		SoftAssert=new SoftAssert();
		SoftAssert.assertEquals(actualTitle, expectedTitle, "The user is not able to access the URL, trouble in opened page");
		SoftAssert.assertAll();
	}
	@Test(priority=2, enabled=false)
	public void validlogin()
	{
		System.out.println("Verifying the login function with all valid inputs");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin@gentleman");
		driver.findElement(By.xpath("/html/body/div[1]/div/form/button")).click();
		String Expectedresult1="GENTLEMAN";
		String actualresult1=driver.getTitle();
		SoftAssert.assertEquals(actualresult1, Expectedresult1, "DEFECT : The user is not able to login with valid inputs");
		SoftAssert.assertAll();
		driver.findElement(By.xpath("//header/nav[1]/div[1]/ul[1]/li[1]/a[1]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign out')]")).click();
	}
	@Test(priority=3)
	public void invalidnamelogin()
	{
		System.out.println("Verifying the login function with invalid name field");
		driver.findElement(By.name("username")).sendKeys("admin123");
		driver.findElement(By.name("password")).sendKeys("admin@gentleman");
		driver.findElement(By.xpath("/html/body/div[1]/div/form/button")).click();
		try
		{
			String Expectedresult1="Gentleman | Welcome";
			String actualresult1=driver.getTitle();
			
			SoftAssert.assertEquals(actualresult1, Expectedresult1, "DEFECT : The user is able to login with invalid name field");
			SoftAssert.assertAll();

			WebElement error=driver.findElement(By.name("invalid-name;error_message"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("invalid-name;error_message")));
			SoftAssert.assertTrue(error.isDisplayed(), "DEFECT : The error message showing incorrectness in the name field is not displayed");
			if(error.isDisplayed())
			{
				List<String> expectedValues = Arrays.asList("Username", "Name", "invalid", "incorrect"); 

				for (String expectedValue : expectedValues) {
					SoftAssert.assertTrue(error.getText().contains(expectedValue), "DEFECT : The error message showing the incorrectness of the name field is not as expected, the expected one contains" + expectedValue);
				}
			}
		}
		catch(AssertionError | NoSuchElementException | TimeoutException e)
		{
			System.out.println("DEFECT : The error message showing incorrectness in the name field is not displayed.");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=4)
	public void invalidpasswordlogin()
	{
		System.out.println("Verifying the login function with invalid password field");
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admingentle");
		driver.findElement(By.xpath("/html/body/div[1]/div/form/button")).click();
		String Expectedresult="Gentleman | Welcome";
		String actualresult=driver.getTitle();
		SoftAssert.assertEquals(actualresult, Expectedresult, "DEFECT : The user is able to login with invalid password field");
		SoftAssert.assertAll();
		try
		{
			WebElement error=driver.findElement(By.name("invalid-password;error_message"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("invalid-password;error_message")));
			SoftAssert.assertTrue(error.isDisplayed(), "DEFECT : The error message showing incorrectness in the password field is not displayed");
			if(error.isDisplayed())
			{
				List<String> expectedValues = Arrays.asList("Password", "invalid", "incorrect"); 

				for (String expectedValue : expectedValues) {
					SoftAssert.assertTrue(error.getText().contains(expectedValue), "DEFECT : The error message showing the incorrectness of the password field is not as expected, the expected one contains" + expectedValue);
				}
			}
		}
		catch(NoSuchElementException | TimeoutException e)
		{
			System.out.println("DEFECT : The error message showing incorrectness in the password field is not displayed.");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	@Test(priority=5)
	public void invalidnamenpasswordlogin()
	{
		System.out.println("Verifying the login function with invalid name and password field");
		driver.findElement(By.name("username")).sendKeys("admin123");
		driver.findElement(By.name("password")).sendKeys("admingentle");
		driver.findElement(By.xpath("/html/body/div[1]/div/form/button")).click();
		String Expectedresult="Gentleman | Welcome";
		String actualresult=driver.getTitle();
		SoftAssert.assertEquals(actualresult, Expectedresult, "DEFECT : The user is able to login with invalid name & password fields");
		SoftAssert.assertAll();
		try
		{
			WebElement error=driver.findElement(By.name("invalid-name&password;error_message"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("invalid-name&password;error_message")));
			SoftAssert.assertTrue(error.isDisplayed(), "DEFECT : The error message showing incorrectness in the name & password fields is not displayed");
			
		}
		catch(NoSuchElementException | TimeoutException e)
		{
			System.out.println("DEFECT : The error message showing incorrectness in the name & password fields is not displayed.");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}
	
}


