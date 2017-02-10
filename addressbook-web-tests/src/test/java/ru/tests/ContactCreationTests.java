package ru.tests;

import org.testng.Assert;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.GroupData;


import java.util.HashSet;
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
        ContactData contact = new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1");
        app.getContactHelper().createContact(contact,true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после


        //отыскиваем id добавленной группы: как самый максимальный в списке
        int max = 0;
        for (ContactData g: after){
            if (g.getId() > max)
                max = g.getId();
        }
        contact.setId(max);

        before.add(contact);
        //сравнение множеств групп до и после добавления
        Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after));
    }

}
