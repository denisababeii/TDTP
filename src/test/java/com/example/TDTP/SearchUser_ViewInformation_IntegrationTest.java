package com.example.TDTP;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import net.serenitybdd.junit.runners.SerenityRunner;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SerenityRunner.class)
public class SearchUser_ViewInformation_IntegrationTest {
    @Managed
    WebDriver driver;

    public void doLogin() {
        String URL = "https://" +"lhdemo" +":" +"LH@Evozon!2022" +"@"+ "lighthouse-demo.evozon.com/login";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("MuiButton-label")));
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div/form/div[1]/div/input")).sendKeys("denisababeii2@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Parola123456789");
        driver.findElement(By.className("MuiButton-label")).click();
    }

    // Tests whether searching for an existing user and retrieving its information works
    @Test
    public void testSearchView() throws InterruptedException {
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[3]/div/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/div/div/div/div[2]/div[4]/div/input"))).sendKeys("Babeii",Keys.ENTER);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("MuiTableRow-root"),3));

        driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div/div/div/div[3]/div[1]/table/tbody/tr[1]/td[2]/div/div")).click();
        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[2]/div[3]/div[2]/div/div/div[2]/div[3]/p[2]")));
        Thread.sleep(500);
        assert elem.getText().equals("Babeii");
        driver.close();
    }
}
