package com.lab.lab9.models;

public class LopHoc {
    private int idLop;
    private String tenLop;
    private int khoi;
    private int idPhongHoc;
    private int idNamHoc;
    private String tenPhongHoc;
    private String tenNamHoc;

    public LopHoc() {
        this.idLop = 0;
        this.tenLop = "";
        this.khoi = 0;
        this.idPhongHoc = 0;
        this.idNamHoc = 0;
        this.tenPhongHoc = "";
    }


    public LopHoc(int idLop, String tenLop, int khoi, int idPhongHoc, int idNamHoc, String tenPhongHoc) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.khoi = khoi;
        this.idPhongHoc = idPhongHoc;
        this.idNamHoc = idNamHoc;
        this.tenPhongHoc = tenPhongHoc;
    }

    public LopHoc(int idLop, String tenLop, int khoi, int idPhongHoc, int idNamHoc, String tenPhongHoc, String tenNamHoc) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.khoi = khoi;
        this.idPhongHoc = idPhongHoc;
        this.idNamHoc = idNamHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.tenNamHoc = tenNamHoc;
    }

    public LopHoc(int idLop, String tenLop, int khoi, int idPhongHoc, int idNamHoc) {
        this.idLop = idLop;
        this.tenLop = tenLop;
        this.khoi = khoi;
        this.idPhongHoc = idPhongHoc;
        this.idNamHoc = idNamHoc;
    }

    public String getTenNamHoc() {
        return tenNamHoc;
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

    public int getIdNamHoc() {
        return idNamHoc;
    }

    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    @Override
    public String toString() {
        return "LopHoc{" +
                "idLop=" + idLop +
                ", tenLop='" + tenLop + '\'' +
                ", khoi=" + khoi +
                ", idPhongHoc=" + idPhongHoc +
                ", idNamHoc=" + idNamHoc +
                ", tenPhongHoc='" + tenPhongHoc + '\'' +
                ", tenNamHoc='" + tenNamHoc + '\'' +
                '}';
    }
}
