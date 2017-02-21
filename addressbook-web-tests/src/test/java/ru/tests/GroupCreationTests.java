package ru.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class  GroupCreationTests extends TestBase{

    @DataProvider //провайдет тестовых данных
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resourses/groups.csv")));
        String line =  reader.readLine();
        while (line != null) {
            String[] split = line.split(";");//локальная переменная с прочитанной строкой из файла
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }


        //list.add(new Object[]{new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
        //list.add(new Object[]{new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
        //list.add(new Object[]{new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void  testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        // GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().create(group);
        assertThat(app.group().сount(),equalTo(before.size() + 1)); //хешированная проверка
        Groups after = app.group().all();

        // сравнение множеств групп до и после добавления
        assertThat(after, equalTo(
               before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }




}
