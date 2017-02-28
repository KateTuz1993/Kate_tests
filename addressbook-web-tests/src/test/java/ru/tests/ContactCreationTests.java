package ru.tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.xerces.xs.XSTerm;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.Contacts;
import ru.models.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @DataProvider //провайдет тестовых данных
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line =  reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml); //приведение типов - считываем объект типа Контакт
            return contacts.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator(); //список массивов с элементами типа Контакт
        }

    }

    @Test (dataProvider = "validContacts")//(enabled = false)
    public void testContactCreation(ContactData contact) {
        Contacts before = app.contact().all();
        //проверка есть ли хоть одна группа? если нет, сначала создаем ее
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test2").withHeader("test2").withFooter("test3"));
        }
        app.goTo().goToHomePage();
        app.goTo().addContactPage();
        File photo = new File("src/2.PNG");
        //ContactData contact = new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("Хрусталева 97,91").withGroup("[none]").withHomePhone("+79787397913").withEmail("nikita.baliassniy@gmail.com").withEmail2("dfsf").withPhoto(photo);
        app.contact().create(contact,true);
        assertThat(app.contact().count(),equalTo(before.size() + 1)); //сравниваем кол-во контактов до начала теста добавления - и после
        Contacts after = app.contact().all();


        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    }

    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File("");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/1.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists()); //существует ли файл картинки
    }

}
