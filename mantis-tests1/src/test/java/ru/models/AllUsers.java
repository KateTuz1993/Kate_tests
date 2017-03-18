package ru.models;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AllUsers extends ForwardingSet<UserData> {
    private Set<UserData> delegate;
    public AllUsers(AllUsers contacts) {
        this.delegate =  new HashSet<UserData>(contacts.delegate);
    }

    public AllUsers() {
        this.delegate = new HashSet<UserData>();
    }

    public AllUsers(Collection<UserData> users) {
        this.delegate =  new HashSet<UserData>(users);
    }


    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

}
