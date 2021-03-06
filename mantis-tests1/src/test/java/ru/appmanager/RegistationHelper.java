package ru.appmanager;

import com.sun.xml.internal.ws.api.message.HeaderList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;


public class RegistationHelper extends HelperBase{

    public RegistationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
       wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
       type(By.name("username"),username);
       type(By.name("email"),email);
       click(By.cssSelector("input[value='Зарегистрироваться']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password") , password);
        type(By.name("password_confirm") , password);
        click(By.cssSelector("button[type='submit']"));
    }

    public void loginAdmin(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        type(By.name("password"),password);
        click(By.cssSelector("input.btn-success"));

    }

    public void gotoResetUsersPassword(String username){
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
        //JOptionPane.showMessageDialog(null,"это в функции "+ username);
        click(By.linkText(username));
        click(By.xpath(".//*[@id='manage-user-reset-form']/fieldset/span/input"));
    }
}
