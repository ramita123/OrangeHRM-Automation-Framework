package base_Objects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Objects

{
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected FluentWait<WebDriver>fluentWait;



	//INITIALIZING THE CONSTRUCTOR FOR WEBDRIVER AND WAITS
	public Base_Objects(WebDriver driver)
	{

		this.driver = driver;
		this.wait= new WebDriverWait(driver,Duration.ofSeconds(15));
		this.fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(0)).ignoring(Exception.class);
		

	}


}
