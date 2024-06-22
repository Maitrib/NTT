package Locators;

import org.openqa.selenium.By;

public class CommonLocators
{
	
	public static By userName = By.xpath("//*[@id='login-username-input']");
	public static By password = By.xpath("//*[@id=\'login-password-input\']");
	public static By btnLogin = By.xpath("//*[contains(text(),'Login')]");
	public static By logoDashboard = By.xpath("//div[@class='sc-hKFxyN kksiKu']//a//*[name()='svg']");
	

	

}
