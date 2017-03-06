package ru.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;
import ru.models.Groups;

import javax.swing.*;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {

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
    public void testContactRemovingFromGroup() {
        Contacts allContacts = app.db().contacts(); // все контакты
        //ContactData anyContact = allContacts.iterator().next(); //выбираем первый попавшийся контакт
        String selectedGroupName; // имя группы из которой удаляется контакт
        ContactData selectedContact = app.db().contacts().iterator().next();
        Groups beforeGroups = app.db().contacts().getById(selectedContact.getId()).getGroups();
        boolean contactInGroupIsPresent = false; //флажок существования контакта, состоящего в какой-либо группе
        for (ContactData anyContact: allContacts){
            if(anyContact.getGroups().size() != 0){
                selectedContact = anyContact;
                contactInGroupIsPresent = true;
                break;
            }
        }
        if(!contactInGroupIsPresent){
            GroupData anyGroup = app.db().groups().iterator().next();
            selectedGroupName = anyGroup.getName();
            app.contact().addToGroup(selectedContact, anyGroup); //добавляем контакт в группу
        }
        else {
            selectedGroupName = selectedContact.getGroups().iterator().next().getName();
        }
        app.contact().removeContactFromGroup(selectedContact, selectedGroupName);


      //  Groups linkedGroups = modifyingContact.getGroups(); // связанные группы контакта
      //  JOptionPane.showMessageDialog(null, "начало" + linkedGroups.size());

        //проверка что контакт добавлен в группу - сравниваем количество групп
        Groups afterGroups = app.db().contacts().getById(selectedContact.getId()).getGroups();
        if (beforeGroups.size() == 0){
            assertThat(afterGroups.size(), equalTo(beforeGroups.size()));
        } else {
            //JOptionPane.showMessageDialog(null, "после удаления" + afterGroups.size());
            assertThat(afterGroups.size(), equalTo(beforeGroups.size() - 1));
        }
    }
}




