package com.lab.lab9.models;

public class Tkb {
    private int idMonHoc;
    private int idLop;
    private int idTiet;
    private int idThu;
    private int idHocKy;

    public Tkb(int idMonHoc, int idLop, int idTiet, int idThu, int idHocKy) {
        this.idMonHoc = idMonHoc;
        this.idLop = idLop;
        this.idTiet = idTiet;
        this.idThu = idThu;
        this.idHocKy = idHocKy;
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
}
