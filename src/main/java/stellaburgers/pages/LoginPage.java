package stellaburgers.pages;

import stellaburgers.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private final WebDriver driver;

    private final By signUpBtn = By.xpath("//a[text()='Зарегистрироваться']");
    private final By inputEmail = By.xpath("//input[@class = 'text input__textfield text_type_main-default' and@type = 'text']");
    private final By inputPassword = By.xpath("//input[@class = 'text input__textfield text_type_main-default' and@type = 'password']");
    private final By loginBtn = By.xpath("//button[text()='Войти']");
    private final By recoverPasswordBtn = By.xpath("//a[text()='Восстановить пароль']");
    private final By headerLoginPage = By.xpath("//h2[text()='Вход']");


    // Метод ожидания полной загрузки страницы
    private void waitForPageLoad() {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    // конструктор класса
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Ожидание полной загрузки страницы
        waitForPageLoad();
    }

    //клик кнопки "Зарегистрироваться"
    public void clickSignUpBtn() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(signUpBtn));
        driver.findElement(signUpBtn).click();
    }

    public void waitLoginBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginBtn));
    }

    //клик кнопки "Войти"
    public void clickLoginBtn() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(loginBtn));
        driver.findElement(loginBtn).click();
    }

    //клик кнопки "Восстановить пароль"
    public void clickRecoverRasswordBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(recoverPasswordBtn));
        driver.findElement(recoverPasswordBtn).click();
    }

    public String getHeaderLoginPageText() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerLoginPage));
        return driver.findElement(headerLoginPage).getText();
    }

    //метод для авторизации
    public void login(User user) {
        waitLoginBtn();
        driver.findElement(inputEmail).sendKeys(user.getEmail());
        driver.findElement(inputPassword).sendKeys(user.getPassword());
        clickLoginBtn();
    }
}
