package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;
import java.time.Duration;
import java.util.Objects;
import static org.junit.Assert.assertEquals;

//класс главной страницы
public class MainPage {
    private final WebDriver driver;

    //Изображение скутера
    private final By scooterImage = By.cssSelector("img[src='/assets/scooter.png']");
    //Кнопка Принять куки
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    //Верхняя кнопка Заказать
    private final By topOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    //Нижняя кнопка Заказать
    private final By lowerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //раздел Вопросы о важном
    private final By sectionFAQ = By.cssSelector(".Home_FAQ__3uVm4");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(Constants.RESOURCE_URL);
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void clickTopOrderButton(){
        driver.findElement(topOrderButton).click();
    }

    public void clickLowerOrderButton(){
        WebElement lowerButton  = driver.findElement(lowerOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",lowerButton);
        waitForVisibilityOfLowerOrderButton();
        lowerButton.click();
    }

    public void clickOrderButton(String button){
        if (Objects.equals(button, "top")) {
            clickTopOrderButton();
        } else {
            clickLowerOrderButton();
        }
    }

    public void waitForLoadScooterImage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(driver -> (driver.findElement(scooterImage).getSize().getWidth() > 0));
    }

    public void waitForVisibilityOfLowerOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(driver -> (driver.findElement(lowerOrderButton).isEnabled()));
    }

    public void scrollToFAQSection() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(sectionFAQ));
    }

    public void waitForLoadAnswer(By actualAnswer) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(actualAnswer)));
    }

    public void checkCorrectAnswer(int questionNum, String textAnswer) {
        String locatorQuestion = String.format(".//div[@id='accordion__heading-%s' and @class='accordion__button']", questionNum);
        //Вопрос из раздела Вопросы о важном
        By actualQuestion = By.xpath(locatorQuestion);

        String locatorAnswer = String.format(".//div[@id='accordion__panel-%s']/p", questionNum);
        //Ответ из раздела Вопросы о важном
        By actualAnswer = By.xpath(locatorAnswer);

        driver.findElement(actualQuestion).click();
        waitForLoadAnswer(actualAnswer);
        assertEquals("Ответ не соответствует вопросу", textAnswer, driver.findElement(actualAnswer).getText());
    }
}
