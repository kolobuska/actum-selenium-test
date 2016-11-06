import common.WebDriverUtils;
import helpers.CollectionsHelper;
import helpers.DateHelper;
import helpers.MathHelper;
import helpers.StringHelper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.ConfirmationPage;
import pageobjects.FirstPage;
import pageobjects.SecondPage;
import pageobjects.ThirdPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestClass extends WebDriverUtils {

    private final String FAV_COLOR = "Red";

    private final String[] FAV_MOVIES =
            {"Big Bang Theory",
                    "Mr.Robot",
                    "How I met Your Mother",
                    "Star Wars",
                    "Matrix",
                    "Game of Thrones"};

    private final List<String> FAV_MOVIES_LIST = new ArrayList<>(Arrays.asList(FAV_MOVIES));

    private final int N_OF_MOVIES = 3;

    @Before
    public void setUp() {
        initializeLocalBrowser("chrome");
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSeY_W-zSf2_JxR4drhbgIxdEwdbUbE4wXhxHZLgxZGiMcNl7g/viewform");
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void Test1() throws InterruptedException {

        FirstPage firstPage = new FirstPage(driver).get();

        // Fill first question
        firstPage.selectCheckBoxes("Check this", false);

        //Fill second question by random date between 5 and 30 days ago
        firstPage.fillInputDate(DateHelper.getDateFewDaysAgo(MathHelper.getRandomInt(5, 30), "dd/LLLL/yyyy"));

        // Check third question is required
        Assert.assertTrue(firstPage.isThirdQuestionRequired());

        //Set current month
        firstPage.setMonth(DateHelper.getCurrentMonth());

        // Submit first page
        SecondPage secondPage = firstPage.goForward();

        // Set movies buy 3 random movies
        secondPage.setMovies(CollectionsHelper.getRandomElementsFromList(FAV_MOVIES_LIST, N_OF_MOVIES));

        // Select favorite color
        secondPage.selectColor(FAV_COLOR);

        // Go back to first page
        firstPage = secondPage.goBack();

        // Set month with reverted value
        firstPage.setMonth(StringHelper.reverseString(firstPage.getMonth()));

        // Go forward to second page
        secondPage = firstPage.goForward();

        // Page questions are filled
        Assert.assertTrue(secondPage.getMovies().length() > 0);
        Assert.assertTrue(secondPage.getColor().length() > 0);

        // Go forward to third page
        ThirdPage thirdPage = secondPage.goForward();

        // Select "Yes" radioButton
        thirdPage.selectDone("Yes");

        // Submit form
//        ConfirmationPage confirmationPage = thirdPage.goForward();

    }
}