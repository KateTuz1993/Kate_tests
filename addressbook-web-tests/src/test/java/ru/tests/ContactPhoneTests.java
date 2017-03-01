package ru.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import org.hamcrest.MatcherAssert;


import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;


public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт. если нет - то создаем его

        if(app.db().contacts().size() == 0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withAddress("Хрусталева 97, 61").withHomePhone("+79787397913").withGroup("[none]"),true);
        }
    }

    @Test
    public void testContactPhones(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next(); //получаем список (множество) контактов и выбираем первый попавшийся
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


        MatcherAssert.assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));


    }

    private String mergePhones(ContactData contact) { //клеим строки
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()) //список телефонов
        .stream().filter((s)->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }
    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]",""); //убираем в телефонах все символы пробелов, табуляций, дефисов, скобок
    }
}
