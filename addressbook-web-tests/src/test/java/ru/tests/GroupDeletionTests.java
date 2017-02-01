package ru.tests;

import org.openqa.selenium.By;
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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSeletedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
