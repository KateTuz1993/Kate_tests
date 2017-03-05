package ru.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;
import ru.models.Groups;

import javax.swing.*;
import java.util.Iterator;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() { // проверка выполения предусловий
        if (app.db().contacts().size() == 0) {  //проверка существует ли контакт
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withGroup("[none]").withHomePhone("+79787397913"), true);
        }
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {  //проверка существует ли группа
            app.group().create(new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3"));
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion() {
        Contacts allContacts = app.db().contacts(); // все контакты
        Groups allGroups = app.db().groups(); //все группы

        ContactData modifyingContact = allContacts.iterator().next(); //выбираем первый попавшийся контакт
        GroupData randomGroup = allGroups.iterator().next(); //выбираем первую попавшуюся группу

        Groups linkedGroups = modifyingContact.getGroups(); // связанные группы контакта

        final Random random = new Random();

        if (linkedGroups.equals(allGroups)) { //если контакт уже входит во все группы, то создаем новую группу
            app.goTo().groupPage();

            int index = random.nextInt(); //используется для уникального имени группы
            app.group().create(new GroupData().withName("test " + index).withHeader("test " + index).withFooter("test " + index));
            //добавление контакта в новую группу
            app.goTo().goToHomePage();
            GroupData newGroup = app.db().groups().iterator().next().withName("test " + index);
            app.contact().addToGroup(modifyingContact, newGroup);
        } else {
            if (linkedGroups.size() == 0) {
                app.goTo().goToHomePage();
                app.contact().addToGroup(modifyingContact, randomGroup);
            } else {
                for (GroupData currentGroup : allGroups) {
                    if (!linkedGroups.contains(currentGroup)) {
                        //добавление контакта в группу
                        app.goTo().goToHomePage();
                        app.contact().addToGroup(modifyingContact, currentGroup);
                        break;
                    }
                }
            }

        }


        //ContactData contact = new ContactData().withId(modifyingContact.getId()).withFirstname("Nikita2").withMiddlename("Valerievich").withLastname("Baliassniy2").withCompany("home")
        //        .withAddress("Хрусталева").withHomePhone("+79787397913").withMobilePhone("324").withWorkPhone("5555")
        //       .withEmail("nikita.baliassniy@gmail.com").withEmail2("53465@mail.ru").withEmail3("9786@mail.ru");


        //    app.goTo().goToHomePage(); //необходио для хрома
        //   assertThat(app.contact().count(),equalTo(allContacts.size()));// хешированная проверка

        //    Contacts after = app.db().contacts();
        //    Assert.assertEquals(after.size(),allContacts.size());

        //сравнение множеств контактов до и после модификации
        //     assertThat(after, equalTo(allContacts.without(modifyingContact).withAdded(contact)));

        //чтобы включить - указать в конфигурации теста в поле VM options значение -DverifyUIcontact=true
        //   verifyContactListInUI(); //проверка множества контактов в БД с множеством на странице home

    }
}




