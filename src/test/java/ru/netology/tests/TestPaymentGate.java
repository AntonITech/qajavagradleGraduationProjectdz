package ru.netology.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DashboardPage;

public class TestPaymentGate {
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUpPage() {
        dashboardPage = new DashboardPage();
    }
}
