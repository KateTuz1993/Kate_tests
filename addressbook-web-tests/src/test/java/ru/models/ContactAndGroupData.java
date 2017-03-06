package ru.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//класс -аналог Контакт дата. Представляет строку таблицы address_in_groups

@Entity
@Table(name = "address_in_groups")
public class ContactAndGroupData {
    @Id
    @Column(name = "id") //это id контакта
    private int contactId = Integer.MAX_VALUE;

    @Column(name = "group_id") //это id группы
    private int groupId = Integer.MIN_VALUE;

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "ContactAndGroupData{" +
                "contactId=" + contactId +
                ", groupId=" + groupId +
                '}';
    }
}