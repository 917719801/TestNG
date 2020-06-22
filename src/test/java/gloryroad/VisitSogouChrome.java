package gloryroad;
/*
启动谷歌浏览器访问Sogou网址后关闭浏览器
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VisitSogouChrome {
    WebDriver driver;
    String url;
    @BeforeMethod
    public void setup(){
        url = "https://www.sogou.com/";
        System.setProperty("webdriver.chrome.driver","D:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    void vivsitsogou(){
        driver.get(url);
    }
    @AfterMethod
    void tearDown(){
        driver.quit();
    }
}
