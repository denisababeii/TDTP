package com.example.TDTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeUserRoleTest {
    WebDriver driver;

    @BeforeEach
    public void doLogin() {
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

    @Test
    public void testAddNonExistentEquipment() {

    }

    @Test
    public void testAddExistentEquipment() {

    }
}
