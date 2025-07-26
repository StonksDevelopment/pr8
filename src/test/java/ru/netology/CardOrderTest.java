package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = System.getProperty("selenide.baseUrl", "http://localhost:8888");
        Configuration.remote = System.getProperty("selenide.remote");
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.timeout = 15000;
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "true"));
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