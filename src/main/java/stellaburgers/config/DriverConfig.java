package stellaburgers.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverConfig {

    public static final String STELLA_BURGERS_URL = "https://stellarburgers.nomoreparties.site";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--kiosk");
                    driver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
                    break;
                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                    ChromeOptions yandexOptions = new ChromeOptions();
                    yandexOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(yandexOptions);
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public void navigateToUrl() {
        driver.get(DriverConfig.STELLA_BURGERS_URL);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
