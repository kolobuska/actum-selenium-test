package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

import static common.WebDriverUtils.LOGGER;

public class SecondPage extends LoadableComponent<SecondPage> {

    private WebDriver driver;

    @FindBy(className = "docssharedWizSelectPaperselectOptionList")
    @CacheLookup
    private WebElement colorDropDown;

    @FindAll(@FindBy(className = "docssharedWizSelectPaperselectOption"))
    @CacheLookup
    private List<WebElement> colorOptions;

    @FindAll(@FindBy(className = "quantumWizButtonPaperbuttonLabel"))
    @CacheLookup
    private List<WebElement> buttons;

    @FindBy(className = "isSelected")
    @CacheLookup
    private WebElement selectedColor;

    @FindBy(className = "quantumWizTextinputPapertextareaInput")
    @CacheLookup
    private WebElement moviesTextArea;

    public SecondPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Get values from movies textarea
     *
     * @return String with favourite movies
     */
    public String getMovies() {
        return moviesTextArea.getText();
    }

    public void setMovies(List list) {
        LOGGER.info("Set movies: " + list);
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            moviesTextArea.sendKeys((String) iter.next());

            // If not last element - click <Enter> button
            if (iter.hasNext())
                moviesTextArea.sendKeys(Keys.ENTER);
        }
    }

    /**
     * Select color in color dropdown
     *
     * @param color color to select
     */
    public void selectColor(String color) {

        LOGGER.info("Set color: " + color);
        colorDropDown.click();

        // Wait till dropdown popup opened
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("isOpen")));

        for (WebElement element : colorOptions) {
            if (element.getText().equals(color)) {
                element.click();
                break;
            }
        }

        // Make sure select popup is closed as it may overlap the elements
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("docssharedWizSelectPaperselectPopup")));

    }

    /**
     * Get color from color input
     *
     * @return String color from input
     */
    public String getColor() {
        return selectedColor.getAttribute("data-value");
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
     * @return ThirdPage
     */
    public ThirdPage goForward() {
        buttons.get(1).click();
        return new ThirdPage(driver).get();
    }

    @Override
    protected void load() {
        LOGGER.info("SecondPage.load()...");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertTrue("Not on right page: " + url, url.endsWith("/formResponse"));
    }
}
