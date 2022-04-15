package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.HocKy;
import com.lab.lab9.models.NamHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HocKyDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public HocKyDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<HocKy> getAllHocKy() throws SQLException {
        List<HocKy> hocky = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCKY, NAMHOC WHERE HOCKY.IDNAMHOC = NAMHOC.IDNAMHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            HocKy hk = new HocKy(
                    resultSet.getInt("IDHOCKY"),
                    resultSet.getString("TENHOCKY"),
                    resultSet.getString("NGAYBATDAU"),
                    resultSet.getString("NGAYKETTHUC"),
                    resultSet.getString("TRANGTHAI"),
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENNAMHOC")
            );
            hocky.add(hk);
        }
        return hocky;
    }
}
