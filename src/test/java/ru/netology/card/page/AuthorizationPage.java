package ru.netology.card.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.card.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {
        private SelenideElement codeField = $("[data-test-id=code] input");
        private SelenideElement verifyButton = $("[data-test-id=action-verify]");

        public AuthorizationPage() {
            codeField.shouldBe(visible);
        }

        public PersonalAccountPage validVerify(DataHelper.VerificationCode verificationCode) {
            codeField.setValue(verificationCode.getCode());
            verifyButton.click();
            return new PersonalAccountPage();
        }
    }
