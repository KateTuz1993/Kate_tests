package ru.tests;

import org.openqa.selenium.remote.BrowserType;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.appmanager.ApplicationManager;


public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite //один запуск браузера и выполенение нескольких тестов
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


}
