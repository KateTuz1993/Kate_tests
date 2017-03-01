package ru.tests;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class  GroupCreationTests extends TestBase{



    @DataProvider //провайдет тестовых данных
    public Iterator<Object[]> validGroups() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))){
            String xml = "";
            String line =  reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
        }

    }

    @Test(dataProvider = "validGroups")
    public void  testGroupCreation(GroupData group) {

        app.goTo().groupPage();
        Groups before = app.db().groups();
        // GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().create(group);
        assertThat(app.group().сount(),equalTo(before.size() + 1)); //хешированная проверка
        Groups after = app.db().groups();

        // сравнение множеств групп до и после добавления
        assertThat(after, equalTo(
               before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
        //чтобы включить проверку: указать в конфигурации теста в поле VM options значение -DverifyUI=true
        verifyGroupListInUI();


    }




}
