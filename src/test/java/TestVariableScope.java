import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;

public class TestVariableScope {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        String browserName = BrowserUtils.getWebDriverName();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.addCommandSwitches("-headless");
                webDriver = new InternetExplorerDriver(ieOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
    File file = new File("src/main/java/com/revature/index.html");
    String path = "file://" + file.getAbsolutePath();
    
    @Test
    public void testGlobalScope() {
        
        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("globalScopeDemo()");

        // Since we can't directly get the result of the executed JavaScript,
        // we may need to modify the JavaScript functions to return values or update the DOM
        // and then retrieve those updated values to perform assertions.
        // For simplicity, let's assume the function updates the DOM and we'll check the updated text.

        String outputTextGlobal = (String) jsExecutor.executeScript("return document.getElementById('output-global').textContent;");
        String expectedTextGlobal = "I am a global scope variable!";
        assertEquals(expectedTextGlobal, outputTextGlobal);
    }

    @Test
    public void testLocalScope() {

        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("localScopeDemo()");

        String outputTextLocalLet = (String) jsExecutor.executeScript("return document.getElementById('output-local-let').textContent;");
        String expectedTextLocalLet = "I am a local scope variable declared using the let keyword!";
        assertEquals(expectedTextLocalLet, outputTextLocalLet);
    }

    @Test
    public void testVarScope(){

        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("varScopeDemo()");

        String outputTextLocalLet = (String) jsExecutor.executeScript("return document.getElementById('output-local-var').textContent;");
        String expectedTextLocalLet = "I am a local scope variable declared using the var keyword!";
        assertEquals(expectedTextLocalLet, outputTextLocalLet);
    
        String outputTextLocalVar = (String) jsExecutor.executeScript("return document.getElementById('output-reassigned-var').textContent;");
        String expectedTextLocalVar = "I have been reassigned with a different value!";
        assertEquals(expectedTextLocalVar, outputTextLocalVar);
    }

    @Test
    public void testBlockScope(){

        webDriver.get(path);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript("blockScopeDemo()");
        
        String outputTextBlockConst = (String) jsExecutor.executeScript("return document.getElementById('output-block-const').textContent;");
        String expectedTextBlockConst = "I am a block-level scope variable declared using the const keyword!";
        assertEquals(expectedTextBlockConst, outputTextBlockConst);
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

class BrowserUtils {
    public static String getWebDriverName() {
        String[] browsers = { "chrome", "firefox", "edge", "ie" };

        for (String browser : browsers) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        new ChromeDriver().quit();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        new FirefoxDriver().quit();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        new EdgeDriver().quit();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        new InternetExplorerDriver().quit();
                        break;
                }
                return browser;
            } catch (Exception e) {
                continue;
            }
        }
        return "Unsupported Browser";
    }
}
