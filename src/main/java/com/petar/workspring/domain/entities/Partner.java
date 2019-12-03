package com.petar.workspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partners_login")
public class Partner extends BaseEntity{

    private String email;
    private String password;

    public Partner() {
    }

    @Column(name = "email", columnDefinition = "nvarchar(255)")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "note2", columnDefinition = "nvarchar(255)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
