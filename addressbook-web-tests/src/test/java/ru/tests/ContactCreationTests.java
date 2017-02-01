package ru.tests;

import org.testng.annotations.Test;
import ru.models.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNafigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);

    }

}
