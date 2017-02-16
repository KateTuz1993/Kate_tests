package ru.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;


import java.util.Set;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт для модификаци. если нет - то создаем его

        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withMiddlename("Valerievich").withLastname("Baliassniy").withCompany("Home").withAddress("nikita.baliassniy@gmail.com").withHome_tel("+79787397913").withGroup("test1"),true);
        }
    }

    @Test //(enabled = false)
    public void testContactDeletion(){
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next(); //выбираем первый попавшийся контакт
        app.contact().delete(deletedContact);
        app.goTo().goToHomePage(); //необходио для хрома
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(deletedContact); //удаляем последний элемент из списка - нужно для сравнения списков
        Assert.assertEquals(before,after); // сравниваем списки целиком

    }



}
