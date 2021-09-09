import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class MyTest {

    public static void main(String[] args) throws Exception{
       // ChromeOptions options = new ChromeOptions();
       // options.addExtensions(new File("C:\\Program Files\\Google\\Chrome\\Application\\91.0.4472.124\\default_apps\\drive.crx"));

        System.setProperty("webdriver.chrome.driver", "D:\\2.projects\\OTT123\\QA\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://m.ott123.com/");

        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }
}
