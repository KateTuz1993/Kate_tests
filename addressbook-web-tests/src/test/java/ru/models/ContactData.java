package ru.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name ="addressbook")
public class ContactData {
    @XStreamOmitField //чтобы в файл xml не записывался id
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE; //чтобы создаваемый контакт был всегда последним;

    @Expose
    @Column(name = "firstname")
    @Type(type = "text")
    private String firstname;

    @Expose
    @Column(name = "middlename")
    @Type(type = "text")
    private String middlename;

    @Expose
    @Column(name = "lastname")
    @Type(type = "text")
    private String lastname;

    @Expose
    @Column(name = "company")
    @Type(type = "text")
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    private String group;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    private String allEmails;

    public File getPhoto() {
        return photo;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private File photo;


    //геттеры
    public int getId() { return id;    }
    public String getFirstname() {   return firstname;   }
    public String getMiddlename() {
        return middlename;
    }
    public String getLastname() {
        return lastname;
    }
    public String getCompany() {
        return company;
    }
    public String getAddress() {    return address;  }
    public String getGroup() {   return group;    }
    public String getHomePhone() {   return homePhone;    }
    public String getMobilePhone() {   return mobilePhone;    }
    public String getWorkPhone() {   return workPhone;    }
    public String getEmail() {   return email;    }
    public String getEmail2() {   return email2;    }
    public String getEmail3() {   return email3;    }
    public String getAllPhones() {    return allPhones;   }
    public String getAllEmails() {    return allEmails;   }
   // public String getContactContent() {    return content;   }


    //сеттеры
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }
    public ContactData withWorkPhone(String work) {
        this.workPhone = work;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }




    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }



}
