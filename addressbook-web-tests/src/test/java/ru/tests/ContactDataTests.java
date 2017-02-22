package ru.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.ContactData;

import org.hamcrest.MatcherAssert;


import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;


public class ContactDataTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions() { // проверка выполения предусловий
        //проверка существует ли контакт. если нет - то создаем его

        if(app.contact().all().size()==0){
            app.goTo().addContactPage();
            app.contact().create(new ContactData().withFirstname("Nikita").withLastname("Baliassniy").withAddress("Хрусталева 97,61").withHomePhone("+79787397913").withWorkPhone("+79787-97-13").withMobilePhone("123(3)").withEmail("nikita.balliassniy@gmail.com").withEmail2("n.b@gmail.com").withEmail3("lya-lya").withGroup("[none]"),true);
        }
    }

    @Test
    public void testContactData(){
        app.goTo().goToHomePage();
        ContactData contact = app.contact().all().iterator().next(); //получаем список (множество) контактов и выбираем первый попавшийся

        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        String contactInfoFromViewForm = app.contact().infoFromViewForm(contact);

        MatcherAssert.assertThat(specialCleaner(contactInfoFromViewForm),equalTo(mergeEditData(contactInfoFromEditForm)));

    }

    //этот метод очищает данные со страницы просмотра контакта
    private String specialCleaner(String content) {
        return content.replaceAll("\\s","").replaceAll("-()","").replaceAll("\n",""); //убираем все символы пробелов, табуляций, дефисов, скобок
    }

    private String mergeEditData(ContactData contact) { //клеим строки
        String homePhone = contact.getHomePhone();
        String mobilePhone = contact.getMobilePhone();
        String workPhone = contact.getWorkPhone();

        //проверки - заполнено ли поле. нужны, чтобы добавить буковки вначале номеров
        if (!homePhone.equals("")) homePhone = "H: "+ homePhone;
        if (!mobilePhone.equals("")) mobilePhone = "M: "+ mobilePhone;
        if (!workPhone.equals("")) workPhone = "W: "+ workPhone;

        return Arrays.asList(
                contact.getFirstname(), contact.getLastname(),
                contact.getAddress(),homePhone, mobilePhone, workPhone,
                contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream()
                .filter((s)->!s.equals("")) //выбрасываем пустые элементы
                .map(ContactDataTests::cleaned) //применяем к каждому элементу потока очистку
                .collect(Collectors.joining("")); //склеиваем и между склеиваемыми значениями вставляем пустую строку

    }

    //этот метод очищает данные со страницы редактирования контакта
    public static String cleaned(String contactData){
        return contactData.replaceAll("\\s","").replaceAll("-()",""); //убираем все символы пробелов, табуляций, дефисов, скобок
    }
}
