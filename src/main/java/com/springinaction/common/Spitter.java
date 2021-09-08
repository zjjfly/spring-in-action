package com.springinaction.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by zjjfly on 2016/12/30.
 */
@Entity
public class Spitter implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2,max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min=2,max = 30,message = "{lastName.size}")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email(message = "{email.valid}")
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min=5,max = 16,message = "{username.size}")
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(min=5,max = 80,message = "{password.size}")
    @Column(name = "password")
    private String password;


    public Spitter() {

    }

    public Spitter(String username, String password, String firstName, String lastNam) {
        this(null, username, password, firstName, lastNam, null);
    }

    public Spitter(Long id, String username, String password, String firstName, String lastNam) {
        this(id, username, password, firstName, lastNam, null);
    }

    public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Spitter spitter = (Spitter) o;

        return new EqualsBuilder()
                .append(firstName, spitter.firstName)
                .append(lastName, spitter.lastName)
                .append(email, spitter.email)
                .append(username, spitter.username)
                .append(password, spitter.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .append(email)
                .append(username)
                .append(password)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Spitter{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
