package ru.tests;

import com.sun.jna.platform.win32.Netapi32Util;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.models.AllUsers;
import ru.models.MailMessage;
import ru.models.UserData;

import javax.mail.MessagingException;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void changePasswordTest() throws IOException, MessagingException {
        String admin = "administrator";
        String password = "root";
        app.registration().loginAdmin(admin, password);

        AllUsers allUsers = app.db().users(); //список всех пользователей
        UserData user = allUsers.iterator().next(); //получили первого попавшегося пользователя
        String username = user.getUsername(); //имя выбранного пользователя
        String email = user.getEmail();
       // String oldPass = user.getPassword();
        String newPassword = "lalalalala";

        app.registration().gotoResetUsersPassword(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(username, newPassword)); //првоерка, что можем залогиниться под новым паролем
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
