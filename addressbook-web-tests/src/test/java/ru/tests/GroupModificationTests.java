package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

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
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test5", "test6"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(),before.size());

    }



}
