package com.example.employe_java.model;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee")
public class ModelEmploye {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birdDate")
    private Date birdDate;

    @Column(name = "basicSallary")
    private int basicSallary;

    @Column(name = "status")
    private String status;

    @Column(name = "groups")
    private String group;

    @Column(name = "description")
    private Date description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirdDate() {
        return birdDate;
    }

    public void setBirdDate(Date birdDate) {
        this.birdDate = birdDate;
    }

    public int getBasicSallary() {
        return basicSallary;
    }

    public void setBasicSallary(int basicSallary) {
        this.basicSallary = basicSallary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDescription() {
        return description;
    }

    public void setDescription(Date description) {
        this.description = description;
    }

    public ModelEmploye(){
    	
    }

    public ModelEmploye(String username,String firstName , String lastName, String email,Date birdDate,int basicSallary,String status,String group,Date description){
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birdDate = birdDate;
        this.basicSallary = basicSallary;
        this.status = status;
        this.group = group;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email +
                ", birdDate=" + birdDate + ",basicSallary=" + basicSallary + ",status=" + status + ",group=" + group + ",description=" + description + " ]";
    }
}
