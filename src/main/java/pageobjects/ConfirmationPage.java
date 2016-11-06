package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static common.WebDriverUtils.LOGGER;

public class ConfirmationPage extends LoadableComponent<ConfirmationPage> {

    private WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccess() {
        try {
            driver.findElement(By.className("freebirdFormviewerViewResponseConfirmationMessage"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void load() {
        LOGGER.info("Confirmation.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        // Check that confirmation message show
        Assert.assertTrue("Confirmation message not shown!", isSuccess());
    }
}
