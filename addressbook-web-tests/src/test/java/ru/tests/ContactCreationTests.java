package ru.tests;

import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;


import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getGroupList();
        app.getNafigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        List<ContactData> after = app.getContactHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после

    }

}
