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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id +"']")).click(); //выбор элемента по id
        //click(By.name("selected[]"));
    }


    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void initContactModify(int index) {
        wd.findElements(By.name("entry")).get(index).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).findElement(By.tagName("img")).click();  //выбор элемента по инедксу
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void initContactModifyById(int id) {
        //wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr['" + id + "']/td[8]/a/img")).click();
       
        wd.findElement(By.cssSelector("a[href = edit.php?id='" + id + "']")).click();
        //wd.findElement(By.tagName("td")).findElement(By.cssSelector("input[value='"+ id +"']")).click();
        //wd.findElements(By.tagName("td")).get(7).findElement(By.cssSelector("a[href = http://localhost:8080/addressbook/edit.php?id='"+ id +"']")).click(); //выбор элемента по id
        //wd.findElement(By.name("entry")).get(index).findElements(By.tagName("td")).get(7).findElement(By.tagName("a")).findElement(By.tagName("img")).click();  //выбор элемента по id
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

    public void modify(ContactData contact) {
        initContactModifyById(contact.getId()); //выбираем любой контакт
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
    public void delete(ContactData contact) {
        selectContactById(contact.getId()); //выбираем последний контакт
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
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String address = tds.get(3).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address);

            contacts.add(contact);
        }
        return contacts;
    }

    //метод для получения множества контактов, состоящий из фамилий
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements){
            List<WebElement> tds = element.findElements(By.tagName("td")); // список элементов td  в строке
            int id = Integer.parseInt(tds.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String address = tds.get(3).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address);

            contacts.add(contact);
        }
        return contacts;
    }


}
