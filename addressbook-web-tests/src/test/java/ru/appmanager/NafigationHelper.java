package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NafigationHelper extends HelperBase{

    public NafigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
       click(By.linkText("groups"));
    }

    public void gotoAddContactPage() {
        click(By.linkText("add new"));
    }
}
