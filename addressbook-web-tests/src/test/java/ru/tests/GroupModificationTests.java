package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase
{

    @Test

    public void testGroupModification(){
        app.getNafigationHelper().gotoGroupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        //int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before.size()-1); //выбираем последнюю группу для модификации
        app.getGroupHelper().initGroupModification();
        //в этой структуре получаем id последней группы в списке
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"test1", "test5", "test6");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств групп до и после модификации
        before.remove(before.size()-1); //изменяем список групп: удаляем модифицируемую и добавляем ее же с новыми значениями
        before.add(group);
        //сортировка списков, чтобы сравнивать не множества, а список
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        //Assert.assertEquals(new HashSet<Object>(before) ,new HashSet<Object>(after)); // сравнение множеств
        Assert.assertEquals(before ,after); // сравнение списков

    }



}
