package ru.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class); //подключаем логирование

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite //один запуск браузера и выполенение нескольких тестов
    public void setUp() throws Exception {
        app.init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p)); // сообщение в логах
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m, Object[] p) {
        logger.info("Stop test " + m.getName()+ "with parameters " + Arrays.asList(p)); // сообщение в логах
    }


}
