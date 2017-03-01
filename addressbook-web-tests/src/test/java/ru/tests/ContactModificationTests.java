package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(app.db().contacts().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withLastname("Baliassniy").withAddress("nikita.baliassniy@gmail.com").withHomePhone("+79787397913").withGroup("test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        int index = before.size()-1;
        assertThat(app.contact().count(),equalTo(before.size()));// хешированная проверка
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikita2").withLastname("Baliassniy2").withAddress("nikita.baliassniy@gmail.com").withHomePhone("+79787397913");

        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств контактов до и после модификации
       assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }




}
