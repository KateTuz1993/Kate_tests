package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(!app.getContactHelper().isThereAContact()){
            app.getNafigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().initContactModify(before-1);
        app.getContactHelper().fillContactForm(new ContactData("Nikita2", "Valerievich2", "Baliassniy2", "Home2", "nikita.baliassniy@gmail.com", "+79787397913", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageFromModify();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before);
    }


}
