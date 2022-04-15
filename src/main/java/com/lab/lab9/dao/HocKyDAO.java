package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.HocKy;
import com.lab.lab9.models.NamHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocKyDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public HocKyDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<HocKy> getAllHocKy() throws SQLException {
        List<HocKy> hocky = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCKY, NAMHOC WHERE HOCKY.IDNAMHOC = NAMHOC.IDNAMHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            HocKy hk = new HocKy(
                    resultSet.getInt("IDHOCKY"),
                    resultSet.getString("TENHOCKY"),
                    resultSet.getString("NGAYBATDAU"),
                    resultSet.getString("NGAYKETTHUC"),
                    resultSet.getString("TRANGTHAI"),
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENNAMHOC")
            );
            hocky.add(hk);
        }
        return hocky;
    }

    public void taoHocKy(String tenHocKy, String ngayBatDau, String ngayKetThuc, String idNamHoc) throws  SQLException{
        String sql = "INSERT INTO HOCKY(TENHOCKY,NGAYBATDAU,NGAYKETTHUC,TRANGTHAI,IDNAMHOC) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String trangThai = "NOT STARTED YET";
        ps.setString(1, tenHocKy);
        ps.setString(2, ngayBatDau);
        ps.setString(3, ngayKetThuc);
        ps.setString(4, trangThai);
        ps.setInt(5, Integer.parseInt(idNamHoc));
        ps.execute();
        ps.close();
    }

    public void xoaHocKy(int id) throws SQLException{
        String sql = "DELETE FROM HOCKY WHERE IDHOCKY = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }
    public void suaHocKy(String id, String ten, String ngayBatDau, String ngayKetThuc, String trangThai, String idNamHoc)
            throws  SQLException {
        String sql = "UPDATE HOCKY SET TENHOCKY = ?, NGAYBATDAU = ?, NGAYKETTHUC = ?, TRANGTHAI = ?, IDNAMHOC = ? WHERE IDHOCKY = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ten);
        ps.setString(2, ngayBatDau);
        ps.setString(3, ngayKetThuc);
        ps.setString(4, trangThai);
        ps.setInt(5, Integer.parseInt(idNamHoc.trim()));
        ps.setInt(6, Integer.parseInt(id.trim()));
        ps.executeUpdate();
        ps.close();
    }

    public boolean checkHocKyActive(String id) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCKY WHERE TRANGTHAI = 'ACTIVE'";
        resultSet = stm.executeQuery(sql);
        int id_rs = -1;
        while (resultSet.next()) {
            id_rs = resultSet.getInt("IDHOCKY");
        }
        if(Integer.parseInt(id.trim()) != id_rs && id_rs != -1)
            return false;

        return true;
    }
}
