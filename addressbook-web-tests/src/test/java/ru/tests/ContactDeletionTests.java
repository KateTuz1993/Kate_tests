package ru.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.ContactData;
import ru.models.GroupData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion(){
        //проверка существует ли контакт для удаления. если нет - то создаем его
        if (!app.getContactHelper().isThereAContact()) {
            app.getNafigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Nikita", "Valerievich", "Baliassniy", "Home", "nikita.baliassniy@gmail.com", "+79787397913", "test1"), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptContactDeletion();
        app.getNafigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before-1);

    }

}
