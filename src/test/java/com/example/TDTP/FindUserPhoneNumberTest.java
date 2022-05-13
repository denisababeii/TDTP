package com.example.TDTP;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindUserPhoneNumberTest {

    WebDriver driver;

    private static final String SELECTOR_USER_INPUT_PATH = "/html/body/div/div[2]/main/div/div/form/div[1]/div/input";

    private static final String SELECTOR_PASSWORD_NAME = "password";

    private static final String SELECTOR_LOGIN_CLASSNAME = "MuiButton-label";

    private static final String SELECTOR_SEARCH_INPUT_PATH = "/html/body/div[1]/div[2]/div[3]/div[2]/div/div/div/div[2]/div[4]/div/input";

    private static final String SELECTOR_USERS_BUTTON_PATH = "/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/button";

    @BeforeEach
    public void doLogin() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String URL = "https://" +"lhdemo" +":" +"LH@Evozon!2022" +"@"+ "lighthouse-demo.evozon.com/login";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className(SELECTOR_LOGIN_CLASSNAME)));
        driver.findElement(By.xpath(SELECTOR_USER_INPUT_PATH)).sendKeys("stefania.mardar@gmail.com");
        driver.findElement(By.name(SELECTOR_PASSWORD_NAME)).sendKeys("Parola1234");
        driver.findElement(By.className(SELECTOR_LOGIN_CLASSNAME)).click();
    }

    @Test
    public void test_FINDS_USERS_PHONE_NUMBER_when_USER_DETAILS_ENTERED() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SELECTOR_USERS_BUTTON_PATH))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SELECTOR_SEARCH_INPUT_PATH))).sendKeys("Raileanu", Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiTableRow-root"),2));

        WebElement foundUser = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/div/div/div/div[3]/div[1]/table/tbody/tr/td[2]/div/div"));

        foundUser.click();

        Thread.sleep(3000);

        WebElement phoneNumber = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/div/div/div[2]/div[5]/p[2]"));

        assert phoneNumber.getText().equalsIgnoreCase("0748518713");
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
    }
}
