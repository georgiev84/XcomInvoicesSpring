package com.petar.workspring.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "partners_login")
public class Partner extends BaseEntity{

    private String username;
    private String password;
    private String company;
    private String city;
    private String mol;
    private String address;
    private String bulstat;
    private String taxNo;


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

    @Column(name = "city", columnDefinition = "nvarchar(255)")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "mol", columnDefinition = "nvarchar(255)")
    public String getMol() {
        return mol;
    }

    public void setMol(String mol) {
        this.mol = mol;
    }

    @Column(name = "address", columnDefinition = "nvarchar(255)")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "bulstat", columnDefinition = "nvarchar(255)")
    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        this.bulstat = bulstat;
    }

    @Column(name = "taxno", columnDefinition = "nvarchar(255)")
    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    @Override
    public String toString() {
        return company + 
                ", Град: " + city +
                ", МОЛ: " + mol +
                ", Адрес: " + address +
                ", Булстат: " + bulstat +
                ", ДДС Номер: " + taxNo ;
    }
}
