package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(app.contact().list().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactModification(){

        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        ContactData contact = new ContactData(before.get(index).getId(),"Nikita2", "Valerievich2", "Baliassniy2", "Home2", "nikita.baliassniy@gmail.com", "+79787397913", null);

        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств контактов до и после модификации
        before.remove(index); //изменяем список контактов: удаляем модифицируемый и добавляем его же с новыми значениями
        before.add(contact);
        //сортировка списков по Id
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);


        Assert.assertEquals(before , after); //сравнение списков контактов на странице
    }




}
