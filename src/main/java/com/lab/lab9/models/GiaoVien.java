package com.lab.lab9.models;

public class GiaoVien {
    private String idGiaoVien;
    private String name;
    private String identity;
    private String date;
    private String sex;
    private String originalplace;
    private String nation;
    private String household;
    private String phone;
    private String email;
    private String avatar;
    private String status;

    public GiaoVien(String idGiaoVien, String name, String identity, String date, String sex, String originalplace, String nation, String household, String phone, String email, String avatar, String status) {
        this.idGiaoVien = idGiaoVien;
        this.name = name;
        this.identity = identity;
        this.date = date;
        this.sex = sex;
        this.originalplace = originalplace;
        this.nation = nation;
        this.household = household;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
    }

    public GiaoVien(String name, String identity, String date, String sex, String originalplace, String nation, String household, String phone, String email, String avatar, String status) {
        this.name = name;
        this.identity = identity;
        this.date = date;
        this.sex = sex;
        this.originalplace = originalplace;
        this.nation = nation;
        this.household = household;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
    }

    public String getIdGiaoVien() {
        return idGiaoVien;
    }

    public String getName() {
        return name;
    }

    public String getIdentity() {
        return identity;
    }

    public String getDate() {
        return date;
    }

    public String getSex() {
        return sex;
    }

    public String getOriginalplace() {
        return originalplace;
    }

    public String getNation() {
        return nation;
    }

    public String getHousehold() {
        return household;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getStatus() {
        return status;
    }
}
