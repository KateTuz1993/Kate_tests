package ru.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.List;

public class  GroupDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
        app.goTo().groupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (app.group().list().size()==0){
            app.group().create(new GroupData("test1", "test2", "test3"));
        }
    }

    @Test
    public void testgroupDeletion() {

        List<GroupData> before = app.group().list();
        int index  = before.size()-1; //индекс последней группы
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(),index);

        before.remove(before.size()-1); //удаляем последний элемент из списка - нужно для сравнения списков
        Assert.assertEquals(before,after); // сравниваем списки целиком


    }



}
