package Tests;

import Tests.utils.AllureSelenide;
import com.andrii_yavtushenko.gmail.page.EmailPage;
import com.andrii_yavtushenko.gmail.page.InboxPage;
import com.andrii_yavtushenko.gmail.page.SignInPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.andrii_yavtushenko.gmail.Utils.waitUntilMailBoxIsLoaded;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;



public class TestGmail {
    private final String USER_EMAIL = "andreytestmail@gmail.com";
    private final String USER_PASSWORD = "difficultpassword";
    private String subject = RandomStringUtils.randomAlphabetic(8);
    private String textMessage = "Text of message";

    @BeforeClass
    public void setUp() throws Exception {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeTest
    public void init() {
        Configuration.browser = "chrome";
        Configuration.timeout = 6000;
    }

    @BeforeMethod
    public void openMainPage() {
        open("https://mail.google.com/mail/");
    }

    @Step
    @Test
    @Description("Login with valid data and send email")
    public void sendLetter() throws InterruptedException {
        SignInPage signInPage = new SignInPage();
        InboxPage inboxPage = new InboxPage();
        signInPage.login(USER_EMAIL, USER_PASSWORD);
        System.out.println(url());
        waitUntilMailBoxIsLoaded();
        inboxPage
                .openNewMessagesForm()
                .sendEmail(subject, textMessage, USER_EMAIL);
        Assert.assertEquals(inboxPage.getInformSending().text(), "Просмотреть сообщение");

    }

    @Step
    @Test(dependsOnMethods = {"sendLetter"})
    @Description("Check last received letter")
    public void checkLetter() throws InterruptedException {
        InboxPage inboxPage = new InboxPage();
        EmailPage emailPage = inboxPage.openFirstEmail();
        waitUntilMailBoxIsLoaded();
        Assert.assertEquals(emailPage.getTextSenderEmail(), USER_EMAIL);
        Assert.assertEquals(emailPage.getTextSubjectOfEmailSend(), subject);
        Assert.assertEquals(emailPage.getTextOfEmailSend(), textMessage);
    }

    @AfterTest
    public void close() {
        closeWebDriver();
    }
}
