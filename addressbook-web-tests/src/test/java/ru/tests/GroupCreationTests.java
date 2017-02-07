package ru.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.getNafigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after,before + 1);
    }

}
