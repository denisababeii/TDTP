package com.example.TDTP.addBooking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddBookingTest {
    private WebDriver driver;
    private final String username = "sera.victor6023@gmail.com";
    private final String password = "FbLcP9ab{Fh,rYn*";
    private WebDriverWait wait;

    @BeforeEach
    public void doLogin() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 100);
        String URL = "https://lhdemo:LH@Evozon!2022@lighthouse-demo.evozon.com/login";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("MuiButton-label")));
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div/form/div[1]/div/input")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        element.click();
    }

    // Tests whether searching for an existing user and retrieving its information works
    @Test
    public void testAddBooking() {
        waitFor(".//span[contains(@class, \"MuiButton-label\") and text()=\"Accept Cookies\"]").click();
        waitFor(".//span[text()=\"ADD NEW BOOKING\"]").click();
        waitFor(".//div[contains(@class,\"DayPicker-Day\")]/div/div[text()=\"17\"]").click();
        waitFor(".//span[text()=\"BOOK DESK\" and contains(@class, \"MuiButton-label\")]").click();

        Assertions.assertTrue(waitFor(".//span/strong[text()=\"Victorash, Parter, DEMO-1\"]").isDisplayed());
        Assertions.assertTrue(waitFor(".//span[text()=\"09:00 - 18:00\"]").isDisplayed());
        Assertions.assertTrue(waitFor(".//h6/strong[text()=\"17 May\"]").isDisplayed());

        removeBooking();
    }

    private WebElement waitFor(String xpath) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    private void removeBooking() {
        waitFor(".//button/span/div").click();
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
    }
}
