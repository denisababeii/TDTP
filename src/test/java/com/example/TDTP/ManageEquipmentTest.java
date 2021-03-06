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

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ManageEquipmentTest {
    WebDriver driver;

    @BeforeEach
    public void doLogIn() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        String URL = "https://" +"lhdemo" +":" +"LH@Evozon!2022" +"@"+ "lighthouse-demo.evozon.com/login";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("MuiButton-label")));
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div/form/div[1]/div/input")).sendKeys("denisababeii2@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Parola123456789");
        driver.findElement(By.className("MuiButton-label")).click();
    }

    // Tests whether adding new equipment works
    @Test
    public void testAddNonExistentEquipment() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[4]/div/button"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/button[2]"))).click();

        int count = driver.findElements(By.className("MuiTableRow-root")).size();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")));
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/div/div/input")).sendKeys("New Equipment");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")).click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("MuiTableRow-root"),0));
        assert driver.findElements(By.className("MuiTableRow-root")).size() == count+1;

        deleteEntry();
    }

    // Tests whether adding equipment with the same name as one already saved fails
    @Test
    public void testAddExistentEquipment() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[4]/div/button"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/button[2]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")));
        WebElement input = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/div/div/input"));
        input.sendKeys("New Equipment");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")).click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("MuiTableRow-root"),0));
        int count = driver.findElements(By.className("MuiTableRow-root")).size();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")));
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys("New Equipment");
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[2]/div/div[1]/button")).click();

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("MuiTableRow-root"),0));
        assert driver.findElements(By.className("MuiTableRow-root")).size() == count;

        deleteEntry();
    }

    public void deleteEntry() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("MuiTableRow-root"),0));
        List<WebElement> elements = driver.findElements(By.className("MuiTableRow-root"));
        for (WebElement elem: elements) {
            if(elem.findElements(By.xpath("//*[text()='New Equipment']")).size() != 0) {
                WebElement button = elem.findElement(By.className("MuiIconButton-root"));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
            }
        }
    }

    @AfterEach
    public void doCleanUp() {
        driver.close();
    }
}
