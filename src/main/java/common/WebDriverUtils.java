package common;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebDriverUtils {

    public static Logger LOGGER = LoggerFactory.getLogger("ActumSeleniumTest");
    public static WebDriver driver;


    public static void initializeLocalBrowser(String type) {
        if (type.equalsIgnoreCase("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.proxy.http", "localhost");
            profile.setPreference("network.proxy.http_port", "3128");
            driver = new FirefoxDriver(profile);
        } else if (type.equalsIgnoreCase("internetExplorer")) {
            driver = new InternetExplorerDriver();
        } else if (type.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            LOGGER.error("Invalid browser type. Cannot initialize.");
            throw new InvalidArgumentException("Invalid browser type. Cannot initialize.");
        }
    }


}