package com.example.TDTP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.UUID;

/**
 * Test Scenario for add building case
 */
public class AddBuildingScenario {

    private final String buildingName;
    private final WebDriver driver;

    /**
     * First Step
     * Login function for passing in the main functionallity page
     */
    public void login() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var emailInput = driver.findElement(By.name("username"));
        var passwordInput = driver.findElement(By.name("password"));
        var signInButton = driver.findElement(By.cssSelector("button"));
        emailInput.sendKeys("<username>");
        passwordInput.sendKeys("<password>");
        signInButton.click();
    }

    /**
     * Second Step
     * Accessing the Buildings screen and adding a building
     */
    public void addBuilding(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var navButtons = driver.findElements(By.tagName("button"));
        var buildingsNavButton = navButtons
                .stream()
                .filter(e -> Objects.equals(e.getText(), "BUILDINGS"))
                .findFirst()
                .orElse(null);
        assert buildingsNavButton != null;
        buildingsNavButton.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var addNewBuildingButton = driver.findElements(By.tagName("button"))
                .stream()
                .filter(e -> Objects.equals(e.getText(), "ADD NEW BUILDING"))
                .findFirst()
                .orElse(null);
        assert addNewBuildingButton != null;
        addNewBuildingButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var labelInput = driver.findElement(By.name("label"));
        var safeDistanceInput = driver.findElement(By.name("safeDistance"));
        labelInput.sendKeys(this.buildingName);
        safeDistanceInput.sendKeys("15");

        var saveButton = driver.findElements(By.tagName("button"))
                .stream()
                .filter(e -> Objects.equals(e.getText(), "SAVE"))
                .findFirst()
                .orElse(null);
        assert saveButton != null;
        saveButton.click();
    }

    /*
    * Third Step
    * Navigating to where the new stored building is placed and returning it's element
    **/
    public WebElement getBuildingElement(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        var nextPageButton = driver.findElements(By.tagName("button"))
                .stream().filter(e -> Objects.equals(e.getAccessibleName(), "Next page"))
                .findFirst()
                .orElse(null);
        WebElement buildingElement = null;
        while (buildingElement == null){
            assert nextPageButton != null;
            buildingElement = driver.findElements(By.tagName("td"))
                    .stream()
                    .filter(e -> Objects.equals(e.getText(), this.buildingName))
                    .findFirst()
                    .orElse(null);
            if(!nextPageButton.isEnabled()) break;
            nextPageButton.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextPageButton = driver.findElements(By.tagName("button"))
                    .stream()
                    .filter(e -> Objects.equals(e.getAccessibleName(), "Next page"))
                    .findFirst()
                    .orElse(null);
        }
        return buildingElement;
    }

    /*
    * Fourth Step
    * Closing the driver
    **/
    public void closeDriver(){
        this.driver.close();
    }

    public AddBuildingScenario(WebDriver driver) {
        this.driver = driver;
        this.buildingName = UUID.randomUUID() + "-test-Dori";
        driver.get("https://lhdemo:LH%40Evozon!2022@lighthouse-demo.evozon.com/login");
        driver.manage().window().maximize();
    }

    public String getBuildingName() {
        return buildingName;
    }
}