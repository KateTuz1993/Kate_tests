package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase
{

   @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
       app.goTo().groupPage();
       //проверка существует ли группа для  модификации. если нет - то создаем ее
       if (app.group().all().size()==0){
           app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
       }
   }
    @Test

    public void testGroupModification(){

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next(); //первый попавшийся элемент множетсва
        //int index = before.size()-1; // индекс последней группы
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test3").withFooter("test4");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size());

        //сравнение множеств групп до и после модификации
        before.remove(modifiedGroup); //изменяем список групп: удаляем модифицируемую и добавляем ее же с новыми значениями
        before.add(group);
        Assert.assertEquals(before ,after); // сравнение списков

    }




}
