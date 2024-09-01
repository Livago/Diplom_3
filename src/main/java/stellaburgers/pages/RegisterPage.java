package stellaburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellaburgers.model.User;

public class RegisterPage {
    private final WebDriver driver;

    private final By registrationHeader = By.xpath("//h2[text()='Регистрация']");
    private final By inputName = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[1]");
    private final By inputEmail = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[2]");
    private final By inputPassword = By.xpath("(//input[@class='text input__textfield text_type_main-default'])[3]");
    private final By signUpBtn = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginBtnRegPage = By.xpath("//a[text()='Войти']");
    private final By incorrectPswrdText = By.xpath("//p[text()='Некорректный пароль']");

    public void waitForRegHeader() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
    }

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignUpBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(signUpBtn));
        driver.findElement(signUpBtn).click();
    }

    public String getIncorrectPswrdText() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(incorrectPswrdText));
        return driver.findElement(incorrectPswrdText).getText();
    }

    //вход через кнопку в форме регистрации
    public void clickLogInBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginBtnRegPage));
        driver.findElement(loginBtnRegPage).click();
    }

    public void signUpUser(User user) {
        waitForRegHeader();
        driver.findElement(inputName).sendKeys(user.getName());
        driver.findElement(inputEmail).sendKeys(user.getEmail());
        driver.findElement(inputPassword).sendKeys(user.getPassword());
        clickSignUpBtn();
    }
}
