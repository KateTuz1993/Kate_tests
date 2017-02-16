package ru.tests;

import org.testng.Assert;

import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test //(enabled = false)
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            //app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        app.goTo().goToHomePage();
        app.goTo().addContactPage();
        ContactData contact = new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHome_tel("+79787397913").withGroup("test1");
        app.contact().create(contact,true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() + 1); //сравниваем кол-во групп до начала теста добавления - и после

        //отыскиваем id добавленного контакта: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа ContactData
        //contact.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
         contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()); //преобразуем объект в число и находим максимальный);
         before.add(contact);
        //сортировка списков по Id
         Assert.assertEquals(before ,after);//сравнение множеств групп до и после добавления
    }

}
