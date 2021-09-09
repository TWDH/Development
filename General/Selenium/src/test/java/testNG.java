import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class testNG {
    @BeforeClass
    public void setUP() {
        // before class
        System.out.println("This is before class");
    }

    @BeforeMethod
    public void beforeMethod() {
        // before method
        System.out.println("This is before method");
    }

    @Test
    public void aFastTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\2.projects\\OTT123\\QA\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://m.ott123.com/");

        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
        System.out.println("Fast test");
    }

    @Test
    public void aSlowTest() {
        System.out.println("Slow test");
    }

    @AfterMethod
    public void aftermethod() {
        System.out.println("This is after method");
    }

    @AfterClass
    public void afterclass() {
        System.out.println("This is after class");
    }

}
