package ru.tests;

import org.testng.annotations.Test;
import ru.models.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        app.getContactHelper().initContactModify();
        app.getContactHelper().fillContactForm(new ContactData("Nikita2", "Valerievich2", "Baliassniy2", "Home", "nikita.baliassniy@gmail.com", "+79787397913"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageFromModify();
    }


}
