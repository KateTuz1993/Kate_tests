package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

import java.util.List;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.getNafigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size() + 1);
    }

}
