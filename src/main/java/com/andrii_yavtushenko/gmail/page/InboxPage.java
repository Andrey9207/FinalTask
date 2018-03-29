package com.andrii_yavtushenko.gmail.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.andrii_yavtushenko.gmail.Utils.clicker;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class InboxPage {


    private SelenideElement buttonCompose = $(byXpath("//div[@role='navigation']/parent::div//child::div[@role='button']"));
    private ElementsCollection emails = $$x("//*[@role='tabpanel']//tbody/tr");
    private SelenideElement informSending = $(byXpath("//*[@id='link_vsm']"));
    private String emailSubjectLocator = "//span[@class='bog'])";

    public SelenideElement getInformSending() {
        return informSending;
    }

    public ElementsCollection getAllEmailsOnPage() {
        return emails;
    }

    public EmailPage openFirstEmail() {
        SelenideElement email = getAllEmailsOnPage()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Could not find email"));
        clicker(email);
        return Selenide.page(EmailPage.class);
    }

    public NewMessageForm openNewMessagesForm() {
        clicker(buttonCompose);
        return Selenide.page(NewMessageForm.class);
    }
}
