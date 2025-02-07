package pageObjects.Dashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base_Objects.Base_Objects;

import org.openqa.selenium.WebDriver;
public class employee_Distribution_Graph extends Base_Objects

{

	public employee_Distribution_Graph(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By clickGraph = By.cssSelector("canvas[style*=\"box-sizing: border-box\"]");
	
	public void tstGraph()
	{
		WebElement clkGraph = wait.until(ExpectedConditions.elementToBeClickable(clickGraph));
		clkGraph.click();
		
		JavascriptExecutor js 	= (JavascriptExecutor) driver;
		String script = "let canvas = document.querySelector('canvas[style*=\"box-sizing: border-box\"]');" +
                "let context = canvas.getContext('2d');" +
                "return canvas.toDataURL();";
		
		String graphData = (String) js.executeScript(script);
		
		System.out.println("Graph Data (Base64): " + graphData);
		
		
		
		//List<WebElement> Datapoints = driver.findElements(clickGraph);
		//for(WebElement point:Datapoints)
		//{
		//	System.out.println("The Data points are " +point.getText());
		//}
	}
}
