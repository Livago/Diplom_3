package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import stellaburgers.config.DriverConfig;
import stellaburgers.model.User;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellaburgers.pages.ForgotPasswordPage;
import stellaburgers.pages.LoginPage;
import stellaburgers.pages.MainPage;
import stellaburgers.pages.RegisterPage;
import stellaburgers.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;


    public class LoginTest extends AbstractTest {
        private WebDriver driver;
        private Faker faker = new Faker();
        private User user;
        private UserSteps userSteps = new UserSteps();
        private DriverConfig driverConfig;

        @Before
        public void setUp() {
            driverConfig = new DriverConfig();
            driver = DriverConfig.getDriver();
            driverConfig.navigateToUrl();
            user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setName(faker.name().firstName());
            userSteps
                    .createUser(user)
                    .statusCode(200)
                    .body("success", is(true));
        }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    public void logInMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        String buttonText = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", buttonText);
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    public void logInPersonalAccountPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersAccBtn();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
        String buttonText = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", buttonText);
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    public void logInRegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignUpBtn();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLogInBtn();
        loginPage.login(user);
        String buttonText = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", buttonText);
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    public void logInForgotPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverRasswordBtn();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickEnterBtn();
        loginPage.login(user);
        String buttonText = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", buttonText);
    }

    @After
    public void tearDown() {
        String token = userSteps.login(user)
                .extract().body().path("accessToken");
        user.setAccessToken(token);

        if (user.getAccessToken() != null) {
            userSteps.deleteUser(user);
        }
        driverConfig.quitDriver();
    }

}
