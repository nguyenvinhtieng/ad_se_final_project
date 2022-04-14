package com.lab.lab9.models;

public class NamHoc {
    private int idNamHoc;
    private String tenNamHoc;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String trangThai;

    public NamHoc() {
        this.idNamHoc = 0;
        this.tenNamHoc = "";
        this.ngayBatDau = "";
        this.ngayKetThuc = "";
        this.trangThai = "";
    }

    public NamHoc(int idNamHoc, String tenNamHoc, String ngayBatDau, String ngayKetThuc, String trangThai) {
        this.idNamHoc = idNamHoc;
        this.tenNamHoc = tenNamHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public int getIdNamHoc() {
        return idNamHoc;
    }

    public void setIdNamHoc(int idNamHoc) {
        this.idNamHoc = idNamHoc;
    }

    public String getTenNamHoc() {
        return tenNamHoc;
    }

    public void setTenNamHoc(String tenNamHoc) {
        this.tenNamHoc = tenNamHoc;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
