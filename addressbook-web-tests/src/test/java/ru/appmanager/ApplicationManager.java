package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.models.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final GroupHelper groupHelper = new GroupHelper();

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        groupHelper.wd = new FirefoxDriver();
        groupHelper.wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        groupHelper.wd.get("http://localhost:8080/addressbook/");
        login("admin", "secret");
    }

    public void gotoGroupPage() {
        groupHelper.wd.findElement(By.linkText("groups")).click();
    }

    private void login(String username, String password) {
        groupHelper.wd.findElement(By.name("pass")).click();
        groupHelper.wd.findElement(By.name("pass")).sendKeys("\\undefined");
        groupHelper.wd.findElement(By.name("user")).click();
        groupHelper.wd.findElement(By.name("user")).clear();
        groupHelper.wd.findElement(By.name("user")).sendKeys(username);
        groupHelper.wd.findElement(By.name("user")).click();
        groupHelper.wd.findElement(By.id("LoginForm")).click();
        groupHelper.wd.findElement(By.name("pass")).click();
        groupHelper.wd.findElement(By.name("pass")).clear();
        groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
        groupHelper.wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    public void returnToHomePage() {
        groupHelper.wd.findElement(By.xpath("//div/div[4]/div/i/a[2]")).click();
    }

    public void enteringContactInfo() {
        groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        groupHelper.wd.findElement(By.name("firstname")).click();
        groupHelper.wd.findElement(By.name("firstname")).clear();
        groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        groupHelper.wd.findElement(By.name("middlename")).click();
        groupHelper.wd.findElement(By.name("middlename")).clear();
        groupHelper.wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        groupHelper.wd.findElement(By.name("lastname")).click();
        groupHelper.wd.findElement(By.name("lastname")).clear();
        groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        groupHelper.wd.findElement(By.name("company")).click();
        groupHelper.wd.findElement(By.name("company")).clear();
        groupHelper.wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        groupHelper.wd.findElement(By.name("address")).click();
        groupHelper.wd.findElement(By.name("address")).clear();
        groupHelper.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        groupHelper.wd.findElement(By.name("home")).click();
        groupHelper.wd.findElement(By.name("home")).clear();
        groupHelper.wd.findElement(By.name("home")).sendKeys(contactData.getHome_tel());
    }

    public void gotoAddContactPage() {
        groupHelper.wd.findElement(By.linkText("add new")).click();
    }

    public void stop() {
        groupHelper.wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
}
