package com.andrii_yavtushenko.gmail.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.andrii_yavtushenko.gmail.Utils.clicker;
import static com.andrii_yavtushenko.gmail.Utils.sendText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class SignInPage {

    private SelenideElement identifierId = $x("//*[@id='identifierId']");
    private SelenideElement password = $x("//*[@id='password']//input");
    private SelenideElement passwordNextButton = $(By.id("passwordNext"));
    private SelenideElement identifierNextButton = $(By.id("identifierNext"));


    public void login(String loginUser, String passwordUser) {
        clicker(identifierId);
        sendText(identifierId, loginUser);
        clicker(identifierNextButton);
        sendText(password, passwordUser);
        clicker(passwordNextButton);
    }
}

