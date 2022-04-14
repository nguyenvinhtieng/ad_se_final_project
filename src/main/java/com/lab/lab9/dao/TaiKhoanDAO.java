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

    public TaiKhoan getUserData(String username, String password) throws  SQLException{
        TaiKhoan taikhoan = new TaiKhoan();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TAIKHOAN WHERE TAIKHOAN = '"+username+"' and MATKHAU='" + password + "'";
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
//    public List<Laptop> getLaptops() throws SQLException {
//        List<Laptop> laptops = new ArrayList<>();
//        stm = conn.createStatement();
//        resultSet = stm.executeQuery("select * from laptop");
//        while(resultSet.next()) {
//            Laptop lt = new Laptop(
//                    resultSet.getInt(1),
//                    resultSet.getString(2),
//                    resultSet.getInt(3),
//                    resultSet.getInt(4),
//                    resultSet.getString(5));
//            laptops.add(lt);
//        }
//
//        resultSet.close();
//        stm.close();
//        return laptops;
//    }
//    public void addNewLaptop(String name, int ram, int price, String image)  throws SQLException {
//        String sql = "INSERT INTO laptop VALUES(?, ?, ?, ?)";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, name);
//        ps.setInt(2, ram);
//        ps.setInt(3, price);
//        ps.setString(4, image);
//        ps.execute();
//        ps.close();
//    }
//    public void deleteLaptop(int id) throws SQLException{
//        PreparedStatement ps = conn.prepareStatement("DELETE FROM laptop WHERE id = ?");
//        ps.setInt(1, id);
//        ps.execute();
//        ps.close();
//    }
//    public void editLaptop(int id, String name, int ram, int price) throws  SQLException {
//        String sql = "UPDATE laptop SET name = ?, ram = ?, price = ? WHERE id = ?";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, name);
//        ps.setInt(2, ram);
//        ps.setInt(3, price);
//        ps.setInt(4, id);
//        ps.executeUpdate();
//        ps.close();
//    }

}
