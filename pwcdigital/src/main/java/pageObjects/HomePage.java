package pageObjects;

import manager.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Wait;

import java.util.List;

//This will handle all the object and methods of home page.
public class HomePage {

    WebDriver driver;
    Integer timeout = (int)FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();

    private static final String PAGE_TITLE = "//p[contains(text(), 'Digital Pulse')]";
    private static final String COLUMNS = "//div[contains(@class,'headline_column')]";

    private static final String ARTICLES = "//div[contains(@class,'headline_column')][$value$]/article";

    private static final String SUBSCRIBE = "//a[contains(@navigation-title,'Subscribe')]";

    private static final String SEARCHBUTTON = "//button[text()='Search']";

    private static final String SEARCHFIELD = "//input[@name='searchfield']";

    private static final String SUBMITSEARCH = "//input[@type='submit']";



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //This is used to navigate to the home page.
    public void navigate(){
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Wait.untilPageLoadComplete(driver);
    }
 //This is used to verify if Home page is opened or not.
    public void verifyHomePageOpened(String pageName){

        switch(pageName){
            case "Home":
                By TitleText = By.xpath(PAGE_TITLE);
                WebElement element = Wait.untilVisible(driver, TitleText, timeout);
                Assert.assertTrue(element.isDisplayed());
                break;


        }

    }

    //This is used to verify the no of columns in the home page.
    public void verifyColumnNumbers(int colNum)
    {
        By columns = By.xpath(COLUMNS);
        List<WebElement> elements = driver.findElements(columns);
        int noOfCol = elements.size();
        Assert.assertEquals(noOfCol,colNum);
    }

    //This is used to verify the no of articles in each column in the home page.
    public void verifyArticlesNumbers(int col,int articleNum)
    {
        String articles = ARTICLES.replace("$value$",String.valueOf(col));
        List<WebElement> elements = driver.findElements(By.xpath(articles));
        int noOfArticle = elements.size();
        Assert.assertEquals(articleNum,noOfArticle);
    }

    public void clickSubscribe()
    {
        By subscribeLink = By.xpath(SUBSCRIBE);
        WebElement element = Wait.untilVisible(driver, subscribeLink, timeout);
        element.click();

    }

    public void clickSearchButton()
    {
        By searchButton = By.xpath(SEARCHBUTTON);
        WebElement element = Wait.untilVisible(driver, searchButton, timeout);
        Assert.assertTrue(element.isDisplayed());
        element.click();
    }

    public void enterTextIntoSearchbox(String text)
    {
        By searchField = By.xpath(SEARCHFIELD);
        WebElement element = Wait.untilVisible(driver, searchField, timeout);
        Assert.assertTrue(element.isDisplayed());
        element.sendKeys(text);
    }

    public void clickOnSubmitSearchButton()
    {
        By submitSearch = By.xpath(SUBMITSEARCH);
        WebElement element = Wait.untilVisible(driver, submitSearch, timeout);
        Assert.assertTrue(element.isDisplayed());
        element.click();

    }




}
