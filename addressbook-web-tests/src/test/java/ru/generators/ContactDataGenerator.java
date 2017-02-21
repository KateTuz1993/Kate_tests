package ru.generators;

import com.sun.javafx.binding.StringFormatter;
import ru.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]); //количесвто контактов
        File file = new File(args[1]); //путь к файлу

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static List<ContactData> generateContacts(int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactData().withFirstname(String.format("Екатерина %s", i))
                    .withLastname(String.format("Тужилкина %s", i))
                    .withCompany(String.format("home %s", i))
                    .withAddress(String.format("Хрусталева 97, 61 %s", i))
                    .withHomePhone(String.format("32 11 58 %s", i))
                    .withMobilePhone(String.format("+7 978 740 74 21 %s", i))
                    .withEmail(String.format("tuz.kat@gmail.com %s", i))
                    .withEmail2(String.format("t.k@gmail.com %s", i)));

        return contacts;
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),contact.getLastname(),contact.getCompany(), contact.getAddress(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getEmail(), contact.getEmail2()));
        }
        writer.close();
    }








}
