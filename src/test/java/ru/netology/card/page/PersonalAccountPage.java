package ru.netology.card.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.card.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

    public class PersonalAccountPage {
        private ElementsCollection cards = $$(".list__item div");
        private final String balanceStart = "баланс: ";
        private final String balanceFinish = " р.";
        private final SelenideElement heading = $("[data-test-id=dashboard]").shouldBe(Condition.visible);;

        public PersonalAccountPage() {
            heading.shouldBe(Condition.visible);
        }

        public int getCardBalance(int index) {
            var text = cards.get(index).getText();
            return extractBalance(text);
        }

        public OperationsPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
            cards.findBy(attribute("data-test-id", cardInfo.getTestId())).$("button").click();
            return new OperationsPage();
        }

        private int extractBalance(String text) {
            val start = text.indexOf(balanceStart);
            val finish = text.indexOf(balanceFinish);
            val value = text.substring(start + balanceStart.length(), finish);
            return Integer.parseInt(value);
        }

}
