package cn.gloryroad.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name = "email")
    public WebElement username;
    @FindBy(xpath = "//*[@name='password']")
    public  WebElement password;
    @FindBy(xpath = "//*[@id='dologin']")
    public  WebElement dologin;
    public  LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

}
