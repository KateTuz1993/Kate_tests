package ru.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();

    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");


    }
}
