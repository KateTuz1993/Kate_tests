package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession(); //создаем новую сессию
        assertTrue(session.login("administrator","root")); //проверка что пользователь действительно залогинился
        assertTrue(session.isLoggedInAs("administrator"));


    }

}
