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
    private int idLop;
    private String tenLop;

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

    public String getTenHocSinh() {
        return tenHocSinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public String getHoKhau() {
        return hoKhau;
    }

    public String getSdtPhuHuynh() {
        return sdtPhuHuynh;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public String getTrangThai() {
        return trangThai;
    }
}
