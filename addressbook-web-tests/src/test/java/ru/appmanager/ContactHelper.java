package ru.appmanager;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import ru.models.ContactData;
import ru.models.Contacts;
import javax.swing.JOptionPane;

import java.util.*;
//import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.xpath("//div/div[4]/div/i/a[2]"));
    }

    public void enteringContactInfo() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("middlename"),contactData.getMiddlename());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
 //       type(By.name("home"),contactData.getHomePhone());

        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());



        if (creation){ //если это форма для создания нового контакта
            new org.openqa.selenium.support.ui.Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else { // если это форма модификации контакте
            Assert.assertFalse(isElementPresent(By.name("new_group")));
            //isElementPresent(By.name("new_group")); //проверка, существует ли поле выбора группы на форме создания (или модификации) контакта
        }


    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id +"']")).click(); //выбор элемента по id
        //click(By.name("selected[]"));
    }


    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void initContactModifyById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click(); //выбор элемента по id
        //wd.findElement(By.name("entry")).get(index).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).findElement(By.tagName("img")).click();  //выбор элемента по индексу
    }
    public void initContactViewById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click(); //выбор элемента по id - нажатие на человечка
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void returnToHomePageFromModify() {
        click(By.linkText("home page"));
    }

    public void acceptContactDeletion() { wd.switchTo().alert().accept();    }

    public void create(ContactData contact, boolean creation) {

       fillContactForm(contact, creation);
       enteringContactInfo();

       returnToHomePage();
        contactCache = null; //обнуляем кеш
    }

    public void modify(ContactData contact) {
        initContactModifyById(contact.getId()); //выбираем любой контакт
        //в этой структуре получаем id контакта, который модифицируем - т.е. последнего. остальные данный заполняем новыми значениями
        fillContactForm(contact,false);
        submitContactModification();
        contactCache = null; //обнуляем кеш
        returnToHomePageFromModify();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId()); //выбираем последний контакт
        deleteSelectedContacts();
        acceptContactDeletion();
        contactCache = null; //обнуляем кеш
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    private Contacts contactCache = null; //кеш

    //метод для получения множества контактов, состоящий из фамилий
    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache); // возвращаем копию кеша
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements){
            List<WebElement> tds = element.findElements(By.tagName("td")); // список элементов td  в строке
            int id = Integer.parseInt(tds.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String address = tds.get(3).getText();
            String allEmails = tds.get(4).getText();
            String allPhones = tds.get(5).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones);

            contactCache.add(contact);
        }
        return contactCache;
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModifyById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).
                withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    public String infoFromViewForm(ContactData contact) {
        initContactViewById(contact.getId());
        String content = wd.findElement(By.id("content")).getText();//getAttribute("value");
       // JOptionPane.showMessageDialog(null, content); //вывод в сообщении содержимого переменной (для проверки)
        wd.navigate().back();
        return content;

    }


}
