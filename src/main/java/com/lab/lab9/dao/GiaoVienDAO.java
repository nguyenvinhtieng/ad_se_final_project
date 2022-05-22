package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.GiaoVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiaoVienDAO {

    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public GiaoVienDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<GiaoVien> getAllTeacher() throws SQLException {
        List<GiaoVien> teachers = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM GIAOVIEN ";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            GiaoVien gv = new GiaoVien(
                    resultSet.getString("IDGV"),
                    resultSet.getString("TENGV"),
                    resultSet.getString("CMND"),
                    resultSet.getString("NGAYSINH"),
                    resultSet.getString("GIOITINH"),
                    resultSet.getString("QUEQUAN"),
                    resultSet.getString("DANTOC"),
                    resultSet.getString("HOKHAU"),
                    resultSet.getString("SDT"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("LINKAVATAR"),
                    resultSet.getString("TRANGTHAI")
            );
            teachers.add(gv);
        }

        resultSet.close();
        stm.close();
        return teachers;
    }

    public List<GiaoVien> getAllTeacherAvtice() throws SQLException {
        List<GiaoVien> teachers = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM GIAOVIEN WHERE TRANGTHAI = 'ACTIVE'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            GiaoVien gv = new GiaoVien(
                    resultSet.getString("IDGV"),
                    resultSet.getString("TENGV"),
                    resultSet.getString("CMND"),
                    resultSet.getString("NGAYSINH"),
                    resultSet.getString("GIOITINH"),
                    resultSet.getString("QUEQUAN"),
                    resultSet.getString("DANTOC"),
                    resultSet.getString("HOKHAU"),
                    resultSet.getString("SDT"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("LINKAVATAR"),
                    resultSet.getString("TRANGTHAI")
            );
            teachers.add(gv);
        }

        resultSet.close();
        stm.close();
        return teachers;
    }

    public void themGiaoVien(String id, String name, String identity, String date, String sex, String originalplace, String nation, String household, String phone, String email, String avatar) throws SQLException{
        String sql = "INSERT INTO GIAOVIEN VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String status = "ACTIVE";
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, identity);
        ps.setString(4, date);
        ps.setString(5, sex);
        ps.setString(6, originalplace);
        ps.setString(7, nation);
        ps.setString(8, household);
        ps.setString(9, phone);
        ps.setString(10, email);
        ps.setString(11, avatar);
        ps.setString(12, status);
        ps.execute();
        ps.close();
    }

    public void suaGiaoVien(String name, String identity, String date, String sex, String originalplace, String nation, String household, String phone, String email, String avatar, String id) throws  SQLException {
        String sql = "";
        if(avatar.trim().equals("")){
            sql = "UPDATE GIAOVIEN SET TENGV = ?, CMND = ?, NGAYSINH = ?, GIOITINH = ?, QUEQUAN = ?, DANTOC = ?, HOKHAU = ?, SDT = ?, EMAIL = ? WHERE IDGV = ?";
        }else{
            sql = "UPDATE GIAOVIEN SET TENGV = ?, CMND = ?, NGAYSINH = ?, GIOITINH = ?, QUEQUAN = ?, DANTOC = ?, HOKHAU = ?, SDT = ?, EMAIL = ?, LINKAVATAR = ? WHERE IDGV = ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, identity);
        ps.setString(3, date);
        ps.setString(4, sex);
        ps.setString(5, originalplace);
        ps.setString(6, nation);
        ps.setString(7, household);
        ps.setString(8, phone);
        ps.setString(9, email);
        if(avatar.trim().equals("")){
            ps.setString(10,id);
        }else{
            ps.setString(10, avatar);
            ps.setString(11, id);
        }
        ps.executeUpdate();
        ps.close();
    }

    public GiaoVien layThongTinGiaoVien(String idGiaoVien) throws SQLException {
        stm = conn.createStatement();
        String sql = "SELECT * FROM GIAOVIEN WHERE IDGV = '"+idGiaoVien+"'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            GiaoVien gv = new GiaoVien(
                    resultSet.getString("IDGV"),
                    resultSet.getString("TENGV"),
                    resultSet.getString("CMND"),
                    resultSet.getString("NGAYSINH"),
                    resultSet.getString("GIOITINH"),
                    resultSet.getString("QUEQUAN"),
                    resultSet.getString("DANTOC"),
                    resultSet.getString("HOKHAU"),
                    resultSet.getString("SDT"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("LINKAVATAR"),
                    resultSet.getString("TRANGTHAI")
            );
            return gv;
        }

        resultSet.close();
        stm.close();
        return null;
    }
}
