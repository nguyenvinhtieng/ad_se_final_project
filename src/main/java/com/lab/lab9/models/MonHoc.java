package com.lab.lab9.models;

public class MonHoc {
    private int idMonHoc;
    private String tenMonHoc;

    public MonHoc(int idMonHoc, String tenMonHoc) {
        this.idMonHoc = idMonHoc;
        this.tenMonHoc = tenMonHoc;
    }

    public int getIdMonHoc() {
        return idMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }
}
