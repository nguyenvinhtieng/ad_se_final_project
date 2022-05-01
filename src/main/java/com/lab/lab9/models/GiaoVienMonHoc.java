package com.lab.lab9.models;

public class GiaoVienMonHoc {
    private String usernameGv;
    private String tenGv;
    private int idLop;
    private int idMonHoc;

    public GiaoVienMonHoc(String usernameGv, String tenGv, int idLop, int idMonHoc) {
        this.usernameGv = usernameGv;
        this.tenGv = tenGv;
        this.idLop = idLop;
        this.idMonHoc = idMonHoc;
    }

    public String getUsernameGv() {
        return usernameGv;
    }

    public String getTenGv() {
        return tenGv;
    }

    public int getIdLop() {
        return idLop;
    }

    public int getIdMonHoc() {
        return idMonHoc;
    }

    @Override
    public String toString() {
        return "GiaoVienMonHoc{" +
                "usernameGv='" + usernameGv + '\'' +
                ", tenGv='" + tenGv + '\'' +
                ", idLop=" + idLop +
                ", idMonHoc=" + idMonHoc +
                '}';
    }
}
