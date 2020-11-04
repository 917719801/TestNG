package cn.gloryroad.testScripts;

import cn.gloryroad.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test126mail {
    private WebDriver driver;
    private  String baseUrl="https://mail.126.com/";
    WebDriverWait wait;
    @BeforeMethod
    public void setup(){

        System.setProperty("webdriver.gecko.driver", "D:\\WebDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }
    @Test
    public void  testLogin() throws  InterruptedException{
        driver.get(baseUrl);
        LoginPage loginPage=new LoginPage(driver);
        Thread.sleep(3000);
        loginPage.username.click();
        loginPage.username.sendKeys("test");
        loginPage.password.sendKeys("123456");
        loginPage.dologin.click();
        Thread.sleep(3000);
    }
    @Test
    void test(){
        driver.get(baseUrl);
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("test");
        driver.findElement(By.id("auto-id-1604396125350")).click();
        driver.findElement(By.id("auto-id-1604396125350")).sendKeys("123456");
        driver.findElement(By.id("dologin")).click();
    }


}
