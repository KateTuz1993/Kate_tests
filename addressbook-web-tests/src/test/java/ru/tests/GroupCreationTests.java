package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.getNafigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test1", "test2", "test3");
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size() + 1);

        //отыскиваем id добавленной группы: как самый максимальный в списке
        // лямбда выражение - сравниватель id объектов типа GroupData
        group.setId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
        before.add(group);
        //сравнение множеств групп до и после добавления
        Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after));
    }

}
