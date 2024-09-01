package stellaburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By enterBtn = By.xpath("//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnterBtn() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(enterBtn));
        driver.findElement(enterBtn).click();
    }
}
