package com.lab.lab9.models;

public class Tkb {
    private int idMonHoc;
    private int idLop;
    private int idTiet;
    private int idThu;
    private int idHocKy;
    private String tenMon;
    private String tenLop;

    public Tkb(int idMonHoc, int idLop, int idTiet, int idThu, int idHocKy) {
        this.idMonHoc = idMonHoc;
        this.idLop = idLop;
        this.idTiet = idTiet;
        this.idThu = idThu;
        this.idHocKy = idHocKy;
    }

    public Tkb(int idMonHoc, int idLop, int idTiet, int idThu, int idHocKy, String tenMon, String tenLop) {
        this.idMonHoc = idMonHoc;
        this.idLop = idLop;
        this.idTiet = idTiet;
        this.idThu = idThu;
        this.idHocKy = idHocKy;
        this.tenMon = tenMon;
        this.tenLop = tenLop;
    }

    public String getTenMon() {
        return tenMon;
    }

    public String getTenLop() {
        return tenLop;
    }

    public int getIdMonHoc() {
        return idMonHoc;
    }

    public int getIdLop() {
        return idLop;
    }

    public int getIdTiet() {
        return idTiet;
    }

    public int getIdThu() {
        return idThu;
    }

    public int getIdHocKy() {
        return idHocKy;
    }

    @Override
    public String toString() {
        return "Tkb{" +
                "idMonHoc=" + idMonHoc +
                ", idLop=" + idLop +
                ", idTiet=" + idTiet +
                ", idThu=" + idThu +
                ", idHocKy=" + idHocKy +
                ", tenMon='" + tenMon + '\'' +
                ", tenLop='" + tenLop + '\'' +
                '}';
    }
}
