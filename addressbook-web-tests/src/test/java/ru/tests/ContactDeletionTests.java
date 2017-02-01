package ru.tests;


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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptContactDeletion();

    }

}
