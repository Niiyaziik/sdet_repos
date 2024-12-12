import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ULSUTest {

    @BeforeClass
    void init() {
        Configuration.baseUrl = "https://www.ulsu.ru/ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 5000;
    }

    @Test
    public void test1() {
        open("");
        $(By.cssSelector("a[href='https://portal.ulsu.ru']"))
                .shouldBe(visible)
                .click();
        $x("//h2")
                .shouldBe(visible)
                .shouldHave(text("Электронная информационно-образовательная среда \"Ульяновского государственного университета\""));
        sleep(5000);
    }

    @Test
    public void test2() {
        open("");
        $(By.cssSelector("a[data-lang='en']"))
                .shouldBe(visible)
                .click();
        webdriver().shouldHave(url("https://www.ulsu.ru/en/"));
        $(By.xpath("//*[contains(@class, 'g-name-university m-name-university js-name-university')]"))
                .shouldHave(text("Ulyanovsk State University"))
                .shouldBe(visible);
        $(By.cssSelector("a[href='https://portal.ulsu.ru']"))
                .shouldNot(exist);
        sleep(5000);
    }

}
