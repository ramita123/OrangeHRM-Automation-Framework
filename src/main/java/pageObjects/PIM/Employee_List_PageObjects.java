package pageObjects.PIM;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;





public class Employee_List_PageObjects {

//FINDING THE LOCATORS 

public WebDriver wait;

	public Employee_List_PageObjects(WebDriver driver) 
	
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private By Employee_List = By.xpath("//a[normalize-space()='Employee List']");
	private By Employee_List_Dropdown = By.xpath("//i[@class='oxd-icon bi-caret-up-fill']");	
	private By Employee_Name = By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--focus']//input[@placeholder='Type for hints...']");
	private By Employee_ID = By.xpath("//input[@class='oxd-input oxd-input--focus']");
	private By Employee_Status = By.xpath("//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]");
	private By Include = By.xpath("//i[@class='oxd-icon bi-caret-up-fill oxd-select-text--arrow']");
	private By Supervisor_Name = By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--focus']//input[@placeholder='Type for hints...']");
	private By Job_Title = By.xpath("//i[@class='oxd-icon bi-caret-up-fill oxd-select-text--arrow']");
	private By Sub_Unit = By.xpath("//div[@class='oxd-select-text oxd-select-text--focus']");
	private By Reset_Button = By.xpath("//button[@type='reset']");
	private By Search_Button = By.xpath("//button[@type='submit']");
	private By Add_Button = By.xpath("//button[normalize-space()='Add']");
	private By Select_All_ID = By.xpath("//div[@role='columnheader']//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");
	private By Edit = By.xpath("//div[@role='table']//div[1]//div[1]//div[9]//div[1]//button[1]//i[1]");
	private By Delete = By.xpath("//div[@role='table']//div[1]//div[1]//div[9]//div[1]//button[2]//i[1]");
	private By Pagination_Number = By.xpath("//button[normalize-space()='1']");
	private By Pagination_Arrow = By.xpath("//i[@class='oxd-icon bi-chevron-right']");
	
	
	
	//CREATING A RE-USABLE METHOD FOR THE EMPLOYEE LIST SECTION DROPDOWN
	
	public void Employee_Section_Drop()
	{
		//WebElement Clk_Drop = wait.until(ExpectedConditions.elementToBeClickable(Employee_List_Dropdown));
		//Clk_Drop.click();
	}
	
	//CREATE THE RE_USABLE METHODS FOR THE EMPLOYEE LIST BUTTON THE HEADER SECTION 
	
		public void Clk_Employee_Lst()
		{
		//	WebElement Clk_Emp_Lst = wait.until(ExpectedConditions.elementToBeClickable(Employee_List));
		//	Clk_Emp_Lst.click();
		}
		
	
	
	//CREATING RE-USABLE METHODS FOR EMPLOYEE NAME
	
