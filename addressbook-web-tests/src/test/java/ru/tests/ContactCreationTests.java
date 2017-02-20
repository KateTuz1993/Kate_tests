package ru.tests;

import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test //(enabled = false)
    public void testContactCreation() {
        Contacts before = app.contact().all();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test2").withHeader("test2").withFooter("test3"));
        }
        app.goTo().goToHomePage();
        app.goTo().addContactPage();
        ContactData contact = new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("Хрусталева 97,91").withHomePhone("+79787397913").withEmail("nikita.baliassniy@gmail.com").withEmail2("dfsf").withGroup("test2");
        app.contact().create(contact,true);
        assertThat(app.contact().count(),equalTo(before.size() + 1)); //сравниваем кол-во контактов до начала теста добавления - и после
        Contacts after = app.contact().all();


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    }

}
