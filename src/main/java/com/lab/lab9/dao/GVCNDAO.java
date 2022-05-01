package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.GVCN;
import com.lab.lab9.models.GiaoVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GVCNDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public GVCNDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<GVCN> getDataGVCNPage() throws SQLException {
        List<GVCN> gvcn = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM LOP " +
                "LEFT JOIN GVCN ON LOP.IDLOP = GVCN.IDLOP " +
                "LEFT JOIN GIAOVIEN ON GIAOVIEN.IDGV = GVCN.USERNAMEGVCN";
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            GVCN gv = new GVCN(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getString("USERNAMEGVCN"),
                    resultSet.getString("TENGV")
            );
            gvcn.add(gv);
        }

        resultSet.close();
        stm.close();
        return gvcn;
    }

    public boolean kiemTraLopCoGVCNChua(String idLop) throws  SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM GVCN WHERE IDLOP = "+ Integer.parseInt(idLop);
        resultSet = stm.executeQuery(sql);
        if(resultSet.next()) {
            return true;
        }
        return false;
    }
    public void themGVCN(String idLop, String gvcnUsername) throws  SQLException{
        String sql = "INSERT INTO GVCN VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, gvcnUsername);
        ps.setInt(2, Integer.parseInt(idLop));
        ps.execute();
        ps.close();
    }
    public void doiGVCN(String idLop, String gvcnUsername) throws  SQLException {
        String sql = "UPDATE GVCN SET USERNAMEGVCN = ? WHERE IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, gvcnUsername);
        ps.setInt(2, Integer.parseInt(idLop));
        ps.executeUpdate();
        ps.close();
    }
}
