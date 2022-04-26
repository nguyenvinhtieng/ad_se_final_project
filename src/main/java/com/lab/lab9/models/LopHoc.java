package com.lab.lab9.models;

public class LopHoc {
    private int idLop;
    private String tenLop;
    private int khoi;
    private int idPhongHoc;
    private int namVaoTruong;
    private String tenPhongHoc;

    public LopHoc(int idLop, String tenLop, int khoi, int idPhongHoc, int namVaoTruong, String tenPhongHoc) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.khoi = khoi;
        this.idPhongHoc = idPhongHoc;
        this.namVaoTruong = namVaoTruong;
        this.tenPhongHoc = tenPhongHoc;
    }

    public LopHoc(int idLop, String tenLop, int khoi, int idPhongHoc, int namVaoTruong) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.khoi = khoi;
        this.idPhongHoc = idPhongHoc;
        this.namVaoTruong = namVaoTruong;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    public int getIdLop() {
        return idLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public int getKhoi() {
        return khoi;
    }

    public int getIdPhongHoc() {
        return idPhongHoc;
    }

    public int getNamVaoTruong() {
        return namVaoTruong;
    }
}
