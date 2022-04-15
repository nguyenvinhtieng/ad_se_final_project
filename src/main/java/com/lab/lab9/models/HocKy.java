package com.lab.lab9.models;

public class HocKy {
    private int idHocKy;
    private String tenHocKy;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String trangThai;
    private int idNamHoc;
    private String tenNamHoc;

    public HocKy(int idHocKy, String tenHocKy, String ngayBatDau, String ngayKetThuc, String trangThai, int idNamHoc) {
        this.idHocKy = idHocKy;
        this.tenHocKy = tenHocKy;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.idNamHoc = idNamHoc;
    }

    public HocKy(int idHocKy, String tenHocKy, String ngayBatDau, String ngayKetThuc, String trangThai, int idNamHoc, String tenNamHoc) {
        this.idHocKy = idHocKy;
        this.tenHocKy = tenHocKy;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.idNamHoc = idNamHoc;
        this.tenNamHoc = tenNamHoc;
    }

    public int getIdHocKy() {
        return idHocKy;
    }

    public void setIdHocKy(int idHocKy) {
        this.idHocKy = idHocKy;
    }

    public String getTenHocKy() {
        return tenHocKy;
    }

    public void setTenHocKy(String tenHocKy) {
        this.tenHocKy = tenHocKy;
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
}
