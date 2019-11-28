package com.petar.workspring.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

//@Entity
//@Table(name = "Partners")
public class PartnersEntity implements Serializable {
//    private int id;
//    private String code;
//    private String company;
//    private String mol;
//    private String city;
//    private String address;
//    private String address2;
//    private String phone;
//    private String fax;
//    private String eMail;
//    private String taxNo;
//    private String bulstat;
//    private String bankName;
//    private String bankCode;
//    private String bankAcct;
//    private String bankVatName;
//    private String bankVatCode;
//    private String bankVatAcct;
//    private Integer priceGroup;
//    private Double discount;
//    private Short type;
//    private Short isVeryUsed;
//    private Integer userId;
//    private Integer groupId;
//    private Timestamp userRealTime;
//    private Integer deleted;
//    private String cardNumber;
//    private String rowguid;
//    private String company2;
//    private String mol2;
//    private String phone2;
//    private String city2;
//    private String note1;
//    private String note2;
//    private Integer paymentDays;
//
//    @Id
//    @Column(name = "ID")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "Code")
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    @Basic
//    @Column(name = "Company")
//    public String getCompany() {
//        return company;
//    }
//
//    public void setCompany(String company) {
//        this.company = company;
//    }
//
//    @Basic
//    @Column(name = "MOL")
//    public String getMol() {
//        return mol;
//    }
//
//    public void setMol(String mol) {
//        this.mol = mol;
//    }
//
//    @Basic
//    @Column(name = "City")
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    @Basic
//    @Column(name = "Address")
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    @Basic
//    @Column(name = "Address2")
//    public String getAddress2() {
//        return address2;
//    }
//
//    public void setAddress2(String address2) {
//        this.address2 = address2;
//    }
//
//    @Basic
//    @Column(name = "Phone")
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    @Basic
//    @Column(name = "Fax")
//    public String getFax() {
//        return fax;
//    }
//
//    public void setFax(String fax) {
//        this.fax = fax;
//    }
//
//    @Basic
//    @Column(name = "eMail")
//    public String geteMail() {
//        return eMail;
//    }
//
//    public void seteMail(String eMail) {
//        this.eMail = eMail;
//    }
//
//    @Basic
//    @Column(name = "TaxNo")
//    public String getTaxNo() {
//        return taxNo;
//    }
//
//    public void setTaxNo(String taxNo) {
//        this.taxNo = taxNo;
//    }
//
//    @Basic
//    @Column(name = "Bulstat")
//    public String getBulstat() {
//        return bulstat;
//    }
//
//    public void setBulstat(String bulstat) {
//        this.bulstat = bulstat;
//    }
//
//    @Basic
//    @Column(name = "BankName")
//    public String getBankName() {
//        return bankName;
//    }
//
//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }
//
//    @Basic
//    @Column(name = "BankCode")
//    public String getBankCode() {
//        return bankCode;
//    }
//
//    public void setBankCode(String bankCode) {
//        this.bankCode = bankCode;
//    }
//
//    @Basic
//    @Column(name = "BankAcct")
//    public String getBankAcct() {
//        return bankAcct;
//    }
//
//    public void setBankAcct(String bankAcct) {
//        this.bankAcct = bankAcct;
//    }
//
//    @Basic
//    @Column(name = "BankVATName")
//    public String getBankVatName() {
//        return bankVatName;
//    }
//
//    public void setBankVatName(String bankVatName) {
//        this.bankVatName = bankVatName;
//    }
//
//    @Basic
//    @Column(name = "BankVATCode")
//    public String getBankVatCode() {
//        return bankVatCode;
//    }
//
//    public void setBankVatCode(String bankVatCode) {
//        this.bankVatCode = bankVatCode;
//    }
//
//    @Basic
//    @Column(name = "BankVATAcct")
//    public String getBankVatAcct() {
//        return bankVatAcct;
//    }
//
//    public void setBankVatAcct(String bankVatAcct) {
//        this.bankVatAcct = bankVatAcct;
//    }
//
//    @Basic
//    @Column(name = "PriceGroup")
//    public Integer getPriceGroup() {
//        return priceGroup;
//    }
//
//    public void setPriceGroup(Integer priceGroup) {
//        this.priceGroup = priceGroup;
//    }
//
//    @Basic
//    @Column(name = "Discount")
//    public Double getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(Double discount) {
//        this.discount = discount;
//    }
//
//    @Basic
//    @Column(name = "Type")
//    public Short getType() {
//        return type;
//    }
//
//    public void setType(Short type) {
//        this.type = type;
//    }
//
//    @Basic
//    @Column(name = "IsVeryUsed")
//    public Short getIsVeryUsed() {
//        return isVeryUsed;
//    }
//
//    public void setIsVeryUsed(Short isVeryUsed) {
//        this.isVeryUsed = isVeryUsed;
//    }
//
//    @Basic
//    @Column(name = "UserID")
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    @Basic
//    @Column(name = "GroupID")
//    public Integer getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(Integer groupId) {
//        this.groupId = groupId;
//    }
//
//    @Basic
//    @Column(name = "UserRealTime")
//    public Timestamp getUserRealTime() {
//        return userRealTime;
//    }
//
//    public void setUserRealTime(Timestamp userRealTime) {
//        this.userRealTime = userRealTime;
//    }
//
//    @Basic
//    @Column(name = "Deleted")
//    public Integer getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Integer deleted) {
//        this.deleted = deleted;
//    }
//
//    @Basic
//    @Column(name = "CardNumber")
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    public void setCardNumber(String cardNumber) {
//        this.cardNumber = cardNumber;
//    }
//
////    @Id
////    @GeneratedValue(generator = "UUID")
////    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator" )
//    @Column(name = "rowguid", columnDefinition="uniqueidentifier")
//    public String getRowguid() {
//        return rowguid.toUpperCase();
//    }
//
//    public void setRowguid(String rowguid) {
//        this.rowguid = rowguid;
//    }
//
//    @Basic
//    @Column(name = "Company2")
//    public String getCompany2() {
//        return company2;
//    }
//
//    public void setCompany2(String company2) {
//        this.company2 = company2;
//    }
//
//    @Basic
//    @Column(name = "MOL2")
//    public String getMol2() {
//        return mol2;
//    }
//
//    public void setMol2(String mol2) {
//        this.mol2 = mol2;
//    }
//
//    @Basic
//    @Column(name = "Phone2")
//    public String getPhone2() {
//        return phone2;
//    }
//
//    public void setPhone2(String phone2) {
//        this.phone2 = phone2;
//    }
//
//    @Basic
//    @Column(name = "City2")
//    public String getCity2() {
//        return city2;
//    }
//
//    public void setCity2(String city2) {
//        this.city2 = city2;
//    }
//
//    @Basic
//    @Column(name = "Note1")
//    public String getNote1() {
//        return note1;
//    }
//
//    public void setNote1(String note1) {
//        this.note1 = note1;
//    }
//
//    @Basic
//    @Column(name = "Note2")
//    public String getNote2() {
//        return note2;
//    }
//
//    public void setNote2(String note2) {
//        this.note2 = note2;
//    }
//
//    @Basic
//    @Column(name = "PaymentDays")
//    public Integer getPaymentDays() {
//        return paymentDays;
//    }
//
//    public void setPaymentDays(Integer paymentDays) {
//        this.paymentDays = paymentDays;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PartnersEntity that = (PartnersEntity) o;
//        return id == that.id &&
//                Objects.equals(code, that.code) &&
//                Objects.equals(company, that.company) &&
//                Objects.equals(mol, that.mol) &&
//                Objects.equals(city, that.city) &&
//                Objects.equals(address, that.address) &&
//                Objects.equals(address2, that.address2) &&
//                Objects.equals(phone, that.phone) &&
//                Objects.equals(fax, that.fax) &&
//                Objects.equals(eMail, that.eMail) &&
//                Objects.equals(taxNo, that.taxNo) &&
//                Objects.equals(bulstat, that.bulstat) &&
//                Objects.equals(bankName, that.bankName) &&
//                Objects.equals(bankCode, that.bankCode) &&
//                Objects.equals(bankAcct, that.bankAcct) &&
//                Objects.equals(bankVatName, that.bankVatName) &&
//                Objects.equals(bankVatCode, that.bankVatCode) &&
//                Objects.equals(bankVatAcct, that.bankVatAcct) &&
//                Objects.equals(priceGroup, that.priceGroup) &&
//                Objects.equals(discount, that.discount) &&
//                Objects.equals(type, that.type) &&
//                Objects.equals(isVeryUsed, that.isVeryUsed) &&
//                Objects.equals(userId, that.userId) &&
//                Objects.equals(groupId, that.groupId) &&
//                Objects.equals(userRealTime, that.userRealTime) &&
//                Objects.equals(deleted, that.deleted) &&
//                Objects.equals(cardNumber, that.cardNumber) &&
//                Objects.equals(rowguid, that.rowguid) &&
//                Objects.equals(company2, that.company2) &&
//                Objects.equals(mol2, that.mol2) &&
//                Objects.equals(phone2, that.phone2) &&
//                Objects.equals(city2, that.city2) &&
//                Objects.equals(note1, that.note1) &&
//                Objects.equals(note2, that.note2) &&
//                Objects.equals(paymentDays, that.paymentDays);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, code, company, mol, city, address, address2, phone, fax, eMail, taxNo, bulstat, bankName, bankCode, bankAcct, bankVatName, bankVatCode, bankVatAcct, priceGroup, discount, type, isVeryUsed, userId, groupId, userRealTime, deleted, cardNumber, rowguid, company2, mol2, phone2, city2, note1, note2, paymentDays);
//    }
}
