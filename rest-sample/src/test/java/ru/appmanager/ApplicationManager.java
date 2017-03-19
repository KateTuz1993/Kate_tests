package ru.appmanager;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ApplicationManager {

    private WebDriver wd;

    private String browser;


    public void init() throws IOException {
        //String target = System.getProperty("target", "local");
        //properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }
}