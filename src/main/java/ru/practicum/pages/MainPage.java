package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;
import java.time.Duration;
import java.util.Objects;

//класс главной страницы
public class MainPage {
    protected final WebDriver driver;

    //Изображение скутера
    private final By scooterImage = By.cssSelector("img[src='/assets/scooter.png']");
    //Кнопка Принять куки
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    //Верхняя кнопка Заказать
    private final By topOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    //Нижняя кнопка Заказать
    private final By lowerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

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
}
