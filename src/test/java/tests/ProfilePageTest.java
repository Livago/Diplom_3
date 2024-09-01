package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellaburgers.config.DriverConfig;
import stellaburgers.model.User;
import stellaburgers.pages.LoginPage;
import stellaburgers.pages.MainPage;
import stellaburgers.pages.ProfilePage;
import stellaburgers.steps.UserSteps;
import static org.hamcrest.CoreMatchers.is;


public class ProfilePageTest extends AbstractTest {
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
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user);
    }


    @Test
    @DisplayName("Проверка перехода в личный кабинет по клику на «Личный кабинет»")
    public void persAccBtnTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersAccBtn();
        ProfilePage profilePage = new ProfilePage(driver);
        String text = profilePage.getProfileText();
        Assert.assertEquals("Профиль", text);

    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на «Конструктор»")
    public void constructorBtnTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersAccBtn();
        ProfilePage profilePage = new ProfilePage(driver);
        String text = profilePage.getProfileText();
        Assert.assertEquals("Профиль", text);
        profilePage.clickConstructorBtn();
        String textCollectBurger = mainPage.getCollectBurgerText();
        Assert.assertEquals("Соберите бургер", textCollectBurger);
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета на логотип Stellar Burgers")
    public void logoStellaBrgsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersAccBtn();
        ProfilePage profilePage = new ProfilePage(driver);
        String text = profilePage.getProfileText();
        Assert.assertEquals("Профиль", text);
        profilePage.clickHeaderLogo();
        String textCollectBurger = mainPage.getCollectBurgerText();
        Assert.assertEquals("Соберите бургер", textCollectBurger);
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void exitBtnTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersAccBtn();
        ProfilePage profilePage = new ProfilePage(driver);
        String text = profilePage.getProfileText();
        Assert.assertEquals("Профиль", text);
        profilePage.clickExitBtn();
        LoginPage loginPage = new LoginPage(driver);
        String loginPageHeaderText = loginPage.getHeaderLoginPageText();
        Assert.assertEquals("Вход", loginPageHeaderText);
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
