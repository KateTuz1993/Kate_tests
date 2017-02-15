package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Comparator;
import java.util.List;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData("test1", "test2", "test3");
        app.group().create(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        //отыскиваем id добавленной группы: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа GroupData
        group.setId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
        before.add(group);
        //сортировка списков, чтобы сравнивать не множества, а список
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);

        //Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after));//сравнение множеств групп до и после добавления
        Assert.assertEquals(before ,after);//сравнение множеств групп до и после добавления
    }

}
