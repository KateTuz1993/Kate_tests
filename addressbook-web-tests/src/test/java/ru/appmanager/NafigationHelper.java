package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NafigationHelper {
    private FirefoxDriver wd;

    public NafigationHelper(FirefoxDriver wd) {
        this.wd= wd;
    }

    public void gotoGroupPage() {
       wd.findElement(By.linkText("groups")).click();
    }

    public void gotoAddContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }
}
