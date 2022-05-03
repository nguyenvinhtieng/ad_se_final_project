package com.lab.lab9.models;

public class ThongBao {
    private int IdThongBao;
    private String TieuDe;
    private String NoiDung;
    private String NgayDang;
    private int IdLoaiThongBao;
    private String TenLoaiThongBao;

    public ThongBao() {
    }

    public ThongBao(int idThongBao, String tieuDe, String noiDung, String ngayDang, int idLoaiThongBao) {
        IdThongBao = idThongBao;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        NgayDang = ngayDang;
        IdLoaiThongBao = idLoaiThongBao;
        TenLoaiThongBao = "";
    }

    public ThongBao(int idThongBao, String tieuDe, String noiDung, String ngayDang, int idLoaiThongBao, String tenLoaiThongBao) {
        IdThongBao = idThongBao;
        TieuDe = tieuDe;
        NoiDung = noiDung;
        NgayDang = ngayDang;
        IdLoaiThongBao = idLoaiThongBao;
        TenLoaiThongBao = tenLoaiThongBao;
    }

    public String getTenLoaiThongBao() {
        return TenLoaiThongBao;
    }

    public void setTenLoaiThongBao(String tenLoaiThongBao) {
        TenLoaiThongBao = tenLoaiThongBao;
    }

    public int getIdThongBao() {
        return IdThongBao;
    }


    public void setIdThongBao(int idThongBao) {
        IdThongBao = idThongBao;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String ngayDang) {
        NgayDang = ngayDang;
    }

    public int getIdLoaiThongBao() {
        return IdLoaiThongBao;
    }
    public String getStringIdLoaiThongBao() {
        return String.valueOf(IdLoaiThongBao);
    }

    public void setIdLoaiThongBao(int idLoaiThongBao) {
        IdLoaiThongBao = idLoaiThongBao;
    }

    @Override
    public String toString() {
        return "ThongBao{" +
                "IdThongBao=" + IdThongBao +
                ", TieuDe='" + TieuDe + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", NgayDang='" + NgayDang + '\'' +
                ", IdLoaiThongBao=" + IdLoaiThongBao +
                ", TenLoaiThongBao='" + TenLoaiThongBao + '\'' +
                '}';
    }
}
