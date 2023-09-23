package ru.netology.selenide;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class ComplexElementsTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void meetingThroughWeek() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("ма");
        $$(".menu-item__control").findBy(Condition.text("Махачкала")).click();
        String meetingDay = generateDate(7, "d");
        String meetingDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateDate(3, "MM").equals(generateDate(7, "MM"))) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").findBy(Condition.text(meetingDay)).click();
        $("[data-test-id='name'] input").setValue("Иван Ефремов");
        $("[data-test-id='phone'] input").setValue("+71234567897");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    public void meetingThrough13Days() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("ма");
        $$(".menu-item__control").findBy(Condition.text("Махачкала")).click();
        String meetingDay = generateDate(13, "d");
        String meetingDate = generateDate(13, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateDate(3, "MM").equals(generateDate(13, "MM"))) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").findBy(Condition.text(meetingDay)).click();
        $("[data-test-id='name'] input").setValue("Иван Ефремов");
        $("[data-test-id='phone'] input").setValue("+71234567897");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }
}


