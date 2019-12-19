package selenium;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class GetPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    final static Logger logger = Logger.getLogger(GetPage.class);
    protected static String baseUrl = "https://otus.ru/";

    // arrange start
    @BeforeClass
    public static void webDriverSetup(){
        logger.debug("setup WebDriver");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void createDriver(){
        logger.debug("Initialize webDriver and set headless");
        ChromeOptions options = new ChromeOptions().setHeadless(false);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public  void closeDriver(){
        if (driver != null){
            driver.quit();
        }
    }
    // arrange end

    @Test
    // act
    public void openTestPage() {
        driver.get(baseUrl + "lessons/java-qa-engineer/");

    }

    @Test
    // assert
    public void assertTestPage() {
        WebElement courseTitle = driver.findElement(By.tagName("h1"));
        String getTitleByText = courseTitle.getText();
        Assert.assertEquals("Java QA Automation Engineer", getTitleByText);
    }
}
