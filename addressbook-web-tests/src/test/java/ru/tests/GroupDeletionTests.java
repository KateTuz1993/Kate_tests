package ru.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.models.GroupData;

public class  GroupDeletionTests extends TestBase{
    

    
    @Test
    public void testgroupDeletion() {

        app.getNafigationHelper().gotoGroupPage();
        //проверка существует ли группа для удаления. если нет - то создаем ее
        if (!app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSeletedGroups();
        app.getGroupHelper().returnToGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after,before-1);
    }

}
