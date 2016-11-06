package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

import static common.WebDriverUtils.LOGGER;

public class ThirdPage extends LoadableComponent<ThirdPage> {

    private WebDriver driver;

    @FindAll(@FindBy(className = "quantumWizButtonPaperbuttonLabel"))
    private List<WebElement> buttons;

    @FindAll(@FindBy(className = "freebirdFormviewerViewItemsRadioLabel"))
    private List<WebElement> radioButtons;

    public ThirdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Select radiobutton "is it done?"
     * @param select what to select
     */
    public void selectDone(String select) {
        LOGGER.info("Select done with value: " + select);
        for (WebElement radioButton : radioButtons) {
            if (radioButton.getText().equals(select)) {
                radioButton.click();
                return;
            }
        }
    }

    /**
     * Click back button
     *
     * @return FirstPage
     */
    public FirstPage goBack() {
        buttons.get(0).click();
        return new FirstPage(driver).get();
    }

    /**
     * Click Next button
     *
     * @return Confirmation page
     */
    public ConfirmationPage goForward() {
        buttons.get(1).click();
        return new ConfirmationPage(driver).get();
    }

    @Override
    protected void load() {
        LOGGER.info("ThirdPage.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on right page: " + url, url.endsWith("/formResponse"));
    }
}
