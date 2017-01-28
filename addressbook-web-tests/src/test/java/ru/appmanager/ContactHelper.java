package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.models.ContactData;

public class ContactHelper extends HelperBase{


    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.xpath("//div/div[4]/div/i/a[2]"));
    }

    public void enteringContactInfo() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        //wd.findElement(By.name("firstname")).click();
       //wd.findElement(By.name("firstname")).clear();
        //wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        //wd.findElement(By.name("middlename")).click();
        //wd.findElement(By.name("middlename")).clear();
        //wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        //wd.findElement(By.name("lastname")).click();
        //wd.findElement(By.name("lastname")).clear();
        //wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        type(By.name("company"),contactData.getCompany());
        //wd.findElement(By.name("company")).click();
        //wd.findElement(By.name("company")).clear();
        //wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        //wd.findElement(By.name("address")).click();
        //wd.findElement(By.name("address")).clear();
        //wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        type(By.name("home"),contactData.getHome_tel());
        //wd.findElement(By.name("home")).click();
        //wd.findElement(By.name("home")).clear();
        //wd.findElement(By.name("home")).sendKeys(contactData.getHome_tel());
    }
}
