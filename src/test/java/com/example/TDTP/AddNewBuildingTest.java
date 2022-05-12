package com.example.TDTP;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

@RunWith(SerenityRunner.class)
public class AddNewBuildingTest {
    @Managed
    WebDriver driver;

    public void doLogin() {
        String URL = "https://" +"lhdemo" +":" +"LH@Evozon!2022" +"@"+ "lighthouse-demo.evozon.com/login";
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className("MuiButton-label")));
        driver.findElement(By.xpath("/html/body/div/div[2]/main/div/div/form/div[1]/div/input")).sendKeys("stefania.mardar@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Parola1234");
        driver.findElement(By.className("MuiButton-label")).click();
    }

    @Test
    public void test_DOES_NOT_ADD_WHEN_invalid_safeDistance_zero() throws InterruptedException {
        /*
        * Function to test the scenario when we want to add a new building with safeDistance value zero.
        * It should NOT be added.
        * */
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        // Select Buildings from the header menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/button"))).click();

        Thread.sleep(5000);

        // Click on the Add New Building Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div[2]"))).click();

        // Get the current number of elements from the table
        int count_before = driver.findElements(By.className("MuiTableRow-root")).size();

        // Enter the name of the building
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/input"))).sendKeys("Test_Building");

        // Enter the safe distance
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[3]/div/div/input")).sendKeys("0");

        // Click on the Add Button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[4]/div/div[2]")).click();

        Thread.sleep(3000);

        // Get the updated number of rows from the table
        int count_after = driver.findElements(By.className("MuiTableRow-root")).size();

        // Check if the number of rows incremented
        assert count_after == count_before;
        driver.close();
    }

    @Test
    public void test_ADD_new_building_WHEN_valid_inputs_safeDistance_in_interval() throws InterruptedException {
        /*
         * Function to test the scenario when we want to add a new building with safeDistance value in the valid interval.
         * It should be added.
         * We should see the new building in the table.
         * */
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        // Select Buildings from the header menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/button"))).click();

        Thread.sleep(5000);

        // Click on the Add New Building Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div[2]"))).click();

        // Get the current number of elements from the table
        int count_before = driver.findElements(By.className("MuiTableRow-root")).size();

        // Enter the name of the building
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/input"))).sendKeys("Test_Building");

        // Enter the safe distance
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[3]/div/div/input")).sendKeys("2");

        // Click on the Add Button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[4]/div/div[2]")).click();

        Thread.sleep(3000);

        // Get the updated number of rows from the table
        int count_after = driver.findElements(By.className("MuiTableRow-root")).size();

        // Check if the number of rows incremented
        assert count_after == count_before + 1;

        deleteLastRow();
        driver.close();
    }

    @Test
    public void test_ADD_new_building_WHEN_valid_inputs_safeDistance_lower_bound() throws InterruptedException {
        /*
         * Function to test the scenario when we want to add a new building with safeDistance value being the lower bound.
         * It should be added.
         * We should see the new building in the table.
         * */
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        // Select Buildings from the header menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/button"))).click();

        Thread.sleep(5000);

        // Click on the Add New Building Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div[2]"))).click();

        // Get the current number of elements from the table
        int count_before = driver.findElements(By.className("MuiTableRow-root")).size();

        // Enter the name of the building
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/input"))).sendKeys("Test_Building");

        // Enter the safe distance
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[3]/div/div/input")).sendKeys("1");

        // Click on the Add Button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[4]/div/div[2]")).click();

        Thread.sleep(5000);

        // Get the updated number of rows from the table
        int count_after = driver.findElements(By.className("MuiTableRow-root")).size();

        // Check if the number of rows incremented
        assert count_after == count_before + 1;

        deleteLastRow();
        driver.close();
    }

    @Test
    public void test_ADD_new_building_WHEN_valid_inputs_name_accepted() throws InterruptedException {
        /*
         * Function to test the scenario when we want to add a new building with valid buildingName.
         * It should be added.
         * We should see the new building in the table.
         * */
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        // Select Buildings from the header menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/button"))).click();

        Thread.sleep(5000);

        // Click on the Add New Building Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div[2]"))).click();

        // Get the current number of elements from the table
        int count_before = driver.findElements(By.className("MuiTableRow-root")).size();

        // Enter the name of the building
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/input"))).sendKeys("abc");

        // Enter the safe distance
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[3]/div/div/input")).sendKeys("100");

        // Click on the Add Button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[4]/div/div[2]")).click();

        Thread.sleep(3000);

        // Get the updated number of rows from the table
        int count_after = driver.findElements(By.className("MuiTableRow-root")).size();

        // Check if the number of rows incremented
        assert count_after == count_before + 1;

        deleteLastRow();
        driver.close();
    }

    @Test
    public void test_DOES_NOT_ADD_WHEN_invalid_name_zero() throws InterruptedException {
        /*
         * Function to test the scenario when we want to add a new building with invalid name zero.
         * It should NOT be added.
         * */
        doLogin();
        WebDriverWait wait = new WebDriverWait(driver, 1000);

        // Select Buildings from the header menu
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div/button"))).click();

        Thread.sleep(5000);

        // Click on the Add New Building Button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div/div[2]"))).click();

        // Get the current number of elements from the table
        int count_before = driver.findElements(By.className("MuiTableRow-root")).size();

        // Enter the name of the building
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[2]/div/div/input"))).sendKeys("0");

        // Enter the safe distance
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[3]/div/div/input")).sendKeys("100");

        // Click on the Add Button
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[1]/div/div[1]/div[2]/div/div[4]/div/div[2]")).click();

        Thread.sleep(3000);

        // Get the updated number of rows from the table
        int count_after = driver.findElements(By.className("MuiTableRow-root")).size();

        // Check if the number of rows incremented
        assert count_after == count_before;
        driver.close();
    }

    public void deleteLastRow() throws InterruptedException {

        // Clean Up
        WebDriverWait wait = new WebDriverWait(driver, 100);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("MuiTableRow-root"),0));
        List<WebElement> elements = driver.findElements(By.className("MuiTableRow-root"));

        int currentIndex = elements.size() -1;

        WebElement optionsButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/div/div/div/div/div[1]/table/tbody/tr[" + currentIndex + "]/td[4]/div/button"));
        wait.until(ExpectedConditions.elementToBeClickable(optionsButton));
        optionsButton.click();

        int divValue = currentIndex + 4;
        WebElement deleteButton = driver.findElement(By.xpath("/html/body/div[" + divValue + "]/div[3]/ul/li[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        divValue = currentIndex + 5;
        WebElement confirmButton = driver.findElement(By.xpath("/html/body/div["+ divValue +"]/div[3]/div/div[3]/div/button"));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();

        Thread.sleep(2000);
        driver.close();
    }

}
