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
    private int groupId = Integer.MAX_VALUE;

    public int getContactId() {
        return contactId;
    }

    public int getGroupId() {
        return groupId;
    }

    public ContactAndGroupData withId(int id) {
        this.contactId = id;
        return this;
    }
    public ContactAndGroupData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactAndGroupData that = (ContactAndGroupData) o;

        if (contactId != that.contactId) return false;
        return groupId == that.groupId;
    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + groupId;
        return result;
    }

    @Override
    public String toString() {
        return "ContactAndGroupData{" +
                "contactId=" + contactId +
                ", groupId=" + groupId +
                '}';
    }
}