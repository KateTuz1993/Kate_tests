package ru.tests;

import org.testng.annotations.Test;
import ru.models.GroupData;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.getNafigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }

}
