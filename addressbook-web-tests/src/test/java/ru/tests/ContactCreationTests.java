package ru.tests;

import org.testng.Assert;

import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.GroupData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test //(enabled = false)
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            //app.goTo().groupPage();
            app.group().create(new GroupData("test1", "test2", "test3"));
        }
        app.goTo().goToHomePage();
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1");
        app.getContactHelper().createContact(contact,true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после

        //отыскиваем id добавленного контакта: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа ContactData
        contact.setId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
        before.add(contact);
        //сортировка списков по Id
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        //Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after));//сравнение множеств групп до и после добавления
        Assert.assertEquals(before ,after);//сравнение множеств групп до и после добавления
    }

}
