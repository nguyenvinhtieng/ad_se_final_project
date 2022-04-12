package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.ThongBao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThongBaoDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();

    public ThongBaoDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<ThongBao> getAllNotify() throws SQLException{
        List<ThongBao> notifies = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM THONGBAO, LOAITHONGBAO WHERE THONGBAO.IDLOAITHONGBAO = LOAITHONGBAO.IDLOAITHONGBAO";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            ThongBao tb = new ThongBao(
                    resultSet.getInt("IDTHONGBAO"),
                    resultSet.getString("TIEUDE"),
                    resultSet.getString("NOIDUNG"),
                    resultSet.getString("NGAYDANG"),
                    resultSet.getInt("IDLOAITHONGBAO"),
                    resultSet.getString("TEN"));
            notifies.add(tb);
        }

        resultSet.close();
        stm.close();
        return notifies;
    }

    public void createNotify(String tieuDe, String noiDung, String idLoaiThongBao, String ngayTao)  throws SQLException {
        String sql = "INSERT INTO THONGBAO VALUES(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tieuDe);
        ps.setString(2, noiDung);
        ps.setString(3, ngayTao);
        ps.setInt(4, Integer.parseInt(idLoaiThongBao));
        ps.execute();
        ps.close();
    }
}
