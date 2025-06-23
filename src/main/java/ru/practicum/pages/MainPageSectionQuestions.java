package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

//класс секции с вопросами на главной странице
public class MainPageSectionQuestions extends MainPage{
    //раздел Вопросы о важном
    private final By sectionFAQ = By.cssSelector(".Home_FAQ__3uVm4");
    //Вопрос из раздела Вопросы о важном
    private final By actualQuestion;
    //Ответ из раздела Вопросы о важном
    private final By actualAnswer;

    public MainPageSectionQuestions(WebDriver driver, int questionNum) {
        super(driver);

        this.actualQuestion = By.xpath(String.format(".//div[@id='accordion__heading-%s' and @class='accordion__button']", questionNum));
        this.actualAnswer = By.xpath(String.format(".//div[@id='accordion__panel-%s']/p", questionNum));
    }

    public void scrollToFAQSection() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(sectionFAQ));
    }

    public void waitForLoadAnswer() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(actualAnswer)));
    }

    public void checkCorrectAnswer(String textAnswer) {
        driver.findElement(actualQuestion).click();
        waitForLoadAnswer();
        assertEquals("Ответ не соответствует вопросу", textAnswer, driver.findElement(actualAnswer).getText());
    }
}
