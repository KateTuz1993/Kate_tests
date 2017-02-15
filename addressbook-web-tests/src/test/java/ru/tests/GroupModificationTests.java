package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase
{

   @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
       app.getNafigationHelper().gotoGroupPage();
       //проверка существует ли группа для  модификации. если нет - то создаем ее
       if (!app.getGroupHelper().isThereAGroup()){
           app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
       }
   }
    @Test

    public void testGroupModification(){

        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size()-1; // индекс последней группы
        GroupData group = new GroupData(before.get(index).getId(),"test1", "test5", "test6");
        app.getGroupHelper().modifyGroup(index, group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств групп до и после модификации
        before.remove(index); //изменяем список групп: удаляем модифицируемую и добавляем ее же с новыми значениями
        before.add(group);
        //сортировка списков, чтобы сравнивать не множества, а список
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before ,after); // сравнение списков

    }




}
