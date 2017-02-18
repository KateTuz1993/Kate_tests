package ru.tests;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт. если нет - то создаем его

        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("Хрусталева 97, 61").withHome_tel("+79787397913").withGroup("test1"),true);
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
