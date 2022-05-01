package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.MonHoc;
import com.lab.lab9.models.NamHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public MonHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<MonHoc> getAllMonHoc() throws SQLException {
        List<MonHoc> monhoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM MONHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            MonHoc m = new MonHoc(
                    resultSet.getInt("IDMONHOC"),
                    resultSet.getString("TENMONHOC")
            );
            monhoc.add(m);
        }
        return monhoc;
    }
}
