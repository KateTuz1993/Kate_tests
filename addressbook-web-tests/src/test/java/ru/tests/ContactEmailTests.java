package ru.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import org.hamcrest.MatcherAssert;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;


public class ContactEmailTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт. если нет - то создаем его

        if(app.db().contacts().size() == 0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("Хрусталева 97, 61").withGroup("[none]").withHomePhone("+79787397913").withEmail("nikita.baliassniy@gmail.com"),true);
        }
    }

    @Test
    public void testContactEmails(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next(); //получаем список (множество) контактов и выбираем первый попавшийся
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));


    }

    private String mergeEmails(ContactData contact) { //клеим строки
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()) //список е-мейлов
                .stream().filter((s)->!s.equals(""))//фильтруем пустые поля
                .collect(Collectors.joining("\n"));//клеим сроки

    }

}
