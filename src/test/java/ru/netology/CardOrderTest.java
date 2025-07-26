package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "http://localhost:7777";
        Configuration.browser = "chrome";
        Configuration.remote = "http://localhost:4444/wd/hub"; // Для Selenium Grid
        Configuration.timeout = 10000;
        Configuration.headless = true;
    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}