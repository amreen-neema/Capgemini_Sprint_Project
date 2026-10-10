package BaseClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments(
                    "--disable-features=PasswordLeakDetection");

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {

            EdgeOptions options = new EdgeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments(
                    "--disable-features=PasswordLeakDetection");

            driver = new EdgeDriver(options);

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");

        System.out.println(
                "Swag Labs login page opened in " + browser);
    }

    // Screenshot method
    public void takeScreenshot(String fileName) {

        if (driver == null) {
            System.out.println("Driver is not initialized.");
            return;
        }

        try {
            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path folder = Paths.get(
                    System.getProperty("user.dir"),
                    "Screenshots");

            Files.createDirectories(folder);

            Path destination =
                    folder.resolve(fileName + ".png");

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            System.out.println(
                    "Screenshot saved: "
                    + destination.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Unable to save screenshot.");
            e.printStackTrace();
        }
    }

    // Capture screenshots only for Negative Suite
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            if (driver != null
                    && result.getTestContext()
                             .getSuite()
                             .getName()
                             .equalsIgnoreCase("Negative Suite")) {

                String timeStamp =
                        new SimpleDateFormat(
                                "yyyyMMdd_HHmmss_SSS")
                                .format(new Date());

                String testName =
                        result.getMethod().getMethodName();

                String status;

                if (result.getStatus() == ITestResult.SUCCESS) {
                    status = "PASS";
                } else if (result.getStatus()
                        == ITestResult.FAILURE) {
                    status = "FAIL";
                } else {
                    status = "SKIP";
                }

                takeScreenshot(
                        "Negative_" + testName
                        + "_" + status
                        + "_" + timeStamp);
            }

        } finally {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
}
