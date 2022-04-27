package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.LopHoc;
import com.lab.lab9.models.PhongHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public LopHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<LopHoc> getALlLopHoc() throws SQLException {
        List<LopHoc> lopHoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM LOP LEFT JOIN PHONGHOC ON LOP.IDPHONGHOC = PHONGHOC.IDPHONGHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getInt("NAMVAOTRUONG"),
                    resultSet.getString("TENPHONGHOC")
            );
            lopHoc.add(l);
        }
        return lopHoc;
    }
    public LopHoc layLopHoc(String idLop) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM LOP WHERE IDLOP = " + idLop;
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getInt("NAMVAOTRUONG")
            );
            System.out.println("== FUNC layLopHoc ==");
            System.out.println(l.toString());
            System.out.println("== == ==");
            return l;
        }
        System.out.println("Here");
        return new LopHoc();
    }
    public void taoLopHoc(String tenLopHoc, String khoi, String idPhongHoc, String namVaoTruong) throws SQLException{
        String sql = "INSERT INTO LOP VALUES(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenLopHoc);
        ps.setInt(2, Integer.parseInt(khoi));
        ps.setInt(3, Integer.parseInt(idPhongHoc));
        ps.setInt(4, Integer.parseInt(namVaoTruong));
        ps.execute();
        ps.close();
    }
    public void xoaLopHoc(String idLopHoc) throws SQLException{
        int idLop = Integer.parseInt(idLopHoc);
        String sql = "DELETE FROM LOP WHERE IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLop);
        ps.execute();
        ps.close();
    }
    public void suaLopHoc(String tenLopHoc, String khoi, String idPhongHoc, String namVaoTruong, String idLop) throws SQLException{
        int idPhong = Integer.parseInt(idPhongHoc);
        String sql = "UPDATE LOP SET TENLOP = ?, KHOI = ?, IDPHONGHOC = ?, NAMVAOTRUONG= ? WHERE IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenLopHoc);
        ps.setInt(2, Integer.parseInt(khoi));
        ps.setInt(3, Integer.parseInt(idPhongHoc));
        ps.setInt(4, Integer.parseInt(namVaoTruong));
        ps.setInt(5, Integer.parseInt(idLop));
        ps.executeUpdate();
        ps.close();
    }
}
