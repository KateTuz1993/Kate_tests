package ru.models;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {


    private Set<GroupData> delegate;

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);
    }

    public Groups() {
        this.delegate = new HashSet<GroupData>();
    }

    @Override
    protected Set delegate() {
        return delegate;
    }

    public Groups withAdded (GroupData group){ //возвращает множество групп после добавления
        Groups groups = new Groups(this);//строим копию существующего объекта
        groups.add(group);
        return groups;
    }

    public Groups without (GroupData group){  //возвращает множество групп после удаления
        Groups groups = new Groups(this);//строим копию существующего объекта
        groups.remove(group);
        return groups;
    }




}
