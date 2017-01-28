package ru;

import org.testng.annotations.Test;

public class  GroupDeletionTests extends TestBase{
    

    
    @Test
    public void testgroupDeletion() {
       gotoGroupPage();
        selectGroup();
        deleteDeletedGroups();
        returnToGroupPage();
    }

}
