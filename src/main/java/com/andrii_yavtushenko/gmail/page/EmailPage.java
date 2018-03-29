package com.andrii_yavtushenko.gmail.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class EmailPage {
    private SelenideElement senderEmail = $(byXpath("//*[@role='listitem']//h3/span"));
    private SelenideElement subjectOfEmailSend = $(byXpath("//table[@role='presentation']//h2"));
    private SelenideElement textOfEmailSend = $(byXpath("//*[@role='listitem']//div[@dir='ltr']"));

    public String getTextSenderEmail() {
        return senderEmail.getAttribute("email");
    }

    public String getTextSubjectOfEmailSend() {
        return subjectOfEmailSend.text();
    }

    public String getTextOfEmailSend() {
        return textOfEmailSend.text();
    }
}
