package stellaburgers.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import stellaburgers.Endpoints;
import stellaburgers.model.User;

import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .body(user)
                .when()
                .post(Endpoints.REGISTER_USER)
                .then();
    }

    @Step("Авторизация юзера")
    public ValidatableResponse login(User user) {
        return given()
                .body(user)
                .when()
                .post(Endpoints.LOGIN_USER)
                .then();
    }

    @Step("Удаление юзера")
    public ValidatableResponse deleteUser(User user) {
        return given()
                .header("accessToken", user.getAccessToken())
                .when()
                .delete(Endpoints.DELETE_USER)
                .then();
    }

}
