package com.petar.workspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partners_login")
public class Partner extends BaseEntity{

    private String username;
    private String password;
    private String company;

    public Partner() {
    }

    @Column(name = "email", columnDefinition = "nvarchar(255)")
    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    @Column(name = "note2", columnDefinition = "nvarchar(255)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "company", columnDefinition = "nvarchar(255)")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
