package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.UserData;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {


    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        open("http://localhost:9999");
        UserData userData = DataGenerator.Registration.generateUser("ru");
        $("[data-test-id=city] input").setValue(userData.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String scheduledDate = DataGenerator.generateDate(3);
        $("[data-test-id=date] input").setValue(scheduledDate);
        $("[data-test-id=name] input").setValue(userData.getName());
        $("[data-test-id=phone] input").setValue(userData.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible).shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + scheduledDate), Duration.ofSeconds(15));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.DELETE);
        String rescheduledDate = DataGenerator.generateDate(14);
        $("[data-test-id=date] input").setValue(rescheduledDate);
        $(".button").shouldHave(Condition.text("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible).shouldHave(Condition.text("Необходимо подтверждение" + " У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15));
        $("[data-test-id=replan-notification] .button").shouldHave(Condition.text("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible).shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + rescheduledDate), Duration.ofSeconds(15));
    }
}
