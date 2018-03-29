package com.andrii_yavtushenko.gmail;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.log4j.Logger.getRootLogger;

public class Utils {

    public static Logger logger = getRootLogger();

    public static void clicker(SelenideElement element) {
        element.click();
        logger.info("Clicked on element: " + element.getTagName() + " - '" + element.getSearchCriteria() + "'");
    }

    public static void sendText(SelenideElement element, String text) {
        element.click();
        element.sendKeys(text);
        element.pressTab();
        logger.info("Send text  '" + text + "' to - " + element.getSearchCriteria());
    }

    public static void waitUntilMailBoxIsLoaded() {
        $(byXpath("//*[@href='#inbox']")).waitUntil(visible, 30000);
    }

}
