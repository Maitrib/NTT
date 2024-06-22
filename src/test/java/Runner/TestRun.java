package Runner;

import java.io.FileInputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Common.CommonMethods;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/report1.html",
		"json:target/cucumber-reports/report2.json", },

		features = { "src/main/resources/FeatureFile/Scenarios.feature" }, monochrome = true,

		glue = { "Steps" },

		dryRun = false,

		tags = "@TestAssignment"

)

public class TestRun {

	public static WebDriver driver;
	public static Properties props=new Properties();
	public static Logger log = LoggerFactory.getLogger(TestRun.class);
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		FileInputStream inputStream=new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application.properties");
        props.load(inputStream);
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		CommonMethods.implicitWaitForSeconds(30);

	}

	@AfterClass
	public static void tearDown() throws Exception {
		CommonMethods.takeScreenShot(driver,System.getProperty("user.dir") + "/src/main/resources/Selenium_Screenshot/test" + CommonMethods.CurrentDateAndTime() + ".png");
		Thread.sleep(2000);
		System.out.println(props.getProperty("username"));
//		driver.quit();
	} 
	
	@After
	public void addSS(Scenario scenario) throws InterruptedException
	{
		String failedScreenShot = System.getProperty("user.dir") + "/src/main/resources/Selenium_Screenshot/test" + CommonMethods.CurrentDateAndTime() + ".png";
		String url = "<img src=" + failedScreenShot + " alt='screenshot'>";
		scenario.attach(url.getBytes(), "png", "Click Here To See Screenshot");
		
	}

}