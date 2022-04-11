package com.lab.lab9.dao;

import com.lab.lab9.models.Laptop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class laptopDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    public laptopDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbName = "SCHOOL";
        String userName = "vinhtieng";
        String passWord = "vinhtieng";
        String url = "jdbc:sqlserver://localhost;databaseName=" + dbName;
        this.conn = DriverManager.getConnection(url, userName, passWord);
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
