package com.lab.lab9.models;

public class HocSinh {
    private String idHocSinh;
    private String tenHocSinh;
    private String ngaySinh;
    private String gioiTinh;
    private String queQuan;
    private String danToc;
    private String hoKhau;
    private String sdtPhuHuynh;
    private String linkAvatar;
    private String trangThai;

    public HocSinh(String idHocSinh, String tenHocSinh, String ngaySinh, String gioiTinh, String queQuan, String danToc, String hoKhau, String sdtPhuHuynh, String linkAvatar, String trangThai) {
        this.idHocSinh = idHocSinh;
        this.tenHocSinh = tenHocSinh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.queQuan = queQuan;
        this.danToc = danToc;
        this.hoKhau = hoKhau;
        this.sdtPhuHuynh = sdtPhuHuynh;
        this.linkAvatar = linkAvatar;
        this.trangThai = trangThai;
    }

    public String getIdHocSinh() {
        return idHocSinh;
    }

    public void setIdHocSinh(String idHocSinh) {
        this.idHocSinh = idHocSinh;
    }

    public String getTenHocSinh() {
        return tenHocSinh;
    }

    public void setTenHocSinh(String tenHocSinh) {
        this.tenHocSinh = tenHocSinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getHoKhau() {
        return hoKhau;
    }

    public void setHoKhau(String hoKhau) {
        this.hoKhau = hoKhau;
    }

    public String getSdtPhuHuynh() {
        return sdtPhuHuynh;
    }

    public void setSdtPhuHuynh(String sdtPhuHuynh) {
        this.sdtPhuHuynh = sdtPhuHuynh;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }


}
