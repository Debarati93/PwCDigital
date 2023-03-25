package pageObjects;

import io.cucumber.datatable.DataTable;
import manager.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Wait;

import java.util.List;
import java.util.Map;
//This will handle all the object and methods of Search Result page.
public class SearchResultPage {

    WebDriver driver;
    Integer timeout = (int)FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();



    private static final String PAGE_TITLE = "//h2[contains(text(),'Refine your search')]";

    private static final String SEARCHRESULTS = "div[class='srp-list'] div";

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    //This is used to verify Search result is opened.
    public void verifySearchResultPageOpened(){

        By TitleText = By.xpath(PAGE_TITLE);
        WebElement element = Wait.untilVisible(driver, TitleText, timeout);
        Assert.assertTrue(element.isDisplayed());
    }


    //This is used to search minn no of search results.
    public void minNoOfSearchResults(String minNoOfSSearch)
    {
        By minsearchResults = By.cssSelector(SEARCHRESULTS);
        List<WebElement> elements =driver.findElements(minsearchResults);
        elements.size();
        Assert.assertTrue(elements.size()>=Integer.parseInt(minNoOfSSearch));


    }


    }




