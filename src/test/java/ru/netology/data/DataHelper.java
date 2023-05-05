package ru.netology.data;

import org.apache.commons.dbutils.QueryRunner;
import lombok.val;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataHelper {
    private static String url = System.getProperty("db.url");
    private static String appURL = System.getProperty("app.url");
    private static String appPORT = System.getProperty("app.port");
    private static String userBD = System.getProperty("app.userDB");
    private static String password = System.getProperty("app.password");

    public static void clearAllData() throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userBD, password);
        runner.update(conn, "DELETE FROM credit_request_entity;");
        runner.update(conn, "DELETE FROM payment_entity;");
        runner.update(conn, "DELETE FROM order_entity:");
    }

    public static void checkPaymentStatus(Status status) throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userBD, password);
        val paymentDataSQL = "SELECT status FROM payment_entity;";
        val payment = runner.query(conn, paymentDataSQL, new BeanHandler<>(PaymentCard.class));
        assertEquals(status, payment.status);
    }

    public static void checkCreditStatus(Status status) throws SQLException {
        val runner = new QueryRunner();
        val conn = DriverManager.getConnection(url, userBD, password);
        val creditDataSQL = "SELECT status FROM credit_request_entity;";
        val credit = runner.query(conn, creditDataSQL, new BeanHandler<>(CreditCard.class));
        assertEquals(status, credit.status);
    }
}
