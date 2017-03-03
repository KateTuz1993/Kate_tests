package ru.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;


import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для удаления. если нет - то создаем его

        if(app.db().contacts().size() == 0){
            app.goTo().groupPage();
            //проверка существует ли группа для  модификации. если нет - то создаем ее
            if (app.db().groups().size() == 0){
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHomePhone("+79787397913").withGroup("[none]"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        app.contact().delete(deletedContact);
        app.goTo().goToHomePage(); //необходио для хрома
        assertThat(app.contact().count(),equalTo(before.size()-1));// хешированная проверка
        Contacts after = app.db().contacts();

        before.remove(deletedContact); //удаляем последний элемент из списка - нужно для сравнения списков
        assertThat(after, equalTo(before.without(deletedContact)));
        Assert.assertEquals(before,after); // сравниваем списки целиком
        //чтобы включить - указать в конфигурации теста в поле VM options значение -DverifyUIcontact=true
        verifyContactListInUI(); //проверка множества контактов в БД с множеством на странице home

    }



}
