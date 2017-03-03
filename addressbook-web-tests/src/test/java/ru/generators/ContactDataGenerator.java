package ru.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.sun.javafx.binding.StringFormatter;
import com.thoughtworks.xstream.XStream;
import ru.models.ContactData;
import ru.models.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

// конфигурация  в program arguments: -c 15 -f src\test\resourсes\contacts.csv -d xml
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count; //количество контактов

    @Parameter(names = "-f", description = "Target file")
    public String file; //сам файл

    @Parameter(names = "-d", description = "Data format")
    public String format;

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
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }


    private List<ContactData> generateContacts(int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++)
            contacts.add(new ContactData().withFirstname(String.format("Екатерина %s", i))
                    .withMiddlename(String.format("Александровна %s", i))
                    .withLastname(String.format("Тужилкина %s", i))
                    .withCompany(String.format("home %s", i))
                    .withAddress(String.format("Хрусталева 97, 61 %s", i))
                    .withHomePhone(String.format("32 11 58 %s", i))
                    .withMobilePhone(String.format("+7 978 740 74 21 %s", i))
                    .withWorkPhone(String.format("+12345 %s", i))
                    .withEmail(String.format("tuz.kat@gmail.com %s", i))
                    .withEmail2(String.format("t.k@gmail.com %s", i))
                    .withEmail3(String.format("t.k@otr.ru %s", i))
                    .withGroup(String.format("[none]%s", "")));

        return contacts;
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }
    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts){
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),contact.getLastname(),contact.getCompany(), contact.getAddress(),
                        contact.getHomePhone(), contact.getMobilePhone(), contact.getEmail(), contact.getEmail2(), contact.getGroup()));
            }
        }

    }








}