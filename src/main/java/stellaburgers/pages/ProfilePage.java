package stellaburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriver driver;

    private final By profileText = By.xpath("//a[text()='Профиль']");
    private final By constructorBtn = By.xpath("//p[text()='Конструктор']");
    private final By headerLogo = By.xpath("(//a[@href='/'])[2]");
    private final By exitBtn = By.xpath("//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProfileText() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(profileText));
        return driver.findElement(profileText).getText();
    }

    public void clickConstructorBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(constructorBtn));
        driver.findElement(constructorBtn).click();
    }

    public void clickHeaderLogo() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(headerLogo));
        driver.findElement(headerLogo).click();
    }

    public void clickExitBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(exitBtn));
        driver.findElement(exitBtn).click();
    }
}
