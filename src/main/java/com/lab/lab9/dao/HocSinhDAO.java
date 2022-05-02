package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.HocKy;
import com.lab.lab9.models.HocSinh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocSinhDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public HocSinhDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<HocSinh> getAllStudent() throws SQLException {
        List<HocSinh> hocsinh = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH, LOP WHERE HOCSINH.IDLOPHOC = LOP.IDLOP";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            HocSinh hs = new HocSinh(
                    resultSet.getString("MAHS"),
                    resultSet.getString("TENHS"),
                    resultSet.getString("NGAYSINH"),
                    resultSet.getString("GIOITINH"),
                    resultSet.getString("QUEQUAN"),
                    resultSet.getString("DANTOC"),
                    resultSet.getString("HOKHAU"),
                    resultSet.getString("SDTPHUHUYNH"),
                    resultSet.getString("LINKAVATAR"),
                    resultSet.getString("TRANGTHAI"),
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP")

            );
            hocsinh.add(hs);
        }
        return hocsinh;
    }
    public boolean checkIdStudent(String maHs) throws SQLException{
        String sql = "SELECT * FROM HOCSINH WHERE MAHS = '" + maHs + "'";
        resultSet = stm.executeQuery(sql);
        int numberRow =  resultSet.getRow();
        System.out.print("So Dong ----------- " + String.valueOf(numberRow));
        return numberRow == 0;
    }

    public void addStudent(String maHs, String ten, String ngaySinh, String gioiTinh, String queQuan, String danToc, String hoKhau, String sdtPhuHuynh, String linkAvatar, String idLop) throws SQLException {
        String sql = "INSERT INTO HOCSINH VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String statusDefault = "ACTIVE";
        ps.setString(1, maHs);
        ps.setString(2, ten);
        ps.setString(3, ngaySinh);
        ps.setString(4, gioiTinh);
        ps.setString(5, queQuan);
        ps.setString(6, danToc);
        ps.setString(7, hoKhau);
        ps.setString(8, sdtPhuHuynh);
        ps.setString(9, linkAvatar);
        ps.setString(10, statusDefault);
        ps.setInt(11, Integer.parseInt(idLop));
        ps.execute();
        ps.close();
    }

    public void editStudent(String maHs, String ten, String ngaySinh, String gioiTinh, String queQuan, String danToc, String hoKhau, String sdtPhuHuynh, String linkAvatar, String idLop, String trangThai) throws SQLException {
        String sql = "UPDATE HOCSINH SET TENHS = ?, NGAYSINH = ?, GIOITINH = ?, QUEQUAN= ?, DANTOC= ?, HOKHAU= ?, SDTPHUHUYNH= ?, LINKAVATAR= ?, TRANGTHAI= ?, IDLOPHOC= ? WHERE MAHS = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ten);
        ps.setString(2, ngaySinh);
        ps.setString(3, gioiTinh);
        ps.setString(4, queQuan);
        ps.setString(5, danToc);
        ps.setString(6, hoKhau);
        ps.setString(7, sdtPhuHuynh);
        ps.setString(8, linkAvatar);
        ps.setString(9, trangThai);
        ps.setInt(10, Integer.parseInt(idLop));
        ps.setString(11, maHs);
        ps.executeUpdate();
        ps.close();
    }
}