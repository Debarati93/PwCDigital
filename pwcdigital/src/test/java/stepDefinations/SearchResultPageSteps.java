package stepDefinations;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import helper.TestContext;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pageObjects.SearchResultPage;
import pageObjects.SubscribePage;

public class SearchResultPageSteps {

    TestContext testContext;
    SearchResultPage searchResultPage;

    private static Logger log = LogManager.getLogger(SearchResultPageSteps.class);

    public SearchResultPageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.searchResultPage = testContext.getPageObjectManager().getSearchResultPage();
    }

    @Then("I am taken to the search results page")
    public void iAmTakenToTheSearchResultsPage() {

        searchResultPage.verifySearchResultPageOpened();
        log.info("Search Result page is displayed");
    }


    @And("I am presented with at least \"(.*)\" search result")
    public void iAmPresentedWithAtLeastSearchResult(String noOdResults) {

        searchResultPage.minNoOfSearchResults(noOdResults);
        log.info("Min 1 search result is present");
    }
}
