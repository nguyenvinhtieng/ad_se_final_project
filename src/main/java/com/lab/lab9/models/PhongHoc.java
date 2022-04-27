package com.lab.lab9.models;

public class PhongHoc {
    private int idPhongHoc;
    private String tenPhongHoc;
    private int trangThai;
    private String tenLopHoc;

    public PhongHoc() {
    }

    public PhongHoc(int idPhongHoc, String tenPhongHoc, int trangThai) {
        this.idPhongHoc = idPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.trangThai = trangThai;
    }

    public PhongHoc(int idPhongHoc, String tenPhongHoc, int trangThai, String tenLopHoc) {
        this.idPhongHoc = idPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.trangThai = trangThai;
        this.tenLopHoc = tenLopHoc;
    }

    public int getIdPhongHoc() {
        return idPhongHoc;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }
}
