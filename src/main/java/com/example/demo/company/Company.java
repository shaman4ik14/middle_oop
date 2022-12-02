package com.example.demo.company;

import jakarta.persistence.*;

@Entity
@Table
public class Company {
    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    private  Integer id;

    private String link;
    private String name;
    private String twitter;
    private String facebook;
    private String logo;
    private String icon;
    private Integer number_employees;
    private String address;

    public Company() {
    }

    public Company(
            String link,
                   String name,
                   String twitter,
                   String facebook,
                   String logo,
                   String icon,
                   Integer number_employees,
                   String address) {
        this.link = link;
        this.name = name;
        this.twitter = twitter;
        this.facebook = facebook;
        this.logo = logo;
        this.icon = icon;
        this.number_employees = number_employees;
        this.address = address;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getNumber_employees() {
        return number_employees;
    }

    public void setNumber_employees(Integer number_employees) {
        this.number_employees = number_employees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
