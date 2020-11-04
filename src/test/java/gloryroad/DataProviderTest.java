package gloryroad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DataProviderTest {
    private static WebDriver driver;
    WebDriverWait wait;
    //使用DataProvider注解定义当前方法中的返回对象作为测试脚本的测试数据集，并且将测试数据集命名为searchWords
    @DataProvider(name = "searchWords")
    public  static Object[][] words(){
        return  new Object[][]{ {"蝙蝠侠","主演","迈克尔"},{ "超人","导演","唐纳"},{"生化危机","编剧","安德森"}
        };
    }
    //测试方法中的三个参数分别使searchWords测试数据集中的每个一维数组中的数据进行赋值
    @Test(dataProvider = "searchWords")
    void test(String searchWord1,String searchWord2,String searchResult){
        driver.get("https://www.sogou.com/");
        driver.findElement(By.id("query")).sendKeys(searchWord1+""+searchWord2);
        driver.findElement(By.id("stb")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //断言结果页面中是否包含期望的关键字
        Assert.assertTrue(driver.getPageSource().contains(searchResult));

    }
    @BeforeMethod
    public void setup(){

        System.setProperty("webdriver.gecko.driver", "D:\\WebDriver\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }
    @AfterMethod
    void setdown(){
        driver.quit();
    }

}
