package ru.tests;

import org.testng.annotations.Test;
import ru.models.GroupData;

public class GroupModificationTests extends TestBase
{

    @Test

    public void testGroupModification(){
        app.getNafigationHelper().gotoGroupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test5", "test6"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }



}
