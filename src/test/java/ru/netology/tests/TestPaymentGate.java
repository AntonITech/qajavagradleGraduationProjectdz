package ru.netology.tests;

import java.sql.SQLException;

import org.junit.jupiter.api.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.pages.DashboardPage;
import ru.netology.data.DataHelper;
import ru.netology.data.Status;

public class TestPaymentGate {
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUpPage() {
        dashboardPage = new DashboardPage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() throws SQLException {
        DataHelper.clearAllData();
    }

    @AfterAll
    static void downAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Оплата по активной карте, обычная покупка, валидные данные")
    void shouldPayByApprovedCard() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Оплата по неактивной карте, обычная покупка, валидные данные")
    void shouldNoPayByDeclinedCard() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444442");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по неизвестной карте, обычная покупка, валидные данные")
    void shouldNoPayByUnknownCard() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444443");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером карты, обычная покупка")
    void shouldNoPayInvalidCardNumberField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("3333 2323 DSDF ASSD");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером месяца, обычная покупка")
    void shouldNoPayInvalidMonthField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("13");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongDate();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером года, обычная покупка")
    void shouldNoPayInvalidYearField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("18");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageOverDate();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным полем владелец, обычная покупка")
    void shouldNoPayInvalidCardOwnerField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Bdfy 1213 Петров 12");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным полем CVV, обычная покупка")
    void shouldNoPayInvalidCVVField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("12D");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером карты, обычная покупка")
    void shouldNoPayEmptyCardNumberField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером месяца, обычная покупка")
    void shouldNoPayEmptyMonthField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером года, обычная покупка")
    void shouldNoPayEmptyYearField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым полем владелец, обычная покупка")
    void shouldNoPayEmptyCardOwnerField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageRequiredField();
    }

    @Test
    @DisplayName("Оплата по карте c пустым полем CVV, обычная покупка")
    void shouldNoPayEmptyCVVField() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }


    @Test
    @DisplayName("Оплата по активной карте, обычная покупка, валидные данные, проверка записи в БД")
    void shouldPayByApprovedCardStatusInDB() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
        DataHelper.checkPaymentStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Оплата по неактивной карте, обычная покупка, валидные данные, проверка записи в БД")
    void shouldNoPayByDeclinedCardStatusInDB() throws SQLException {
        dashboardPage.buyForYourMoney();
        dashboardPage.setCardNumber("4444444444444442");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
        DataHelper.checkPaymentStatus(Status.DECLINED);
    }
}
