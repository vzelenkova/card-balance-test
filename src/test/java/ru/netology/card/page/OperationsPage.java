package ru.netology.card.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.card.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class OperationsPage {
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']").shouldBe(Condition.visible);;
    private final SelenideElement amountInput = $("[data-test-id='amount'] input").shouldBe(Condition.visible);;
    private final SelenideElement fromInput = $("[data-test-id='from'] input").shouldBe(Condition.visible);;
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]").shouldBe(Condition.visible);;
    private final SelenideElement errorButton = $("[data-test-id=error-notification] button").shouldBe(Condition.visible);;


    public OperationsPage() {
        transferHead.shouldBe(Condition.visible);
    }

    public LoginPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new LoginPage();
    }

    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void errorLimit() {
        $("[data-test-id=error-notification]").should(Condition.exactText("Ошибка"));
    }

    public void invalidCard() {
        $("[data-test-id=error-notification]").should(Condition.text("Ошибка! "));
    }
}
