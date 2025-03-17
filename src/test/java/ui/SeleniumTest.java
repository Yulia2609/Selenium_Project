package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.assertTrue;

public class SeleniumTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }
    @AfterEach
    void tearDown () {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)
    void openLinkByText(String chapterName, String linkText, String linkUrl, String headerName, boolean isFrame) {

        WebElement chapter = driver.findElement(By.xpath("//div[h5[text()='" + chapterName + "']]"));
        WebElement chapterLink = chapter.findElement(By.linkText(linkText));

        assertTrue(chapterLink.isDisplayed(), "Link isn't displayed");
        Assertions.assertEquals(chapterName, chapter.findElement(By.tagName("h5")).getText(), "Chapter should have name " + chapterName);
        chapterLink.click();
        Assertions.assertEquals(linkUrl, driver.getCurrentUrl(), "URL is not matching");

        if (isFrame) {
            WebElement frameElement = driver.findElement(By.cssSelector("frame[name='frame-header']"));
            driver.switchTo().frame(frameElement);
        }

        String actualHeader = driver.findElement(By.cssSelector("h1.display-6")).getText();
        Assertions.assertTrue(actualHeader.contains(headerName), "Header should contain text" + headerName);
    }
}
