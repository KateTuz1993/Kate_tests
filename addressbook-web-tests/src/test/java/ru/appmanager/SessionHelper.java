package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{
    //private FirefoxDriver wd;

    public SessionHelper(FirefoxDriver wd) {

        super(wd);
    }

    public void login(String username, String password) {

        click(By.name("pass"));
        //wd.findElement(By.name("pass")).sendKeys("\\undefined");
        type(By.name("user"),username);
        //wd.findElement(By.name("user")).click();
       //wd.findElement(By.name("user")).clear();
        //wd.findElement(By.name("user")).sendKeys(username);
        click(By.name("user"));
        click(By.id("LoginForm"));
        type(By.name("pass"),password);
        //wd.findElement(By.name("pass")).click();
        //wd.findElement(By.name("pass")).clear();
        //wd.findElement(By.name("pass")).sendKeys(password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
