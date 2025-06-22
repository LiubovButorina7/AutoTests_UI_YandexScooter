package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;

import java.time.Duration;

//класс страницы оформления заказа Про аренду
public class OrderFormAboutRentPage {
    private final WebDriver driver;

    //Заголовок формы Про аренду
    private final By formHeading = By.xpath(".//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
    //Поле ввода даты доставки
    private final By deliveryDateField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Когда привезти самокат']");
    //Поле срока аренды
    private final By rentalPeriodField = By.cssSelector(".Dropdown-root");
    //Поле ввода комментария
    private final By commentField = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='Комментарий для курьера']");
    //Кнопка Заказать
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    public OrderFormAboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDeliveryDate(String deliveryDate) {
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(formHeading).click();
    }

    public void setRentalPeriod(String rentalDays) {
        driver.findElement(rentalPeriodField).click();
        String locator = String.format(".//div[@class='Dropdown-option' and text()='%s']", rentalDays);
        //Значение из выпадающего списка продолжительности аренды
        By days = By.xpath(locator);
        driver.findElement(days).click();
    }

    public void setScooterColor(String color) {
        //Поле выбора цвета
        By checkboxColor = By.id(String.format("%s", color));
        driver.findElement(checkboxColor).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void enterRentalData(String deliveryDate, String rentalDays, String color, String comment) {
        setDeliveryDate(deliveryDate);
        setRentalPeriod(rentalDays);
        setScooterColor(color);
        setComment(comment);
        clickOrderButton();
    }

    public void waitForLoadHeadingAboutRent() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(formHeading)));
    }
}
