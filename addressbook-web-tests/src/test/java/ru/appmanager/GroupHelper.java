package ru.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.models.GroupData;
import ru.models.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSeletedGroups() {
        click(By.name("delete"));
    }


    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value = '"+ id +"']")).click(); //выбор элемента по id
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null; //обнуляем кеш
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId()); //выбираем последнюю группу для модификации
        initGroupModification();
        //в этой структуре получаем id последней группы в списке
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null; //обнуляем кеш
        returnToGroupPage();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSeletedGroups();
        groupCache = null; //обнуляем кеш
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }


    public int сount() { //возвращает количество групп на странице
        return wd.findElements(By.name("selected[]")).size();
    }


    private Groups groupCache = null; //кеш


    //метод для получения множества групп, состоящий из их названий
    public Groups all() {
        if (groupCache!=null){
            return new Groups(groupCache); // возвращаем копию кеша
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));//получаем id чекбокса, преобразованный в число
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return groupCache;
    }

}
