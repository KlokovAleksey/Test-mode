package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {

    private DataGenerator (){
    }

    public static class Registration {

        private Registration() {}


        public static RegistrationDto generateStatusActiveUser() {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(),
                    "active"
            );
        }

        public static RegistrationDto generateStatusBlockedUser() {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(),
                    "blocked"
            );
        }
    }

    public static class GenerateUser {
        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        static void setUpAll(RegistrationDto registrationDto) {
            given() // "дано"
                    .spec(requestSpec) // указываем, какую спецификацию используем
                    .body(registrationDto) // передаём в теле объект, который будет преобразован в JSON
                    .when() // "когда"
                    .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                    .then() // "тогда ожидаем"
                    .statusCode(200); // код 200 OK
        }
    }
}

