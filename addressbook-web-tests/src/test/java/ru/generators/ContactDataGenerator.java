package ru.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.sun.javafx.binding.StringFormatter;
import ru.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count; //количество контактов

    @Parameter(names = "-f", description = "Target file")
    public String file; //сам файл

    public static void main(String[] args) throws IOException {
        // int count = Integer.parseInt(args[0]); //количесвто контактов
        //File file = new File(args[1]); //путь к файлу

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }


    private List<ContactData> generateContacts(int count){
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

    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),contact.getLastname(),contact.getCompany(), contact.getAddress(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getEmail(), contact.getEmail2()));
        }
        writer.close();
    }








}