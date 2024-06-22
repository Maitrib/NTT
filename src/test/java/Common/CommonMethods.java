package Common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;


import Runner.TestRun;

public class CommonMethods extends TestRun {

	public static void takeScreenShot(WebDriver driver, String path) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(path);
		FileHandler.copy(SrcFile, new File(path));

	}

	public static void openUrlInNewTab(String url) throws InterruptedException {
		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("tabs size before opening new tab " + tabs1.size());
		((JavascriptExecutor) driver).executeScript("window.open()");
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("tabs size after opening new tab " + tabs.size());
		driver.switchTo().window((tabs.get(tabs.size() - 1)));
		Thread.sleep(2000);
		driver.get(url);
	}

	public static String CurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static void JavaScriptExecutorMethod(String JS_Code)
	{
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	js.executeScript(JS_Code);
	}
	
	
	public static void implicitWaitForSeconds (int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public static void implicitWaitForMinutes (int time)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(time));
	}
	
	public static void waitElementToBeClickable (By element, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitElementToBeVisible(WebElement element, int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(time));
		wait.until(ExpectedConditions.visibilityOf((WebElement) element));
	}
	
//	public static void WaitElementDisplayedImplicitlyForSeconds (By element, int time)
//	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
//	}
//
//	public static void WaitElementDisappearedImplicitlyForSeconds (By element, int time)
//	{
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
//	}
//	
	
	public static void waitForPageLoad(int seconds) {
		ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				try {
					System.out.println("try to load");
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}
		};
		new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class).until(e);
	}
	
	
	public static void type(By element, String text)
	{
		waitElementDisplayedImplicitlyForSeconds(element, 20);
		driver.findElement(element).sendKeys(text);
	}
	
	public static void click(By element)
	{
//		waitElementDisplayedImplicitlyForSeconds(element, 20);
		driver.findElement(element).click();
	}
	
	
	public static void clickUsingXpath(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void clickUsingCSSLocator(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static String getTextUsingXpath(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		String text = (String) executor.executeScript("return arguments[0].value", element);  
		System.out.println(text);
		return text;  
		
	

	}
	
	 public static void waitElementDisplayedImplicitlyForSeconds(final By locator, int seconds) {
			new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofSeconds(1)).ignoring(WebDriverException.class)
					.until(visibilityOfElementLocated(locator));
		}
	
	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By locator) {
	    return new ExpectedCondition<WebElement>() {
	      public WebElement apply(WebDriver driver) {
	        try {
	          return elementIfVisible(driver.findElement(locator));
	        } catch (Exception e) {
	          return null;
	        }
	      }

	      @Override
	      public String toString() {
	        return "visibility of element located by " + locator;
	      }
	    };
	  }
	
	 private static WebElement elementIfVisible(WebElement element) {
		    return element.isDisplayed() ? element : null;
		  }
	 
	 
	 
	 public void waitElementDisapperedImplicitlyForSeconds(final By locator, int seconds) {
			new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofSeconds(1))
					.until(invisibilityOfElementLocated(locator));
		}
	 
	 
	 public static ExpectedCondition<Boolean> invisibilityOfElementLocated(final By locator) {
		    return new ExpectedCondition<Boolean>() {
		      public Boolean apply(WebDriver driver) {
		        try {
		          return !(driver.findElement(locator).isDisplayed());
		        } catch (NoSuchElementException e) {
		          // Returns true because the element is not present in DOM. The
		          // try block checks if the element is present but is invisible.
		          return true;
		        } catch (Exception e) {
		          // Returns true because stale element reference implies that element
		          // is no longer visible.
		          return true;
		        }
		      }

		      @Override
		      public String toString() {
		        return "element to no longer be visible: " + locator;
		      }
		    };
		  }
	 
	
	
	

}