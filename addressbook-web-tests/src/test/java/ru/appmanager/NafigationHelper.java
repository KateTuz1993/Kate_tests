package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NafigationHelper extends HelperBase{

    public NafigationHelper(WebDriver wd) {
        super(wd);
    }
//добавлены проверки: необходимо ли делать этот клик? можно не делать его, если мы уже находимся на нужной странице
    public void groupPage() {
       if (isElementPresent(By.tagName("h1")) //на странице присутствует заголовок
               && wd.findElement(By.tagName("h1")).getText().equals("Groups") //название заголовка Groups
               && isElementPresent(By.name("new"))){   // и присутствует элемент с названием New(кнопка)
           return;
       }
       click(By.linkText("groups"));
    }

    public void addContactPage() {

        if (isElementPresent(By.tagName("h1"))  //на странице присутствует заголовок
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry") ){ //название заголовка Edit ...
            return;
        }
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        click(By.linkText("home"));
    }
}
