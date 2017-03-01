package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase
{

   @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
       //проверка существует ли группа для  модификации. если нет - то создаем ее
       app.goTo().groupPage();
       if (app.db().groups().size() == 0){
           app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
       }

   }
    @Test
    public void testGroupModification(){

        Groups before = app.db().groups(); //загружаем список групп из БД
        GroupData modifiedGroup = before.iterator().next(); //первый попавшийся элемент множетсва
        //int index = before.size()-1; // индекс последней группы
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test2").withHeader("test3").withFooter("test4");
        app.goTo().groupPage();
        app.group().modify(group);
        Assert.assertEquals(app.group().сount(),before.size()); // хешированная проверка
        Groups after = app.db().groups();
        //сравнение множеств групп до и после модификации
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGroupListInUI();

    }



}
