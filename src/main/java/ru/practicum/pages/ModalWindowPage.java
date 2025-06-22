package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;

import java.time.Duration;
import static org.junit.Assert.assertTrue;

//класс модального окна Хотите оформить заказ
public class ModalWindowPage {
    private final WebDriver driver;

    private final By formModal = By.cssSelector(".Order_Modal__YZ-d3");
    //Кнопка Да
    private final By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Сообщение об успешном оформлении заказа
    private final By message = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");

    public ModalWindowPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public void waitForLoadMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.presenceOfElementLocated(formModal));
    }

    public void checkOrderMessage() {
        assertTrue("Заказ не оформлен", driver.findElement(message).isDisplayed());
    }
}
