package ru.tests;

import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {

        int before = app.getContactHelper().getContactCount();
        app.getNafigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before + 1);
    }

}
