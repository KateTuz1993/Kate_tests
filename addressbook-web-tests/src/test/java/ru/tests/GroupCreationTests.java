package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Set;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test2").withHeader("test3").withFooter("test4");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size() + 1);

        //отыскиваем id добавленной группы: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа GroupData
        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()); //преобразуем объект в число и находим максимальный
        before.add(group);
        Assert.assertEquals(before ,after);//сравнение множеств групп до и после добавления
    }

}
