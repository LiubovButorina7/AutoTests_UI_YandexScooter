package ru.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPageSectionQuestions;
import ru.practicum.util.Constants;

@RunWith(Parameterized.class)
public class FAQTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private final int questionNum;
    private final String textAnswer;

    public FAQTest(int questionNum, String textAnswer) {
        this.questionNum = questionNum;
        this.textAnswer = textAnswer;
    }

    @Parameterized.Parameters(name = "Тестовые данные {index}: {0} {1}" )
    public static Object[][] getQuestionsData() {
        return new Object[][] {
                { 0, Constants.ANSWER_PAYMENT},
                { 1, Constants.ANSWER_SEVERAL_SCOOTERS},
                { 2, Constants.ANSWER_CALCULATION_RENTAL_DURATION},
                { 3, Constants.ANSWER_ORDER_CURRENT_DAY},
                { 4, Constants.ANSWER_EXTEND_ORDER},
                { 5, Constants.ANSWER_SCOOTER_CHARGER},
                { 6, Constants.ANSWER_CANCEL_ORDER},
                { 7, Constants.ANSWER_DELIVERY_OUTSIDE_CITY},
        };
    }

    @Test
    public void checkFAQCorrectAnswersTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        MainPageSectionQuestions mainPageSectionQuestionsObj = new MainPageSectionQuestions(driver, questionNum);
        mainPageSectionQuestionsObj.openMainPage();
        mainPageSectionQuestionsObj.waitForLoadScooterImage();
        mainPageSectionQuestionsObj.clickCookieButton();
        mainPageSectionQuestionsObj.scrollToFAQSection();
        mainPageSectionQuestionsObj.checkCorrectAnswer(textAnswer);
    }

}


