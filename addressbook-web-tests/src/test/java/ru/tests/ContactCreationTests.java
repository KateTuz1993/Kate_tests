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
        List<ContactData> before = app.contact().list();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()){
            //app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        app.goTo().goToHomePage();
        app.goTo().addContactPage();
        ContactData contact = new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHome_tel("+79787397913").withGroup("test1");
        app.contact().create(contact,true);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после

        //отыскиваем id добавленного контакта: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа ContactData
        contact.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
        before.add(contact);
        //сортировка списков по Id
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);

        //Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after));//сравнение множеств групп до и после добавления
        Assert.assertEquals(before ,after);//сравнение множеств групп до и после добавления
    }

}
