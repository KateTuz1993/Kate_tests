package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{
    //private FirefoxDriver wd;

    public SessionHelper(WebDriver wd) {

        super(wd);
    }

    public void login(String username, String password) {

        click(By.name("pass"));
        type(By.name("user"),username);
        click(By.name("user"));
        click(By.id("LoginForm"));
        type(By.name("pass"),password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
