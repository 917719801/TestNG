package gloryroad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDataDrivenByCSVFile {
    public WebDriver driver;
    String baseURL="https://www.sogou.com/";
    WebDriverWait wait;
    @DataProvider(name = "testdata")
    public  static  Object[][] words() throws IOException {
        return getTestDate("E:\\ideawork\\TestNG\\bin\\testData.csv");
    }

    @Test(dataProvider = "testdata")
    public void  testSearch(String searchWord1,String searchWord2,String searchResult){
        driver.get(baseURL);
        driver.findElement(By.id("query")).sendKeys(searchWord1+""+searchWord2);
        driver.findElement(By.id("stb")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
//            @Override
//            public  Boolean apply(WebDriver d){
//                return  d.findElement(By.id("s_footer")).getText().contains("搜索帮助");
//            }
//        });
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

    private static Object[][] getTestDate(String fileName) throws IOException {
        List<Object[]> records=new ArrayList<Object[]>();
        String record;
        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
        file.readLine();
        while ((record=file.readLine())!=null){
            String Fields[]=record.split(",");
            records.add(Fields);
        }
        file.close();
        Object[][] results=new Object[records.size()][];
        for (int i= 0 ;i< records.size();i++){
            results[i]=records.get(i);
        }
        return results;
    }




}
