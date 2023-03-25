package stepDefinations;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.TestContext;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pageObjects.HomePage;
import pageObjects.SubscribePage;

public class SubscribePageSteps {

    TestContext testContext;
    SubscribePage subscribePage;

    private static Logger log = LogManager.getLogger(SubscribePageSteps.class);

    public SubscribePageSteps(TestContext testContext) {
        this.testContext = testContext;
        this.subscribePage = testContext.getPageObjectManager().getSubscribePage();
    }


    @Then("I am taken to the Subscribe page")
    public void iAmTakenToTheSubscribePage() {
        subscribePage.verifySubscribePageOpened();
        log.info("Subscribe page is displayed");
    }


    @And("I am presented with the below fields")
    public void iAmPresentedWithTheBelowFields(DataTable fields) throws InterruptedException {

        subscribePage.verifyFields(fields);

    }

    @And("I will need to complete Google reCAPTCHA before I can complete my request")
    public void iWillNeedToCompleteGoogleReCAPTCHABeforeICanCompleteMyRequest() {

        subscribePage.verifyRecaptcha();
    }


}
