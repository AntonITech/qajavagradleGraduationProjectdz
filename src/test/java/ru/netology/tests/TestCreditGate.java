package ru.netology.tests;

import java.sql.SQLException;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.data.DashboardPage;
import ru.netology.data.DataHelper;
import ru.netology.data.Status;

public class TestCreditGate {
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
    @DisplayName("Оплата по активной карте, покупка в кредит, валидные данные")
    void shouldPayByApprovedCardInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
    }

    @Test
    @DisplayName("Оплата по неактивной карте, покупка в кредит, валидные данные")
    void shouldNoPayByDeclinedCardInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444442");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по неизвестной карте, покупка в кредит, валидные данные")
    void shouldNoPayByUnknownCardInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444443");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером карты, покупка в кредит")
    void shouldNoPayInvalidCardNumberFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("3333 2323 DSDF ASSD");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером месяца, покупка в кредит")
    void shouldNoPayInvalidMonthFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("13");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongDate();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным номером года, покупка в кредит")
    void shouldNoPayInvalidYearFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("18");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageOverDate();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным полем владелец, покупка в кредит")
    void shouldNoPayInvalidCardOwnerFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Bdfy 1213 Петров 12");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c невалидным полем CVV, покупка в кредит")
    void shouldNoPayInvalidCVVFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("12D");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageError();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером карты, покупка в кредит")
    void shouldNoPayEmptyCardNumberFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером месяца, покупка в кредит")
    void shouldNoPayEmptyMonthFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым номером года, покупка в кредит")
    void shouldNoPayEmptyYearFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по карте c пустым полем владелец, покупка в кредит")
    void shouldNoPayEmptyCardOwnerFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageRequiredField();
    }

    @Test
    @DisplayName("Оплата по карте c пустым полем CVV, покупка в кредит")
    void shouldNoPayEmptyCVVFieldInCredit() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageWrongFormat();
    }

    @Test
    @DisplayName("Оплата по активной карте, покупка в кредит, валидные данные, проверка записи в БД")
    void shouldPayByApprovedCardInCreditStatusInDB() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444441");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
        DataHelper.checkCreditStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Оплата по неактивной карте, покупка в кредит, валидные данные, проверка записи в БД")
    void shouldPayByDeclinedCardInCreditStatusInDB() throws SQLException {
        dashboardPage.buyOnCredit();
        dashboardPage.setCardNumber("4444444444444442");
        dashboardPage.setCardMonth("08");
        dashboardPage.setCardYear("22");
        dashboardPage.setCardOwner("Ivan Petrov");
        dashboardPage.setCardCVV("999");
        dashboardPage.pushСontinueButton();
        dashboardPage.checkMessageSuccess();
        DataHelper.checkCreditStatus(Status.DECLINED);
    }
}
