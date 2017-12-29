package J;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestJUnit {

    private String baseUrl;
    private static ChromeDriverService service;
    public static WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("c:\\chromedriver\\chromedriver.exe "))
                .usingAnyFreePort()
                .build();
        service.start();


    }

    @Before
    public void setUp() throws Exception {

        driver = new ChromeDriver(service);
        baseUrl = "https://www.google.com.ua";
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);


    }

    @Test
    public void Apple() throws Exception {
        GoogleEngine StartPage = new GoogleEngine(driver);
        StartPage.Search("apple").PageLinkSearch("www.apple.com");
    }

    @Test
    public void Tesla() throws Exception {
        GoogleEngine StartPage = new GoogleEngine(driver);
        StartPage.Search("tesla").PageLinkSearch("seekingalpha.com");
    }

    @Test
    public void Me() throws Exception {
        GoogleEngine StartPage = new GoogleEngine(driver);
        StartPage.Search("Viacheslav").PageLinkSearch("viacheslav.com");
    }


    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
