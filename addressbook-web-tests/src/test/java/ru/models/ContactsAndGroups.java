package ru.models;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class ContactsAndGroups extends ForwardingSet<ContactAndGroupData> {

    private Set<ContactAndGroupData> delegate;

    public ContactsAndGroups(Collection<ContactAndGroupData> contacts) {
        this.delegate =  new HashSet<ContactAndGroupData>(contacts);
    }

    @Override
    protected Set<ContactAndGroupData> delegate() {
        return delegate;
    }


}
