package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(!app.getContactHelper().isThereAContact()){
            app.getNafigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModify(before.size()-1); //выбераем последний контакт
        app.getContactHelper().fillContactForm(new ContactData("Nikita2", "Valerievich2", "Baliassniy2", "Home2", "nikita.baliassniy@gmail.com", "+79787397913", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageFromModify();
        List<ContactData> after = app.getContactHelper().getContactList();
         Assert.assertEquals(after.size(),before.size());
    }


}
