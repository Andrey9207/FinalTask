package com.andrii_yavtushenko.gmail.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.andrii_yavtushenko.gmail.Utils.clicker;
import static com.andrii_yavtushenko.gmail.Utils.sendText;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NewMessageForm {

    private SelenideElement subjectField = $(byXpath(".//input[@name='subjectbox']"));
    private SelenideElement recipientsField = $(byName("to"));
    private SelenideElement messageTextField = $(".editable");
    private SelenideElement sendButton = $x("//table[@role='group']//div[@role='button']");

    public InboxPage sendEmail(String subject, String textMessage, String recipients) {
        sendText(recipientsField, recipients);
        sendText(subjectField, subject);
        sendText(messageTextField, textMessage);
        clicker(sendButton);
        return Selenide.page(InboxPage.class);
    }
}
