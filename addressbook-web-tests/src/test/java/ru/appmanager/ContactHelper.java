package ru.appmanager;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import ru.models.ContactData;
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
        wd.findElements(By.name("entry")).get(index).findElements(By.tagName("td")).get(7).findElements(By.tagName("a")).get(0).findElements(By.tagName("img")).get(0).click();  //выбор элемента по инедксу
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void returnToHomePageFromModify() {
        click(By.linkText("home page"));
    }

    public void acceptContactDeletion() { wd.switchTo().alert().accept();    }

    public void createContact(ContactData contact, boolean creation) {

       fillContactForm(contact, creation);
       enteringContactInfo();
       returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


}
