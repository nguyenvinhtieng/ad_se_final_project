package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.NamHoc;
import com.lab.lab9.models.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NamHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public NamHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<NamHoc> getAllNamHoc() throws SQLException {
        List<NamHoc> namhoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM NAMHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            NamHoc nh = new NamHoc(
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENNAMHOC"),
                    resultSet.getString("NGAYBATDAU"),
                    resultSet.getString("NGAYKETTHUC"),
                    resultSet.getString("TRANGTHAI")
            );
            namhoc.add(nh);
        }
        return namhoc;
    }
    public String layTenNamHoc(String idNamHoc) throws  SQLException {
        stm = conn.createStatement();
        String sql = "SELECT * FROM NAMHOC WHERE IDNAMHOC = " + idNamHoc;
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            return resultSet.getString("TENNAMHOC");
        }
        return "";
    }

    public void taoNamHoc(String tenNamHoc, String ngayBatDau, String ngayKetThuc) throws SQLException{
        String sql = "INSERT INTO NAMHOC VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String trangThai = "NOT STARTED YET";
        ps.setString(1, tenNamHoc);
        ps.setString(2, ngayBatDau);
        ps.setString(3, ngayKetThuc);
        ps.setString(4, trangThai);
        ps.execute();
        ps.close();
    }

    public void xoaNamHoc(int id) throws SQLException{
        String sql = "DELETE FROM NAMHOC WHERE IDNAMHOC = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public void suaNamHoc(String id, String ten, String ngayBatDau, String ngayKetThuc, String trangThai) throws  SQLException {
        String sql = "UPDATE NAMHOC SET TENNAMHOC = ?, NGAYBATDAU = ?, NGAYKETTHUC = ?, TRANGTHAI = ? WHERE IDNAMHOC = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ten);
        ps.setString(2, ngayBatDau);
        ps.setString(3, ngayKetThuc);
        ps.setString(4, trangThai);
        ps.setInt(5, Integer.parseInt(id));
        ps.executeUpdate();
        ps.close();
    }

    public boolean checkNamHocActive(String id) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM NAMHOC WHERE TRANGTHAI = 'ACTIVE'";
        resultSet = stm.executeQuery(sql);
        int id_rs = -1;
        while (resultSet.next()) {
            id_rs = resultSet.getInt("IDNAMHOC");
        }
        if(Integer.parseInt(id) != id_rs && id_rs != -1)
            return false;

        return true;
    }
}
