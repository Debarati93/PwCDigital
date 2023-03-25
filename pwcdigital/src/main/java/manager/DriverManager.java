package manager;

import enums.DriverType;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//This class is used to handle all the driver related function.
public class DriverManager {

    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static Logger log = LogManager.getLogger(DriverManager.class);
    private String driver_path;

    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
        driver_path = FileReaderManager.getInstance().getConfigReader().getDriverPath();
    }

    //This will return the current driver instance.
    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    //This will create a new driver instance.
    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    //This will create a remote driver instance
    private WebDriver createRemoteDriver() {
        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                ChromeOptions options = new ChromeOptions();
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    //This will create a local driver instance
    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX : driver = new FirefoxDriver();
                break;
            case CHROME :
                WebDriverManager.chromedriver().setup();
                //System.setProperty(CHROME_DRIVER_PROPERTY,driver_path);
                String downloadFilepath = System.getProperty("user.dir") + "\\"+ "data"+"\\"+"reports\\download";
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("download.default_directory", downloadFilepath);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                cap.setCapability(ChromeOptions.CAPABILITY, options);

                try
                {
                    driver = new ChromeDriver(cap);
                    log.info("Opening browser is successful with browser " + driverType);
                }
                catch (Exception e)
                {
                    log.error("Opening browser is failed with message " + e);
                    throw e;
                }
                break;
            case INTERNETEXPLORER : driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("Wrong Choice");
                break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        return driver;
    }

    //This will close all the driver instance.
    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}
