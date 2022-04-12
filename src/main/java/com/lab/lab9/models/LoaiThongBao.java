package com.lab.lab9.models;

public class LoaiThongBao {
    private int IdLoaiThongBao;
    private String Ten;

    public LoaiThongBao(int idLoaiThongBao, String ten) {
        IdLoaiThongBao = idLoaiThongBao;
        Ten = ten;
    }

    public int getIdLoaiThongBao() {
        return IdLoaiThongBao;
    }

    public void setIdLoaiThongBao(int idLoaiThongBao) {
        IdLoaiThongBao = idLoaiThongBao;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    @Override
    public String toString() {
        return "LoaiThongBao{" +
                "IdLoaiThongBao=" + IdLoaiThongBao +
                ", Ten='" + Ten + '\'' +
                '}';
    }
}
