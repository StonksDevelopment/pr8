package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FieldValidationTest {
    @BeforeAll
    static void setUpAll() {
        Configuration.headless = true;
        Configuration.timeout = 10000; // Увеличиваем таймаут до 10 секунд
        open("http://localhost:9999");
    }

    @Test
    void shouldShowErrorForUncheckedAgreement() {
        // Заполняем обязательные поля
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79270000000");

        // Не ставим галочку согласия и нажимаем кнопку
        $("button").click();

        // Вариант 1: Проверяем, что чекбокс стал невалидным
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));

        // Вариант 2: Проверяем текст ошибки (если он есть)
        // Если текст ошибки находится в другом элементе, используйте подходящий селектор
        $("[data-test-id=agreement].input_invalid")
                .shouldBe(visible)
                .shouldHave(text("Я соглашаюсь с условиями обработки"));
    }
}