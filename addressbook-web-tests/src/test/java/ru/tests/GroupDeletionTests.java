package ru.tests;

import org.testng.annotations.Test;

public class  GroupDeletionTests extends TestBase{
    

    
    @Test
    public void testgroupDeletion() {
        app.gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteDeletedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
