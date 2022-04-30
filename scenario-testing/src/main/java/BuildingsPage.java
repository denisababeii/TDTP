import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

// page_url = https://lighthouse-demo.evozon.com/buildings
public class BuildingsPage {
    @FindBy(css = "[data-shrink = '']")
    public WebElement usernameInput;

    @FindBy(css = "[data-shrink = '']")
    public WebElement passwordInput;


    public BuildingsPage(WebDriver driver) {
        driver.get("https://lhdemo:LH@Evozon!2022@lighthouse-demo.evozon.com/buildings");
        usernameInput.sendKeys("dory7141@gmail.com");
        passwordInput.sendKeys("Andreiboss12");
        PageFactory.initElements(driver, this);
    }
}