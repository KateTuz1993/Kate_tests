package ru.models;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate =  new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) { this.delegate =  new HashSet<ContactData>(contacts);    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded (ContactData contact){ //возвращает множество контактов после добавления
        Contacts contacts = new Contacts(this);//строим копию существующего объекта
        contacts.add(contact);
        return contacts;
    }

    public Contacts without (ContactData contact){  //возвращает множество контактов после удаления
        Contacts contacts = new Contacts(this);//строим копию существующего объекта
        contacts.remove(contact);
        return contacts;
    }

    public ContactData getById(int id) {
        Contacts contacts = new Contacts(this);
        for (ContactData contact: contacts){
            if (contact.getId() == id)
                return contact;
        }
        return null;
    }
}
