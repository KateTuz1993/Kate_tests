package ru.models;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@Table(name ="addressbook")
public class ContactData {
    @XStreamOmitField //чтобы в файл xml не записывался id
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE; //чтобы создаваемый контакт был всегда последним;

    @Column(name = "firstname")
    private String firstname = "";

    @Column(name = "middlename")
    private String middlename = "";

    @Column(name = "lastname")
    private String lastname = "";

    @Column(name = "company")
   // @Type(type = "text")
    private String company = "";

    @Column(name = "address")
    @Type(type = "text")
    private String address = "";

    @Transient
    private String group = "[none]";

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone = "";


    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone = "";


    @Column(name = "work")
    @Type(type = "text")
    private String workPhone = "";

    @Transient
    private String allPhones = "";


    @Column(name = "email")
    @Type(type = "text")
    private String email = "";


    @Column(name = "email2")
    @Type(type = "text")
    private String email2 = "";


    @Column(name = "email3")
    @Type(type = "text")
    private String email3 = "";

    @Transient
    private String allEmails = "";

    @Column(name = "photo")
    @Type(type = "text")
    private String photo = "";

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

    public File getPhoto() {
        if (photo == null) {
            return null;
        }
        return new File(photo);
    }


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
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +  //добавила
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", group='" + group + '\'' +   //добавила
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", allPhones='" + allPhones + '\'' +   //добавила
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", allEmails='" + allEmails + '\'' +  //добавила
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        return allEmails != null ? allEmails.equals(that.allEmails) : that.allEmails == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        return result;
    }
}
