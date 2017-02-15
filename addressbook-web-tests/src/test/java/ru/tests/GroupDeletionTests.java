package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.Set;

public class  GroupDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
        app.goTo().groupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testgroupDeletion() {

        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next(); //первый попавшийся элемент множетсва
        //int index  = before.size()-1; //индекс последней группы
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(deletedGroup); //удаляем последний элемент из списка - нужно для сравнения списков
        Assert.assertEquals(before,after); // сравниваем списки целиком


    }



}
