package ru.practicum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.MainPage;
import ru.practicum.pages.ModalWindowPage;
import ru.practicum.pages.OrderFormAboutRentPage;
import ru.practicum.pages.OrderFormForWhomPage;
import ru.practicum.util.Constants;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    private final String orderButton;
    private final String username;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String deliveryDate;
    private final String rentalDays;
    private final String color;
    private final String comment;

    public OrderScooterTest(String button, String username, String surname, String address, String station, String phone, String deliveryDate, String rentalDays, String color, String comment) {
        this.orderButton = button;
        this.username = username;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalDays = rentalDays;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {Constants.BUTTON1, Constants.NAME1, Constants.SURNAME1, Constants.ADDRESS1, Constants.STATION1, Constants.PHONE1, Constants.DELIVERY_DATE1, Constants.RENTAL_DURATION1, Constants.SCOOTER_COLOR1, Constants.COMMENT1},
                {Constants.BUTTON2, Constants.NAME2, Constants.SURNAME2, Constants.ADDRESS2, Constants.STATION2, Constants.PHONE2, Constants.DELIVERY_DATE2, Constants.RENTAL_DURATION2, Constants.SCOOTER_COLOR2, Constants.COMMENT2},
        };
    }

    @Test
    public void makeOrderWithCorrectData() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();

        MainPage mainPageObj = new MainPage(driver);
        mainPageObj.openMainPage();
        mainPageObj.waitForLoadScooterImage();
        mainPageObj.clickCookieButton();
        mainPageObj.clickOrderButton(orderButton);
        OrderFormForWhomPage forWhomObj = new OrderFormForWhomPage(driver);
        forWhomObj.waitForLoadOrderUserForm();
        forWhomObj.enterPersonalData(username, surname, address, station, phone);
        OrderFormAboutRentPage aboutRentObj = new OrderFormAboutRentPage(driver);
        aboutRentObj.waitForLoadHeadingAboutRent();
        aboutRentObj.enterRentalData(deliveryDate, rentalDays, color, comment);
        ModalWindowPage modalWindowObj = new ModalWindowPage(driver);
        modalWindowObj.clickYesButton();
        modalWindowObj.waitForLoadMessage();
        modalWindowObj.checkOrderMessage();
    }
}
