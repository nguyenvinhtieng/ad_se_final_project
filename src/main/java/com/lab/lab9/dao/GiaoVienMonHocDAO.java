package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.GiaoVienMonHoc;
import com.lab.lab9.models.NamHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiaoVienMonHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public GiaoVienMonHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<GiaoVienMonHoc> layGiaoVienMonHocTheoLop(String idLop) throws SQLException {
        List<GiaoVienMonHoc> giaovien = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM GV_MONHOC LEFT JOIN GIAOVIEN ON GV_MONHOC.IDGV = GIAOVIEN.IDGV WHERE GV_MONHOC.IDLOP = " + idLop;
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            GiaoVienMonHoc g = new GiaoVienMonHoc(
                    resultSet.getString("IDGV"),
                    resultSet.getString("TENGV"),
                    resultSet.getInt("IDLOP"),
                    resultSet.getInt("IDMONHOC")
            );
            giaovien.add(g);
        }

        return giaovien;
    }

    public boolean coGiaoVienMonHocChua(String idLop, String idMon) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM GV_MONHOC WHERE IDMONHOC = " + idMon + " AND IDLOP = " + idLop;
        resultSet = stm.executeQuery(sql);
        if(resultSet.next()){
            return true;
        }
        return false;
    }

    public void themGiaoVienMonHoc(String idLop, String idMon, String usernameGv) throws  SQLException{
        String sql = "INSERT INTO GV_MONHOC(IDGV,IDMONHOC,IDLOP) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usernameGv);
        ps.setInt(2, Integer.parseInt(idMon));
        ps.setInt(3, Integer.parseInt(idLop));
        ps.execute();
        ps.close();
    }
    public void suaGiaoVienMonHoc(String idLop, String idMon, String usernameGv) throws  SQLException{
        String sql = "UPDATE GV_MONHOC SET IDGV = ? WHERE IDMONHOC = ? AND IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usernameGv);
        ps.setInt(2, Integer.parseInt(idMon));
        ps.setInt(3, Integer.parseInt(idLop));
        ps.execute();
        ps.close();
    }
}
