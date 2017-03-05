package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;
import ru.models.Groups;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        Groups groupsList = app.db().groups();

        if(app.db().contacts().size()==0){
            if (app.db().groups().size() == 0){
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            Groups groups = app.db().groups();
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withLastname("Baliassniy")
                    .withAddress("nikita.baliassniy@gmail.com").withHomePhone("+79787397913").inGroup(groups.iterator().next()),true);
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        int index = before.size()-1;
        assertThat(app.contact().count(),equalTo(before.size()));// хешированная проверка

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikita2").withMiddlename("Valerievich").withLastname("Baliassniy2").withCompany("home")
                .withAddress("Хрусталева").withHomePhone("+79787397913").withMobilePhone("324").withWorkPhone("5555")
                .withEmail("nikita.baliassniy@gmail.com").withEmail2("53465@mail.ru").withEmail3("9786@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств контактов до и после модификации
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

        //чтобы включить - указать в конфигурации теста в поле VM options значение -DverifyUIcontact=true
         verifyContactListInUI(); //проверка множества контактов в БД с множеством на странице home

    }




}
