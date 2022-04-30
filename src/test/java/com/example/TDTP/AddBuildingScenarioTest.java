package com.example.TDTP;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

class AddBuildingScenarioTest {
    @Test
    public void test_addBuildingScenario_successfully(){
        System.setProperty("webdriver.edge.driver", "D:\\edgedriver_win64\\msedgedriver.exe");
        var addBuildingScenario = new AddBuildingScenario(new EdgeDriver());
        addBuildingScenario.login();
        addBuildingScenario.addBuilding();
        var buildingElement = addBuildingScenario.getBuildingElement();
        assertEquals(buildingElement.getText(), addBuildingScenario.getBuildingName());
        addBuildingScenario.closeDriver();
    }
}