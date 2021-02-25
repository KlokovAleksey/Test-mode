package ru.netology;


import com.codeborne.selenide.Condition;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class AuthTest {


    @Test
    public void shouldLoginUserStatusActive(){
        open("http://localhost:9999");
        RegistrationDto registrationDto = DataGenerator.Registration.generateStatusActiveUser();
        DataGenerator.GenerateUser.setUpAll(registrationDto);
        $("[name=login]").setValue(registrationDto.getLogin());
        $("[name=password]").setValue(registrationDto.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    public void shouldLoginUserStatusBlocked(){
        open("http://localhost:9999");
        RegistrationDto registrationDto = DataGenerator.Registration.generateStatusBlockedUser();
        DataGenerator.GenerateUser.setUpAll(registrationDto);
        $("[name=login]").setValue(registrationDto.getLogin());
        $("[name=password]").setValue(registrationDto.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);
    }

    @Test
    public void shouldInvalidLoginActiveUser(){
        open("http://localhost:9999");
        RegistrationDto registrationDto = DataGenerator.Registration.generateStatusActiveUser();
        DataGenerator.GenerateUser.setUpAll(registrationDto);
        $("[name=login]").setValue("ivan");
        $("[name=password]").setValue(registrationDto.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин")).shouldBe(Condition.visible);
    }

    @Test
    public void shouldInvalidPasswordActiveUser(){
        open("http://localhost:9999");
        RegistrationDto registrationDto = DataGenerator.Registration.generateStatusActiveUser();
        DataGenerator.GenerateUser.setUpAll(registrationDto);
        $("[name=login]").setValue(registrationDto.getPassword());
        $("[name=password]").setValue(registrationDto.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }
}
