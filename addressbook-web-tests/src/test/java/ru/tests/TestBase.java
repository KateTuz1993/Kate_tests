package ru.tests;

import org.apache.xpath.operations.Bool;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;
import ru.models.Groups;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


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

    //сравнивает два множества: полученного с пользовательского инт-са с полученного из БД
    public void verifyGroupListInUI() {

//отключаемая проверка. чтобы включить - необходимо указать в конфигурации теста в поле VM options значение -DverifyUI=true
        if(Boolean.getBoolean("verifyUI")) {

            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet()))); //для каждого объекта типа GroupData, считанного из БД, оставляем только id и имя
        }

    }

    public void verifyContactListInUI() {
        if(Boolean.getBoolean("verifyUI")) {
            //вывод в сообщении содержимого переменной (для проверки)

            Contacts dbContacts = app.db().contacts();
            JOptionPane.showMessageDialog(null, dbContacts);
            Contacts uiContacts = app.contact().all();
            JOptionPane.showMessageDialog(null, uiContacts);
            MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withLastname(g.getLastname())
                            .withFirstname(g.getFirstname()).withAddress(g.getAddress()))
                          //  .withAllEmails(g.getAllEmails()).withAllPhones(g.getAllPhones()))
                    .collect(Collectors.toSet()))); //для каждого объекта типа ContactData, считанного из БД, оставляем только id и имя
        }
    }



}
