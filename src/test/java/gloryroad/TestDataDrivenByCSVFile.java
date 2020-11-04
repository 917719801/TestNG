package gloryroad;
/*
使用testNg和CSV文件进行数据驱动
 */

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
        //调用类中的静态方法getTestDate获取CSV文件中的测试数据
        return getTestDate("E:\\ideawork\\TestNG\\bin\\testData.csv");
    }

    @Test(dataProvider = "testdata")
    public void  testSearch(String searchWord1,String searchWord2,String searchResult){
        driver.get(baseURL);
        driver.findElement(By.id("query")).sendKeys(searchWord1+""+searchWord2);
        driver.findElement(By.id("stb")).click();
        //使用显式等待，确认页面已经加载完成，页面底部的关键字“相关搜索”已经显示在页面上
        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public  Boolean apply(WebDriver d){
                return  d.findElement(By.id("hint_container")).getText().contains("相关搜索");
            }
        });
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
//读取CSV文件的静态方法，使用CSV文件的绝对路径作为函数参数
    private static Object[][] getTestDate(String fileName) throws IOException {
        List<Object[]> records=new ArrayList<Object[]>();
        String record;
        //设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件
        BufferedReader file=new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
        //忽略读取CSV文件的第一行
        file.readLine();
        //遍历读取文件除第一行以外的全部内容并存储在名为records的Arraylist中
        //每一个recods中存储的对象为一个String数组
        while ((record=file.readLine())!=null){
            String Fields[]=record.split(",");
            records.add(Fields);
        }
        //关闭文件对象
        file.close();
        //定义函数返回值，即Object[][]
        //将存储数据的list转换为一个Object二维数组
        Object[][] results=new Object[records.size()][];
        //设置二维数组每行的只，每行是一个Object对象
        for (int i= 0 ;i< records.size();i++){
            results[i]=records.get(i);
        }
        return results;
    }




}
