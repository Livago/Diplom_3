package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import stellaburgers.config.DriverConfig;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import stellaburgers.model.User;
import stellaburgers.pages.LoginPage;
import stellaburgers.pages.MainPage;
import stellaburgers.pages.RegisterPage;
import stellaburgers.steps.UserSteps;


public class RegistrationTest extends AbstractTest {
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
    }

    @Test
    @DisplayName("проверка успешной регистрации")
    public void signUpTest() {
        user = new User();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignUpBtn();
        RegisterPage registerPage = new RegisterPage(driver);
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        registerPage.signUpUser(user);
        loginPage.login(user);
        String buttonText = mainPage.getCreateOrderButtonText();
        Assert.assertEquals("Оформить заказ", buttonText);
    }

    @Test
    @DisplayName("проверка регистрации с некорректным паролем")
    public void signUpWithIncorrectPswrdTest() {
        user = new User();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignUpBtn();
        RegisterPage registerPage = new RegisterPage(driver);
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword("Aa12");
        registerPage.signUpUser(user);
        String errorText = registerPage.getIncorrectPswrdText();
        Assert.assertEquals("Некорректный пароль", errorText);
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
