package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtilities {
	
	public WebDriver driver;
	static String selectedmonth ;
	public static String TESTDATA_SHEET_PATH="C:/Users/Ramita Sambyal/Orange_HRM_Advance/Oragne_HRM_Advance"
			+ "/src/main/java/com/hrm/qa/testdata/TestData.xlsx";
	
	private static XSSFSheet sheet;
	private static Object[][] data;



	public static void staticWait(int sleepTimeInSeconds) {
		try {
			Thread.sleep(sleepTimeInSeconds * 1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitElementToVisible(WebDriver driver, int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	
	}
	
	/*
	 * public void waitElementToVisible(int time, WebElement element) {
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	 * wait.until(ExpectedConditions.visibilityOf(element));
	 * 
	 * }
	 */
	
	public void waitForInvisibility(WebDriver driver,int time, WebElement element) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.invisibilityOfAllElements(element));
	}
	
	public void waitElementToBeClickable(WebDriver driver, int time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//reusable selelctdate function
	public  void selectDateFromCalendar(String month,WebDriver driver,WebElement calendarField, List<WebElement> listOfDays, String day,WebElement selectedMonth,WebElement chooseMonth) {
		waitElementToVisible(driver,1,calendarField);
		calendarField.click();
		
		waitForElementsVisible(driver,2,listOfDays);
		 selectedmonth=selectedMonth.getText();
		System.out.println(selectedmonth);
		while(!(selectedMonth.getText().equals(month))) {
			chooseMonth.click();
		}
    	System.out.println(listOfDays.size());
    	for(int i=0;i<listOfDays.size();i++) {
    		String calendarDay=listOfDays.get(i).getText();
    		try {
    		if(calendarDay.equals(day)) {
    			listOfDays.get(i).click();
    			break;
    		}
    	}
    	catch(Exception e) {
    		System.out.println(e.getStackTrace());
    	}}
	}

	public  void  waitForElementsVisible(WebDriver driver, int timeout, List<WebElement> locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		 wait.until(ExpectedConditions.visibilityOfAllElements(locator));
		 
	
	}

	public void scrollPage(int x, int y, WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
	}
	
	public void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
	    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
	        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
	    );
	}
	
	
	public Object[][] getTestData(String sheetname) {
		
		FileInputStream file = null;
		try {
		 file= new FileInputStream(TESTDATA_SHEET_PATH);
		 System.out.println("path of excel sheet is"+file);
		}
		catch(Exception e) {
			e.getMessage();
		}
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	int sheetsCount=	workbook.getNumberOfSheets();
	System.out.println("sheet name is "+ sheetname);
	for(int i=0;i<sheetsCount;i++) {
		String sheetName=workbook.getSheetAt(i).getSheetName();
		if(sheetName.equals(sheetname)) {
		 sheet=	workbook.getSheetAt(i);
		 
		  data=new Object[sheet.getPhysicalNumberOfRows()-1][sheet.getRow(0).getPhysicalNumberOfCells()];
			int row=sheet.getPhysicalNumberOfRows();
			System.out.println("count of row"+row);
			
			int col=sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("count of col"+col);
			for(int k=0;k<row-1;k++) {
				for(int j=0;j<col;j++) {
				data[k][j]=	sheet.getRow(k+1).getCell(j).toString();
				System.out.println(data[k][j]);
				}
			}
			
		
		}
		break;
	}
	
	return data;
		
		
		
	}
	
	public void getSingleElementScreenhsot(WebElement element,String Name) throws IOException {
	File file=	element.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/Reports/HTML"+Name+".png"));
	}
	
	//here we need to do downcasting because web driver does not extent this takesScreenshot
	//interface 
	public static String getScreenshot(WebDriver driver,String testcaseName) {
		TakesScreenshot takeScreenshot=(TakesScreenshot) driver;
	File screenshotFile=	takeScreenshot.getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(screenshotFile, new File(System.getProperty("user.dir")+"//Reports2//"+testcaseName+".png"));
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return System.getProperty("user.dir")+"//Reports2//"+testcaseName+".png";
	}
	
	
	public void getHeightWidth(WebElement element) {
	System.out.println(	element.getRect().getDimension().getHeight());
	System.out.println(	element.getRect().getDimension().getWidth());
		
	}
	
	
	public void uploadFileHelper(String autoItExePath) {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(autoItExePath);

            // Start the process
            Process process = processBuilder.start();

            // Wait for the AutoIt script to complete (optional)
            int exitCode = process.waitFor();
            System.out.println("AutoIt script executed with exit code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("file not found in mentioned path");
		}
	}
}
