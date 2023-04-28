package ru.netology.data;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("app", "pass");
    }

    public static CardInfo getCardInfoApproved() {
        return new CardInfo("4444 4444 4444 4441", "APPROVED");
    }

    public static CardInfo getCardInfoDeclined() {
        return new CardInfo("44444 4444 4444 4442", "DECLINED");
    }

    public static class CardInfo {
        String cardNumber;
        String cardStatus;
    }

    public static class AuthInfo {
        String username;
        String password;
    }
}
