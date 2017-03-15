package ru.models;

//import com.mysql.cj.api.x.Table;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
//import org.dom4j.Entity;

import javax.persistence.*;

@XStreamAlias("user")
@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @XStreamOmitField //чтобы в файл xml не записывался id
    @Id
    @Column(name = "id")
    private int id = 0;

    @Column(name = "username")
    //@Type(type = "text")
    private String username = "";

    @Column(name = "email")
    private String email = "";

    @Column(name = "password")
   // @Type(type = "text")
    private String password = "";

    //геттеры
    public int getId() { return id;    }
    public String getUsername() {   return username;   }
    public String getEmail() {   return email;   }
    public String getPassword() {   return password;   }

    //сеттеры
    public UserData withId(int id) {
        this.id = id;
        return this;
    }
    public UserData withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (id != userData.id) return false;
        if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
        if (email != null ? !email.equals(userData.email) : userData.email != null) return false;
        return password != null ? password.equals(userData.password) : userData.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
