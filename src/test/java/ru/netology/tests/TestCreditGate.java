package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DashboardPage;
import ru.netology.data.DataHelper;

import java.sql.SQLException;

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

}
