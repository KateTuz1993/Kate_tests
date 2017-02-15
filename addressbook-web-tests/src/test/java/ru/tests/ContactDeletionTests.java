package ru.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(!app.contact().isThereAContact()){
            app.goTo().addContactPage();
            app.contact().create(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion(){
        List<ContactData> before = app.contact().list();
        int index = before.size()-1; //индекс последнего контакта
        app.contact().delete(index);
        app.goTo().goToHomePage(); //необходио для хрома
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(index); //удаляем последний элемент из списка - нужно для сравнения списков
        Assert.assertEquals(before,after); // сравниваем списки целиком

    }



}
