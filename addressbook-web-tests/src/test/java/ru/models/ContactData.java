package ru.models;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String company;
    private final String address;
    private final String home_tel;
    private String group;

    public ContactData(String firstname, String middlename, String lastname, String company, String address, String home_tel, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.home_tel = home_tel;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHome_tel() {
        return home_tel;
    }

    public String getGroup() {
        return group;
    }
}
