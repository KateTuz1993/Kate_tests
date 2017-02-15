package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase
{

   @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
       app.goTo().groupPage();
       //проверка существует ли группа для  модификации. если нет - то создаем ее
       if (app.group().list().size()==0){
           app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
       }
   }
    @Test

    public void testGroupModification(){

        List<GroupData> before = app.group().list();
        int index = before.size()-1; // индекс последней группы
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("test2").withHeader("test3").withFooter("test4");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
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
