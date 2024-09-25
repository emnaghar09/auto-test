import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver.exe");

        // Create ChromeOptions object to disable notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // Initialize WebDriver with Chrome options
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.autoscout24.fr/");
        driver.manage().window().maximize();

        // Wait for the "Accepter tout" button to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement acceptCookiesButton = driver.findElement(By.className("_consent-accept_1fb0r_111"));
        acceptCookiesButton.click();
        driver.findElement(By.id("cta-selling")).click();


        // Wait for the dropdown to be clickable
        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='make']")));
        Select brand = new Select(brandDropdown);

        // Select the option
        // Select by value instead of visible text
        brand.selectByValue("13");


        Select month = new Select(driver.findElement(By.cssSelector("select[data-valid='true']:nth-child(1)")));
        month.selectByVisibleText("02");

        WebElement selectElement = driver.findElement(By.xpath("//select//option[text()='Année']")).findElement(By.xpath("./ancestor::select"));

        Select year = new Select(selectElement);

        year.selectByVisibleText("2021");

        WebElement modelDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='model']")));
        Select model = new Select(modelDropdown);
        model.selectByVisibleText("X1 SUV/4x4/Pick-Up 5 Portes");

        WebElement fuelDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='fuel']")));
        Select fuel = new Select(fuelDropdown);
        fuel.selectByVisibleText("Essence");

        WebElement powerDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='power']")));
        Select power = new Select(powerDropdown);
        power.selectByVisibleText("103 (140)");

        WebElement equipmentLineDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='equipmentLine']")));
        Select equipmentLine = new Select(equipmentLineDropdown);
        equipmentLine.selectByVisibleText("sDrive 18i 140 ch DKG7 Business Design (05/2019 - )");

        WebElement colorDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='color']")));
        Select color = new Select(colorDropdown);
        color.selectByVisibleText("Blanc");

        WebElement vehicleSelectionLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("vehicle-selection-link0")));
        vehicleSelectionLink.click();
        // Wait for the redirection to the login page
        wait.until(ExpectedConditions.urlContains("autoscout24.fr/entry/auth"));

        // Verify the URL of the new page

        if (driver.getCurrentUrl().startsWith("https://www.autoscout24.fr/entry/auth")) {
            System.out.println("Redirection successful" );
        } else {
            System.out.println("Redirection failed" );
        }


        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        email.sendKeys("emna.ghariani09@gmail.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("button-highlight")));
        continueButton.click();

        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        password.sendKeys("emna.ghariani09@gmail.com");
        // Click the label associated with the checkbox
        driver.findElement(By.xpath("//label[@for='dsgvo']")).click();
        //take a screen shot
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Files.copy(screenshotFile.toPath(), Path.of("C:\\Users\\User\\OneDrive\\Pictures\\wallpapers\\screenshot_" + timestamp + ".png"));
        // find total links in page
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        // Count them
        int numberOfLinks = allLinks.size();
        // print it
        System.out.println("Nombre total de liens sur la page : " + numberOfLinks);
        // Optionally, close the browser
        driver.quit();
    }

}
