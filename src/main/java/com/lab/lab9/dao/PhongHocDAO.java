package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.HocKy;
import com.lab.lab9.models.PhongHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public PhongHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<PhongHoc> getALlPhongHoc() throws SQLException {
        List<PhongHoc> phongHoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM PHONGHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            PhongHoc p = new PhongHoc(
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getString("TENPHONGHOC")
            );
            phongHoc.add(p);
        }
        return phongHoc;
    }
    public void taoPhongHoc(String tenPhongHoc) throws SQLException{
        String sql = "INSERT INTO PHONGHOC VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenPhongHoc);
        ps.execute();
        ps.close();
    }
    public void xoaPhongHoc(String idPhongHoc) throws SQLException{
        int idPhong = Integer.parseInt(idPhongHoc);
        String sql = "DELETE FROM PHONGHOC WHERE IDPHONGHOC = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idPhong);
        ps.execute();
        ps.close();
    }
    public void suaPhongHoc(String idPhongHoc, String tenPhongHoc) throws SQLException{
        int idPhong = Integer.parseInt(idPhongHoc);
        String sql = "UPDATE PHONGHOC SET TENPHONGHOC = ? WHERE IDPHONGHOC = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenPhongHoc);
        ps.setInt(2, idPhong);
        ps.executeUpdate();
        ps.close();
    }

}
