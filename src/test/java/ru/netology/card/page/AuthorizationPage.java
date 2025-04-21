package ru.netology.card.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.card.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {
        private SelenideElement codeField = $("[data-test-id=code] input").shouldBe(Condition.visible);
        private SelenideElement verifyButton = $("[data-test-id=action-verify]").shouldBe(Condition.visible);

        public AuthorizationPage() {
            codeField.shouldBe(Condition.visible);
        }

        public PersonalAccountPage validVerify(DataHelper.VerificationCode verificationCode) {
            codeField.setValue(verificationCode.getCode());
            verifyButton.click();
            return new PersonalAccountPage();
        }
    }
