import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;

public class SeleniumTest {

    private WebDriver webDriver;
    private String path;
    
    @BeforeEach
    public void setUp() {
       
      // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");//linux_64

        // Get file
        File file = new File("src/main/java/com/revature/index.html");
        path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }
    
   
    // File file = new File("src/main/java/com/revature/index.html");
    // String path = "file://" + file.getAbsolutePath();
    
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
       
            webDriver.quit();
        
    }
}