	/*
	 * public void Clk_Employee_Name( String name ) { WebElement
	 * Employee_Name_Element =
	 * wait.until(ExpectedConditions.elementToBeClickable(Employee_Name));
	 * Employee_Name_Element.clear(); Employee_Name_Element.sendKeys(name); }
	 * 
	 * 
	 * //CREATING RE-USABLE METHOD FOR THE EMPLOYEE _ID
	 * 
	 * public void Clk_Employee_ID(String ID) { // WebElement Clk_Emp_ID =
	 * wait.until(ExpectedConditions.elementToBeClickable(Employee_ID)); //
	 * Clk_Emp_ID.clear(); //Clk_Emp_ID.click(); //Clk_Emp_ID.sendKeys(ID);
	 * 
	 * }
	 * 
	 * //CREATING RE - USABLE METHODS FOR THE EMPLOYEE STATUS TO CLICK ON SPECIFIC
	 * OPTION (HARD CODE)
	 * 
	 * public void Clk_Employee_Status(String Status) { try { //CLICK TO OPEN THE
	 * DROPDOWN
	 * 
	 * //WebElement Clk_Emp_St =
	 * wait.until(ExpectedConditions.elementToBeClickable(Employee_Status)); //
	 * Clk_Emp_St.click();
	 * 
	 * //CONSTRUCT THE DYNAMIC XPATH TO FIND THE STATUS OPTION By Status_Option =
	 * By.xpath("\"//span[text()='\"" + Status + "']"); // WebElement Options =
	 * wait.until(ExpectedConditions.elementToBeClickable(Status_Option));
	 * //Options.click();
	 * 
	 * //
	 * Assert.assertTrue(Options.isDisplayed(),"Failed To Select The Status:"+Status
	 * );
	 * 
	 * }catch(Exception e ) { System.err.println("Error in selecting status: " +
	 * e.getMessage()); throw e; } }
	 * 
	 * 
	 * //CREATING RE-USABLE METHODS THAT WILL RANDOMLY SELECT ONE OPTION FROM THE
	 * EMPLOYEE STATUS DROP DOWN
	 * 
	 * public void Rnd_Clk_Employee_Status() { try {
	 * 
	 * WebElement Rnd_DropDown_Emp_St =
	 * wait.until(ExpectedConditions.elementToBeClickable(Employee_Status));
	 * Rnd_DropDown_Emp_St.click();
	 * 
	 * 
	 * //FETCH ALL THE OPTIONS OF THE DROPDOWN IN THE LIST List<WebElement> options
	 * = driver.findElements(By.xpath("//div[@role='option']"));
	 * 
	 * 
	 * //SELECTING A RANDOM OPTION
	 * 
	 * Random random = new Random(); int randomIndex =
	 * random.nextInt(options.size()); WebElement randomOptions =
	 * options.get(randomIndex); randomOptions.click();
	 * 
	 * Assert.assertTrue(randomOptions.isDisplayed()
	 * ,"Failed To Select options randomly ");
	 * 
	 * } catch(Exception Rnd)
	 * 
	 * {
	 * 
	 * System.err.println("Error in Selecting Option:"+ Rnd.getMessage()); }
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * //CREATING A RE-USABLE METHOD TO SELECT A SPECEFIC OPTION FORM THE INCLUDE
	 * DROPDOWN
	 * 
	 * public void Select_Include(String Status)
	 * 
	 * { try { //CLICK TO OPEN THE DROPDOWN WebElement slt_Include =
	 * wait.until(ExpectedConditions.elementToBeClickable(Include));
	 * slt_Include.click();
	 * 
	 * //CONSTRUCT THE X-PATH TO CLICK ON THE SPECEFIC OPTION ON THE DROPDOWN //THIS
	 * X-PATH IS DYNAMIC
	 * 
	 * By Status_Option = By.xpath("//span[text()='\"" +Status+"']"); WebElement
	 * Click_Option =
	 * wait.until(ExpectedConditions.elementToBeClickable(Status_Option));
	 * Click_Option.click(); Assert.assertTrue(Click_Option.isDisplayed(),
	 * "Failed To select the specific option"); } catch(Exception Inc) {
	 * System.err.println("Error in Selcting the Specific Option"+Inc.getMessage());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * //CREATING A RE-USABLE METHOD TO CLICK RANDOMLY ON ANY OPTION ON THE DROP
	 * DOWN "INLCUDE"
	 * 
	 * public void RNd_Select_Include() { try { WebElement Click_Drop_Arrow =
	 * wait.until(ExpectedConditions.elementToBeClickable(Include));
	 * Click_Drop_Arrow.click();
	 * 
	 * //FETCHinG ALL THE OPTIONS OF DROPDOWN IN AN ARRAY
	 * 
	 * List<WebElement> Rnd_Slt_Opt = driver.findElements(By.xpath(
	 * "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div[2]"
	 * ));
	 * 
	 * Random Random_Click = new Random(); int randomIndex =
	 * Random_Click.nextInt(Rnd_Slt_Opt.size()); WebElement randomOption
	 * =Rnd_Slt_Opt.get(randomIndex); randomOption.click();
	 * 
	 * Assert.assertTrue(randomOption.isDisplayed()
	 * ,"Failed To Select Random Option from DorpDown");
	 * System.out.println("Randomly Selected Option is "+randomOption.getText()); }
	 * catch (Exception Inc) {
	 * System.err.println("Error In Selecting The Option "+Inc.getMessage()); throw
	 * Inc ; }
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * //CREAING A RE-USABLE METHOD TO CLICK ON THE AUTOFIELD SUPERVISOR NAME
	 * 
	 * public void Slt_Supervisior_Name(String Partial_Name , String Full_name) {
	 * try { WebElement Click_Supervisor_Field =
	 * wait.until(ExpectedConditions.elementToBeClickable(Supervisor_Name));
	 * Click_Supervisor_Field.click(); Click_Supervisor_Field.clear();
	 * Click_Supervisor_Field.sendKeys("Sai"); // EDIT THIS NAME AS THI IS DYNAMIC
	 * THIS cAN BE CHANGED
	 * 
	 * By Find_Option = By.xpath("//div[@class='oxd-form-actions']"+Full_name+"']");
	 * //ADJUST LOCATOR IF NEEDED WebElement Slt_Frm_Drp =
	 * wait.until(ExpectedConditions.elementToBeClickable(Find_Option));
	 * 
	 * Slt_Frm_Drp.click();
	 * 
	 * System.out.println("The Seleted Full Name is :"+Full_name);
	 * 
	 * 
	 * }
	 * 
	 * catch(Exception Sp_Nm) {
	 * System.err.println("Error Selecting the Supervisior"+Sp_Nm.getMessage()); } }
	 * 
	 * 
	 * //CREATING A RE-USABLE METHOD TO SElECt A SPECIF OPTION FROM THE JOB TITLE
	 * DROP DOWN
	 * 
	 * public void Click_Spc_Opt_JobTitle() { try { WebElement Clk_Drp_Dwn =
	 * wait.until(ExpectedConditions.elementToBeClickable(Job_Title));
	 * Clk_Drp_Dwn.click();
	 * 
	 * By Locate_Option =
	 * By.xpath("//div[@class='orangehrm-background-container']"); WebElement
	 * Clk_opt = wait.until(ExpectedConditions.elementToBeClickable(Locate_Option));
	 * Clk_opt.click();
	 * 
	 * Assert.assertTrue(Clk_opt.isDisplayed(),
	 * "Failed to Select Option Form The Dropdown"); System.out.println();
	 * 
	 * }
	 * 
	 * catch(Exception Er_Spc_Opt) {
	 * System.err.println("Error in Selecting The Job Tiitle "+Er_Spc_Opt.getMessage
	 * ()); } }
	 * 
	 * 
	 * //CREATING A RE USABLE METHOD TO SELECT ANY RANDOM OPTION FOR THE JOB TITLE
	 * 
	 * public void Rnd_Opt_Job_Title () { try
	 * 
	 * {
	 * 
	 * 
	 * WebElement Open_DropDown =
	 * wait.until(ExpectedConditions.elementToBeClickable(Job_Title));
	 * Open_DropDown.click();
	 * 
	 * List<WebElement> Fetch_All_Options = driver.findElements(By.xpath(
	 * "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div[2]"
	 * ));
	 * 
	 * Random Select_Random_JobTitle = new Random(); int randomIndex =
	 * Select_Random_JobTitle.nextInt(Fetch_All_Options.size());
	 * 
	 * WebElement Click_Random_JobTitle = Fetch_All_Options.get(randomIndex);
	 * Click_Random_JobTitle.click();
	 * 
	 * System.out.println("Randomly Selected Job Title "+Click_Random_JobTitle.
	 * getText());
	 * 
	 * } catch(Exception Rnd_Jb_Title) {
	 * System.err.println("Unable To Select The Options The Error Message:"
	 * +Rnd_Jb_Title.getMessage() );
	 * 
	 * 
	 * } }
	 * 
	 * 
	 * //CREATING A RE - USABLE METHOD TO SELECT SPECIFIC OPTION FROM THE SUB UNIT
	 * 
	 * public void sltSubUnit()
	 * 
	 * { try { WebElement clkDrpdwn =
	 * wait.until(ExpectedConditions.elementToBeClickable(Sub_Unit));
	 * clkDrpdwn.click();
	 * 
	 * By LocateOption = By.xpath(
	 * "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[7]/div/div[2]/div/div[2]/div[4]"
	 * ); WebElement sltOption =
	 * wait.until(ExpectedConditions.elementToBeClickable(LocateOption));
	 * sltOption.click();
	 * 
	 * Assert.assertTrue(sltOption.isDisplayed()
	 * ,"Failed To Select The Expected Option");
	 * 
	 * 
	 * }
	 * 
	 * catch(Exception sbunit) {
	 * System.err.println("The Warning Message is : "+sbunit.getMessage()); }
	 * 
	 * 
	 * }
	 * 
	 * //CREATING A RE USABLE METHOD TO SELECT A RANDOM OPTION FROM THE SUB UNIT
	 * FIELD
	 * 
	 * public void sltRndSubUnit() { try { WebElement openDrpDwn =
	 * wait.until(ExpectedConditions.elementToBeClickable(Sub_Unit));
	 * openDrpDwn.click();
	 * 
	 * List<WebElement> fetchAllOption = driver.findElements(By.xpath(
	 * "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[7]/div/div[2]/div/div[2]"
	 * ));
	 * 
	 * Random indexOptions =new Random(); int RandomIndex =
	 * indexOptions.nextInt(fetchAllOption.size());
	 * 
	 * WebElement randomOption = fetchAllOption.get(RandomIndex);
	 * randomOption.click();
	 * 
	 * System.out.println("The Selected Option From The Dropdowm is : "+randomOption
	 * .getText());
	 * 
	 * }
	 * 
	 * catch(Exception RndSubUnit) {
	 * System.err.println("THE ERROR WARNING MESSAGE "+RndSubUnit); }
	 * 
	 * 
	 * }
	 * 
	 * //CREATING RE USABLE METHOD TO CLICK ON THE SEARCH BUTTON
	 * 
	 * public void clkSearchButton () {
	 * 
	 * try { WebElement clickSearch =
	 * wait.until(ExpectedConditions.elementToBeClickable(Search_Button));
	 * clickSearch.click();
	 * 
	 * System.out.println("Clicked On the Search Button"); } catch(Exception SrBT) {
	 * System.err.println("The Warning Message is : "+SrBT.getMessage()); }
	 * 
	 * }
	 * 
	 * 
	 * // CREATING RE USABLE METGOD TO CLICK ON YHE RESER BUTTON
	 * 
	 * public void clkResetButton() {
	 * 
	 * try { WebElement clkReset =
	 * wait.until(ExpectedConditions.elementToBeClickable(Reset_Button));
	 * clkReset.click();
	 * 
	 * System.out.println("Button Successfully Clicked");
	 * 
	 * 
	 * } catch(Exception ckBT) {
	 * System.err.println("The Warning Message is Coming"+ckBT.getMessage()); } }
	 * 
	 * 
	 * 
	 * //CREATING RE - USABLE METHOD TO CLICK ON THE ADD BUTTON
	 * 
	 * public void clkADD() { try { WebElement clickADD =
	 * wait.until(ExpectedConditions.elementToBeClickable(Add_Button));
	 * clickADD.click();
	 * 
	 * System.out.println("The Button Has Been CLicked "); }
	 * 
	 * catch(Exception ckADD) { System.err.println("The Error MESSAGE :"
	 * +ckADD.getMessage()); } }
	 * 
	 * 
	 * 
	 * public void sltAllRecord() { try {
	 * 
	 * WebElement clickAllRd =
	 * wait.until(ExpectedConditions.elementToBeClickable(Select_All_ID));
	 * clickAllRd.click();
	 * 
	 * System.out.println("SUCCESSFULY Selected ALl Records "); } catch(Exception
	 * ckALRd) { System.err.println("The errror message :"+ckALRd.getMessage()); } }
	 * 
	 * 
	 * 
	 * //CREATING RE - USABLE METHOD TO CLICK ON THE EDIT BUTTON OF A SPECIFIC
	 * RECORD EDIT BUTTON
	 * 
	 * public void clkEditBtn() { //DON'T FORGOT TO CHNAGE THE LOCATOR OF THE EDIT
	 * BUTTON AS IT IS FOR THE SPECIFC EMPLOYEE WebElement clkEdit =
	 * wait.until(ExpectedConditions.elementToBeClickable(Edit)); clkEdit.click();
	 * 
	 * 
	 * }
	 * 
	 * //CREATING A RE USBALE METHOD TO CLICK ON THE EDIT BUTTON RANDOMLY FOR ANY
	 * OPTION .
	 * 
	 * public void rndClkEdit() {
	 * 
	 * }
	 * 
	 * public void clkDelete() { WebElement clickDelete =
	 * wait.until(ExpectedConditions.elementToBeClickable(Delete));
	 * clickDelete.click(); }
	 * 
	 * 
	 * //CREATING RE UABLE TO FIND OUT LL THE BROKEN LINKS IN THE PAGE
	 * 
	 * public void brnlinkchk() {
	 * 
	 * try { List<WebElement> all_links = driver.findElements(By.tagName("a"));
	 * System.out.println("System Founded All This Link" + all_links.size());
	 * 
	 * //ITERATE OVER EACH LINKS
	 * 
	 * for(WebElement link:all_links) { String url = link.getAttribute("href");
	 * 
	 * //CHECKING ALL THE LINKS ARE CORRECT OR NOT
	 * 
	 * if(url!=null && !url.isEmpty()) { vrfLinks(url);
	 * 
	 * } else { System.out.println("The Link are Either Not Configured or  empty");
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * catch(Exception Brklink) { System.err.println("Error while checking links: "
	 * + Brklink.getMessage()); } }
	 * 
	 * public void vrfLinks(String urlLink) {
	 * 
	 * try {
	 * 
	 * @SuppressWarnings("deprecation") URL url = new URL(urlLink);
	 * HttpURLConnection httpurlconnection = (HttpURLConnection)
	 * url.openConnection(); httpurlconnection.setConnectTimeout(5000);
	 * httpurlconnection.connect();
	 * 
	 * //CHECK IF THE RESPONSE CODE IS 200OK
	 * 
	 * if(httpurlconnection.getResponseCode()>=400) {
	 * System.out.println("The Prinkted link is a broken libk"+urlLink);
	 * 
	 * 
	 * } else { System.out.println("The Printed link is a Valid Link "+urlLink); }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * catch(Exception vrflnk) { System.err.println("Error while checking links: " +
	 * vrflnk.getMessage());
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */






}





















