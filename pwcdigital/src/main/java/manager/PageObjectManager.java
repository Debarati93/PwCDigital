package manager;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

//This class is used to handle all the page objects.
public class PageObjectManager {

    private WebDriver driver;
    private HomePage homePage;

    private SubscribePage subscribePage;

    private SearchResultPage searchResultPage;



    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    //This will return an instance of Home page.
    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    //This will return an instance of Subscribe page.
    public SubscribePage getSubscribePage() {
        return (subscribePage == null) ? subscribePage = new SubscribePage(driver) : subscribePage;
    }

    //This will return an instance of Search Result page.
    public SearchResultPage getSearchResultPage() {
        return (searchResultPage == null) ? searchResultPage = new SearchResultPage(driver) : searchResultPage;
    }


}
