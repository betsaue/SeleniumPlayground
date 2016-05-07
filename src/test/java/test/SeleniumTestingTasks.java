package test;


import static org.junit.Assert.*;


import java.awt.Point;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumTestingTasks {

	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://timvroom.com/selenium/playground/");

	}

	@Test
	public void test() throws InterruptedException {
		
		//Grab page title and place title text in answer slot #1
		String pageTitle = driver.getTitle();
		driver.findElement(By.id("answer1")).sendKeys(pageTitle);
		
		
		//Fill out name section of form to be Kilgore Trout
		String pageName = "Kilgore Trout";
		driver.findElement(By.id("name")).sendKeys(pageName);
		
		
		//Set occupation on form to Sci-Fi Author
		WebElement dropDownList = driver.findElement(By.id("occupation"));
		//new Select(dropDownList).selectByIndex(2);
		new Select(dropDownList).selectByVisibleText("Science Fiction Author");
		
		
		//Count number of blue_boxes on page after form and enter into answer box #4
		int xpathCount= driver.findElements(By.xpath("//span[@class='bluebox']")).size();
		String count = String.valueOf(xpathCount);
		driver.findElement(By.id("answer4")).sendKeys(count);
		
		
		//Click link that says 'click me'
		driver.findElement(By.linkText("click me")).click();
		
		
		//Find red box on its page find class applied to it, and enter into answer box #6
		String redClass = driver.findElement(By.id("redbox")).getAttribute("class").toString();
		driver.findElement(By.id("answer6")).sendKeys(redClass);
		
		
		//Run JavaScript function as: ran_this_js_function() from your Selenium script
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("ran_this_js_function();");
		
		
		//Run JavaScript function as: got_return_from_js_function() from your Selenium script, take returned value and place it in answer slot #8
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		Long returnValue = (Long)executor1.executeScript("return got_return_from_js_function();");
		String value = String.valueOf(returnValue);
		driver.findElement(By.id("answer8")).sendKeys(value);
		
		
		//Mark radio button on form for written book? to Yes
		driver.findElement(By.name("wrotebook")).click();
		
		
		//Get the text from the Red Box and place it in answer slot #10
		String redText = driver.findElement(By.id("redbox")).getText();
		driver.findElement(By.id("answer10")).sendKeys(redText);
			
		
		//Which box is on top? orange or green -- place correct color in answer slot #11
		int pointOrange = driver.findElement(By.id("orangebox")).getLocation().getY();
		int pointGreen = driver.findElement(By.id("greenbox")).getLocation().getY();
		if (pointOrange < pointGreen){
			driver.findElement(By.id("answer11")).sendKeys("orange");
		}
		else{
			driver.findElement(By.id("answer11")).sendKeys("green");
		}
		
		
		
		//Set browser width to 850 and height to 650
		Dimension d = new Dimension(850,650);
		driver.manage().window().setSize(d);
		
		
		
		//Type into answer slot 13 yes or no depending on whether item by id of ishere is on the page
		if (driver.findElements(By.id("ishere")).size() != 0 )
			driver.findElement(By.id("answer13")).sendKeys("yes");
		
		else
			driver.findElement(By.id("answer13")).sendKeys("no");
				
		
		
		//Type into answer slot 14 yes or no depending on whether item with id of purplebox is visible
		if (driver.findElement(By.id("purplebox")).isDisplayed())
			driver.findElement(By.id("answer14")).sendKeys("yes");
		
		else
			driver.findElement(By.id("answer14")).sendKeys("no");
		
		
		
		//Waiting game
		driver.findElement(By.xpath("//a[@onclick='show_new_link_after_wait();return false;']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@onclick='click_after_wait();return false;']")));
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath("//a[@onclick='click_after_wait();return false;']")).click();		
		
	}
	
	
		@Test
		public void test1() throws InterruptedException {
		//Click OK on the confirm after completing task 15
		Alert alertWindow = driver.switchTo().alert(); 
		alertWindow.accept();	
		
		
		
		//Click the submit button on the form 
		driver.findElement(By.id("submitbutton")).click();
			
		
		
		//Checking the final results
		driver.findElement(By.id("checkresults")).click();	
			
	}

	
	
	@AfterClass
	public static void afterClass() {
		//driver.close();
	}

}