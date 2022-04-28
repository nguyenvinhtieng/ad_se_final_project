package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.TaiKhoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public TaiKhoanDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public TaiKhoan getUserData(String username) throws  SQLException{
        TaiKhoan taikhoan = new TaiKhoan();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TAIKHOAN WHERE TAIKHOAN = '"+username+"'";
        resultSet = stm.executeQuery(sql);
        String role = "";
        while(resultSet.next()) {
            taikhoan.setUsername(resultSet.getString("TAIKHOAN"));
            taikhoan.setPassword(resultSet.getString("MATKHAU"));
            taikhoan.setRole(resultSet.getString("ROLE"));
            taikhoan.setStatus(resultSet.getString("TRANGTHAI"));
        }
        return taikhoan;
    }
    public List<TaiKhoan> getAllAccount() throws SQLException {
        List<TaiKhoan> accounts = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TAIKHOAN WHERE ROLE <> 'ADMIN'";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            TaiKhoan tk = new TaiKhoan(
                resultSet.getString("TAIKHOAN"),
                resultSet.getString("MATKHAU"),
                resultSet.getString("ROLE"),
                resultSet.getString("TRANGTHAI")
            );
            accounts.add(tk);
        }

        resultSet.close();
        stm.close();
        return accounts;
    }

    public void resetPassword(String username, String password) throws  SQLException {
        String sql = "UPDATE TAIKHOAN SET MATKHAU = ? WHERE TAIKHOAN = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
    }

    public boolean kiemTraUsername(String userName) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM TAIKHOAN WHERE TAIKHOAN = '"+userName+"'";
        resultSet = stm.executeQuery(sql);
        return resultSet.getRow() == 0;
    }

    public void taoTaiKhoan(String taiKhoan, String matKhau, String role) throws  SQLException{
        String sql = "INSERT INTO TAIKHOAN VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String trangThai = "ACTIVE";
        ps.setString(1, taiKhoan);
        ps.setString(2, matKhau);
        ps.setString(3, role);
        ps.setString(4, trangThai);
        ps.execute();
        ps.close();
    }
}
