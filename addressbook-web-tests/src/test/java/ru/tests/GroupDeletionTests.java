package ru.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class  GroupDeletionTests extends TestBase{

    @BeforeMethod
    public  void ensurePreconditions(){ // проверка выполения предусловий
        app.goTo().groupPage();
        //проверка существует ли группа для  модификации. если нет - то создаем ее
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test
    public void testgroupDeletion() {

        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next(); //первый попавшийся элемент множетсва
        app.group().delete(deletedGroup);
        assertThat(app.group().сount(), equalTo(before.size()-1));
        Groups after = app.group().all();
        Assert.assertEquals(after.size(),before.size()-1);




    }



}
