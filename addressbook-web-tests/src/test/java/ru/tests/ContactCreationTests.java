package ru.tests;

import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.GroupData;


import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.getNafigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            //app.getNafigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getNafigationHelper().goToHomePage();
        app.getNafigationHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после

    }

}
