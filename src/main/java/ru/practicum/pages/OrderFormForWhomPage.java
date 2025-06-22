package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.util.Constants;

import java.time.Duration;

//класс страницы оформления заказа Для кого
public class OrderFormForWhomPage {
    private final WebDriver driver;

    //Форма ввода данных пользователя
    private final By userForm = By.cssSelector(".Order_Content__bmtHS");
    //Поле ввода имени
    private final By usernameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Имя']");
    //Поле ввода фамилии
    private final By surnameField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By addressField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции
    private final By stationField = By.cssSelector(".select-search__input[placeholder='* Станция метро']");
    //Выпадающий список станций
    private final By stationSelect = By.cssSelector(".select-search__select");
    //Поле ввода телефона
    private final By phoneField = By.cssSelector(".Input_Input__1iN_Z[placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public OrderFormForWhomPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setStation(String station) {
        driver.findElement(stationField).sendKeys(station);
        driver.findElement(stationSelect).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterPersonalData(String username, String surname, String address, String station, String phone) {
        setUserName(username);
        setSurname(surname);
        setAddress(address);
        setStation(station);
        setPhone(phone);
        clickNextButton();
    }

    public void waitForLoadOrderUserForm() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.WAITING_SECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(userForm)));
    }
}
