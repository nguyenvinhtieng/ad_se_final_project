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
        String sql = "SELECT * FROM HOCSINH";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            HocSinh hs = new HocSinh(
                    resultSet.getString("IDHS"),
                    resultSet.getString("TENHS"),
                    resultSet.getString("NGAYSINH"),
                    resultSet.getString("GIOITINH"),
                    resultSet.getString("QUEQUAN"),
                    resultSet.getString("DANTOC"),
                    resultSet.getString("HOKHAU"),
                    resultSet.getString("SDTPHUHUYNH"),
                    resultSet.getString("LINKAVATAR"),
                    resultSet.getString("TRANGTHAI")
            );
            hocsinh.add(hs);
        }
        return hocsinh;
    }
}
