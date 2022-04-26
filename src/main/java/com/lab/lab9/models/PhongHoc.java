package com.lab.lab9.models;

public class PhongHoc {
    private int idPhongHoc;
    private String tenPhongHoc;
    private String trangThai;

    public PhongHoc(int idPhongHoc, String tenPhongHoc) {
        this.idPhongHoc = idPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
    }

    public PhongHoc(int idPhongHoc, String tenPhongHoc, String trangThai) {
        this.idPhongHoc = idPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.trangThai = trangThai;
    }

    public int getIdPhongHoc() {
        return idPhongHoc;
    }

    public void setIdPhongHoc(int idPhongHoc) {
        this.idPhongHoc = idPhongHoc;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
