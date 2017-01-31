package ru.tests;

import org.testng.annotations.Test;
import ru.models.GroupData;

public class GroupModificationTests extends TestBase
{

    @Test

    public void testGroupModification(){
        app.getNafigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

    }



}
