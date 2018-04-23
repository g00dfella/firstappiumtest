import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class MobileWebTest {

    @Test
    public void testIncorrectFBLogin() throws Exception {
        URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        //capabilities.setCapability(MobileCapabilityType.UDID, "530593A1-84B3-42C9-A95A-4964D1D3FB");

        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

        capabilities.setCapability("showXcodeLog", "true");
        // Be careful, if you have alerts to be displayed in the app future on, this setting should be false
        capabilities.setCapability("autoAcceptAlerts", "true");

        System.out.println("Step 1. Create new driver");
        AppiumDriver driver = new IOSDriver(serverUrl, capabilities);
        WebDriverWait  wait = new WebDriverWait(driver, 30);

        System.out.println("Step 2. Open website");
        driver.get("https://facebook.com");

        System.out.println("Step 3. Enter email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).sendKeys("max@test.com");
        //driver.getKeyboard().sendKeys(Keys.ENTER);

        System.out.println("Step 4. Enter password");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("pass")));
        driver.findElement(By.name("pass")).sendKeys("123456");

        System.out.println("Step 5. Click login button");
        driver.findElement(By.name("login")).click();

        System.out.println("Step 6. Check error message");
        Thread.sleep(3000);
        assertTrue(driver.findElement(By.cssSelector("div[data-sigil='m_login_notice']")).getText().contains("The password you entered is incorrect"));

        System.out.println("Step 7. Close driver");
        driver.quit();

    }

}

