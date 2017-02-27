package ru.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite //один запуск браузера и выполенение нескольких тестов
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
