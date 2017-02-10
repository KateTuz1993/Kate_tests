package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @Test
    public void testContactModification(){
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(!app.getContactHelper().isThereAContact()){
            app.getNafigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModify(before.size()-1); //выбераем последний контакт
        //в этой структуре получаем id контакта, который модифицируем - т.е. последнего. остальные данный заполняем новыми значениями
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Nikita2", "Valerievich2", "Baliassniy2", "Home2", "nikita.baliassniy@gmail.com", "+79787397913", null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePageFromModify();
        List<ContactData> after = app.getContactHelper().getContactList();
         Assert.assertEquals(after.size(),before.size());

        //сравнение множеств контактов до и после модификации
        before.remove(before.size()-1); //изменяем список контактов: удаляем модифицируемый и добавляем его же с новыми значениями
        before.add(contact);
        //сортировка списков по Id
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);


        //Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after)); //сравнение множеств контактов на странице
        Assert.assertEquals(before , after); //сравнение списков контактов на странице
    }


}
