package ru.netology.card.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.card.page.LoginPage;
import ru.netology.card.page.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.card.data.DataHelper.*;


public class OperationsTest {
    LoginPage loginPage;
    PersonalAccountPage personalAccountPage;

    @BeforeEach
    void setup() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCode();
        personalAccountPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void transferToFirstCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = personalAccountPage.getCardBalance(0);
        var secondCardBalance = personalAccountPage.getCardBalance(1);
        var amount = generateValidAmount(personalAccountPage.getCardBalance(0));
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = personalAccountPage.selectCardToTransfer(secondCardInfo);
        loginPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = personalAccountPage.getCardBalance(0);
        var actualBalanceSecondCard = personalAccountPage.getCardBalance(1);

        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    public void transferToSecondCard() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = personalAccountPage.getCardBalance(0);
        var secondCardBalance = personalAccountPage.getCardBalance(1);
        var amount = generateValidAmount(personalAccountPage.getCardBalance(1));
        var expectedBalanceFirstCard = firstCardBalance + amount;
        var expectedBalanceSecondCard = secondCardBalance - amount;
        var transferPage = personalAccountPage.selectCardToTransfer(firstCardInfo);
        loginPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceFirstCard = personalAccountPage.getCardBalance(0);
        var actualBalanceSecondCard = personalAccountPage.getCardBalance(1);

        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    public void availableTransferAmount() {
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = personalAccountPage.getCardBalance(0);
        var secondCardBalance = personalAccountPage.getCardBalance(1);
        var amount = generateInvalidAmount(personalAccountPage.getCardBalance(0));
        var transferPage = personalAccountPage.selectCardToTransfer(secondCardInfo);
        loginPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        transferPage.verifyErrorNotification("Ошибка", true);
        
        var actualBalanceFirstCard = personalAccountPage.getCardBalance(0);
        var actualBalanceSecondCard = personalAccountPage.getCardBalance(1);

        assertEquals(firstCardBalance, actualBalanceFirstCard);
        assertEquals(secondCardBalance, actualBalanceSecondCard);
    }

}
