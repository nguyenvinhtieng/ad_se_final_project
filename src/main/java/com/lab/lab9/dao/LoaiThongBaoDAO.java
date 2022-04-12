package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.LoaiThongBao;
import com.lab.lab9.models.ThongBao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoaiThongBaoDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();

    public LoaiThongBaoDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<LoaiThongBao> getAllType() throws SQLException{
        List<LoaiThongBao> types = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM LOAITHONGBAO";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            LoaiThongBao t = new LoaiThongBao(
                    resultSet.getInt("IDLOAITHONGBAO"),
                    resultSet.getString("TEN")
            );
            types.add(t);
        }

        resultSet.close();
        stm.close();
        return types;
    }
}
