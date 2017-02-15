package ru.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.List;

public class  GroupDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
        app.getNafigationHelper().gotoGroupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
    }

    @Test
    public void testgroupDeletion() {

        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().deleteSeletedGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size()-1);

        before.remove(before.size()-1); //удаляем последний элемент из списка - нужно для сравнения списков
        Assert.assertEquals(before,after); // сравниваем списки целиком


    }

}
