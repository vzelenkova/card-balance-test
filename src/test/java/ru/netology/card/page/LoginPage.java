package ru.netology.card.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.card.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;


    public class LoginPage {
        private SelenideElement loginField = $("[data-test-id=login] input").shouldBe(Condition.visible);;
        private SelenideElement passwordField = $("[data-test-id=password] input").shouldBe(Condition.visible);;
        private SelenideElement loginButton = $("[data-test-id=action-login]").shouldBe(Condition.visible);;

        public AuthorizationPage validLogin(DataHelper.AuthInfo info) {
            loginField.setValue(info.getLogin());
            passwordField.setValue(info.getPassword());
            loginButton.click();
            return new AuthorizationPage();
        }

}
