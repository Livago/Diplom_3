package tests;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellaburgers.config.DriverConfig;
import stellaburgers.pages.MainPage;


public class MainPageTest extends AbstractTest {
    private WebDriver driver;
    private DriverConfig driverConfig;

    @Before
    public void setUp() {
        driverConfig = new DriverConfig();
        driver = DriverConfig.getDriver();
        driverConfig.navigateToUrl();
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    @Description("проверка перехода в раздел Соусы возвратом текста")
    public void saucesSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesBtn();
        mainPage.isChoiceSectionVisible();
        Assert.assertEquals("Соусы", mainPage.getTextSelectedMenu());
    }

    @Test
    @DisplayName("Переход на раздел Булки")
    @Description("проверка перехода на раздел Булки возвратом текста")
    public void bunsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.isChoiceSectionVisible();
        Assert.assertEquals("Булки", mainPage.getTextSelectedMenu());
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    @Description("проверка перехода в раздел Начинки возвратом текста")
    public void fillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsBtn();
        Assert.assertEquals("Начинки", mainPage.getTextSelectedMenu());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driverConfig.quitDriver();
        }
    }
}
