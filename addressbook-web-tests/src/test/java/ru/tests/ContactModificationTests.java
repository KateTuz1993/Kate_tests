package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHome_tel("+79787397913").withGroup("test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        int index = before.size()-1;
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikita2").withMiddlename("Valerievich2").withLastname("Baliassniy2").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHome_tel("+79787397913");

        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств контактов до и после модификации
        before.remove(modifiedContact); //изменяем список контактов: удаляем модифицируемый и добавляем его же с новыми значениями
        before.add(contact);
        Assert.assertEquals(before , after); //сравнение списков контактов на странице
    }




}
