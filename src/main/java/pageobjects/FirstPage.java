package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

import static common.WebDriverUtils.LOGGER;

public class FirstPage extends LoadableComponent<FirstPage> {

    private WebDriver driver;

    @FindBy(className = "quantumWizButtonPaperbuttonLabel")
    @CacheLookup
    private WebElement submit;

    @FindAll(@FindBy(className = "freebirdFormviewerViewItemsCheckboxLabel"))
    @CacheLookup
    private List<WebElement> checkboxes;

    @FindBy(className = "quantumWizTextinputPaperinputInput")
    @CacheLookup
    private WebElement dateInput;

    @FindBy(name = "entry.1864473569")
    @CacheLookup
    private WebElement monthInput;

    @FindBy(className = "freebirdFormviewerViewItemsTextTextItem")
    @CacheLookup
    private WebElement thirdQuersionContainer;

    public FirstPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Fill input date (second question)
     *
     * @param date date to fill
     */
    public void fillInputDate(String date) {
        LOGGER.info("Fill date with date: " + date);
        dateInput.sendKeys(date);
    }

    /**
     * Get text from month input
     *
     * @return String current month
     */
    public String getMonth() {
        return monthInput.getAttribute("value");
    }

    /**
     * Fill month (third question)
     *
     * @param month month to fill
     */
    public void setMonth(String month) {
        LOGGER.info("Fill month with value: " + month);
        monthInput.clear();
        monthInput.sendKeys(month);
    }

    /**
     * Submit results
     *
     * @return SecondPage
     */
    public SecondPage goForward() {
        submit.click();
        return new SecondPage(driver).get();
    }

    /**
     * Select checkboxes (first questions)
     *
     * @param text            text to select
     * @param selectOnlyFirst find only first match or all of them
     */
    public void selectCheckBoxes(String text, boolean selectOnlyFirst) {
        LOGGER.info("Select checkboxes: " + text);
        for (WebElement element : checkboxes) {
            if (element.getText().equals(text)) {
                element.click();
                if (selectOnlyFirst)
                    return;
            }
        }
    }

    /**
     * Check third question is required
     *
     * @return true if third question is required
     */
    public boolean isThirdQuestionRequired() {
        try {
            thirdQuersionContainer.findElement(By.className("freebirdFormviewerViewItemsItemRequiredAsterisk"));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void load() {
        LOGGER.info("FirstPage.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on right page: " + url, url.endsWith("/viewform") || url.endsWith("/formResponse"));
    }
}