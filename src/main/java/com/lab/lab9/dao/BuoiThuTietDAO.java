package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.Buoi;
import com.lab.lab9.models.GiaoVien;
import com.lab.lab9.models.Thu;
import com.lab.lab9.models.Tiet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuoiThuTietDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public BuoiThuTietDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<Buoi> getAllBuoi() throws SQLException {
        List<Buoi> buoi = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM BUOI";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Buoi b = new Buoi(
                    resultSet.getInt("IDBUOI"),
                    resultSet.getString("TENBUOI")
            );
            buoi.add(b);
        }
        resultSet.close();
        stm.close();
        return buoi;
    }
    public List<Tiet> getAllTiet() throws SQLException {
        List<Tiet> tiet = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TIET";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Tiet t = new Tiet(
                    resultSet.getInt("IDTIET"),
                    resultSet.getString("TENTIET"),
                    resultSet.getInt("IDBUOI")
            );
            tiet.add(t);
        }
        resultSet.close();
        stm.close();
        return tiet;
    }
    public List<Thu> getAllThu() throws SQLException {
        List<Thu> thu = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM THU";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Thu t = new Thu(
                    resultSet.getInt("IDTHU"),
                    resultSet.getString("TENTHU")
            );
            thu.add(t);
        }
        resultSet.close();
        stm.close();
        return thu;
    }
}
