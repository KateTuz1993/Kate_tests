package ru.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;


import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHomePhone("+79787397913").withGroup("test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion(){
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        app.contact().delete(deletedContact);
        app.goTo().goToHomePage(); //необходио для хрома
        assertThat(app.contact().count(),equalTo(before.size()-1));// хешированная проверка
        Contacts after = app.contact().all();

        before.remove(deletedContact); //удаляем последний элемент из списка - нужно для сравнения списков
        assertThat(after, equalTo(before.without(deletedContact)));
        Assert.assertEquals(before,after); // сравниваем списки целиком

    }



}
