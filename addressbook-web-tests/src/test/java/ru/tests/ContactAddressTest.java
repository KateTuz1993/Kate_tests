package ru.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import org.hamcrest.MatcherAssert;


import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;


public class ContactAddressTest extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт. если нет - то создаем его

        if(app.db().contacts().size() == 0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("Хрусталева 97,61").withHomePhone("+79787397913").withEmail("nikita.balliassniy@gmail.com").withGroup("[none]"),true);
        }
    }

    @Test
    public void testAddress(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next(); //получаем список (множество) контактов и выбираем первый попавшийся
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAddress().trim(),equalTo(mergeAddress(contactInfoFromEditForm)));


    }

    private String mergeAddress(ContactData contact) { //клеим строки
        return Arrays.asList(contact.getAddress())
                .stream().filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n"));

    }

}
