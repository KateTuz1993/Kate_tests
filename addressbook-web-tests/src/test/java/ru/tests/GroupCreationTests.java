package ru.tests;

import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class  GroupCreationTests extends TestBase{

    @Test
    public void  testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        assertThat(app.group().сount(),equalTo(before.size() + 1)); //хешированная проверка
        Groups after = app.group().all();

        // сравнение множеств групп до и после добавления
        assertThat(after, equalTo(
               before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));

    }




}
