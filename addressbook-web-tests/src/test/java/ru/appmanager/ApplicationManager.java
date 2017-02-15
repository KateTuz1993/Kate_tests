package ru.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NafigationHelper nafigationHelper;
    private GroupHelper groupHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
        if (Objects.equals(browser, BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (Objects.equals(browser, BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.IE)){
            wd = new InternetExplorerDriver();
            wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        nafigationHelper = new NafigationHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

        public void stop() {
        wd.quit();
    }

    public GroupHelper group() { return groupHelper;  }

    public NafigationHelper goTo() {
        return nafigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
