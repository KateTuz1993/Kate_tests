package ru.appmanager;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import ru.models.ContactData;


import java.util.ArrayList;
import java.util.List;
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
        type(By.name("home"),contactData.getHome_tel());

        if (creation){ //если это форма для создания нового контакта
            new org.openqa.selenium.support.ui.Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else { // если это форма модификации контакте
            Assert.assertFalse(isElementPresent(By.name("new_group")));
            //isElementPresent(By.name("new_group")); //проверка, существует ли поле выбора группы на форме создания (или модификации) контакта
        }


    }


    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click(); //выбор элемента по инедксу
        //click(By.name("selected[]"));
         }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void initContactModify(int index) {
        wd.findElements(By.name("entry")).get(index).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).findElement(By.tagName("img")).click();  //выбор элемента по инедксу
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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
    }

    public void modify(int index, ContactData contact) {
        initContactModify(index); //выбераем последний контакт
        //в этой структуре получаем id контакта, который модифицируем - т.е. последнего. остальные данный заполняем новыми значениями
        fillContactForm(contact,false);
        submitContactModification();
        returnToHomePageFromModify();
    }

    public void delete(int index) {
        selectContact(index); //выбираем последний контакт
        deleteSelectedContacts();
        acceptContactDeletion();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    //метод для получения списка контактов, состоящий из фамилий
    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements){
            List<WebElement> tds = element.findElements(By.tagName("td")); // список элементов td  в строке
            int id = Integer.parseInt(tds.get(0).findElement(By.tagName("input")).getAttribute("value"));

            //int id = Integer.parseInt(element.findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).getAttribute("href").substring(46));  //получаем id карандашика
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String address = tds.get(3).getText();
            ContactData contact = new ContactData(id, firstname,null,lastname,null,address,null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
