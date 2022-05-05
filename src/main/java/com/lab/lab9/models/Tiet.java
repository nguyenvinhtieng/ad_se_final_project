package com.lab.lab9.models;

public class Tiet {
    private int idTiet;
    private String tenTiet;
    private int idBuoi;

    public Tiet(int idTiet, String tenTiet, int idBuoi) {
        this.idTiet = idTiet;
        this.tenTiet = tenTiet;
        this.idBuoi = idBuoi;
    }

    public int getIdTiet() {
        return idTiet;
    }

    public String getTenTiet() {
        return tenTiet;
    }

    public int getIdBuoi() {
        return idBuoi;
    }
}
