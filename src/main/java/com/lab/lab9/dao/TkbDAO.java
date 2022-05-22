package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.ThongBao;
import com.lab.lab9.models.Tkb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TkbDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();

    public TkbDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<Tkb> layThoiKhoaBieu(String idLop, String idHocKy) throws SQLException{
        List<Tkb> tkb = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TKB WHERE IDLOP = "+idLop+" AND IDHOCKY = "+idHocKy;
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Tkb t = new Tkb(
                    resultSet.getInt("IDMONHOC"),
                    resultSet.getInt("IDLOP"),
                    resultSet.getInt("IDTIET"),
                    resultSet.getInt("IDTHU"),
                    resultSet.getInt("IDHOCKY")
            );
            tkb.add(t);
        }
        resultSet.close();
        stm.close();
        return tkb;
    }

    public List<Tkb> layThoiKhoaBieuGv(String idGv, String idHocKy) throws SQLException{
        List<Tkb> tkb = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TKB " +
                "LEFT JOIN LOP ON TKB.IDLOP = LOP.IDLOP " +
                "LEFT JOIN GV_MONHOC ON GV_MONHOC.IDLOP = LOP.IDLOP " +
                "LEFT JOIN MONHOC ON MONHOC.IDMONHOC = TKB.IDMONHOC " +
                "WHERE GV_MONHOC.IDGV = '"+idGv+"' AND TKB.IDHOCKY = "+Integer.parseInt(idHocKy);
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Tkb t = new Tkb(
                    resultSet.getInt("IDMONHOC"),
                    resultSet.getInt("IDLOP"),
                    resultSet.getInt("IDTIET"),
                    resultSet.getInt("IDTHU"),
                    resultSet.getInt("IDHOCKY"),
                    resultSet.getString("TENMONHOC"),
                    resultSet.getString("TENLOP")
            );
            boolean isHas = false;
            for(Tkb tkb1: tkb){
                if(tkb1.getIdTiet() == t.getIdTiet() && tkb1.getIdThu() == t.getIdThu()){
                    isHas = true;
                }
            }
            if(!isHas){
                tkb.add(t);
            }

        }
        resultSet.close();
        stm.close();
        return tkb;
    }

    public boolean kiemTraGVCoBan(String idLop, String idMon, String idthu, String idTiet, String idBuoi, String idHocKy) throws SQLException{
        List<Tkb> tkb = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM TKB WHERE " +
                "TKB.IDHOCKY = "+idHocKy+" AND TKB.IDTHU = "+idthu+" AND TKB.IDTIET = "+idTiet;
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            Tkb t = new Tkb(
                    resultSet.getInt("IDMONHOC"),
                    resultSet.getInt("IDLOP"),
                    resultSet.getInt("IDTIET"),
                    resultSet.getInt("IDTHU"),
                    resultSet.getInt("IDHOCKY")
            );
            tkb.add(t);
        }

        for (Tkb t: tkb) {
            
        }
        resultSet.close();
        stm.close();

        return true;
    }

    public boolean kiemTraTietCoTrong(String idLop, String idthu, String idTiet, String idHocKy) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM TKB WHERE IDLOP = " + idLop +
                " AND IDHOCKY = "+idHocKy +
                " AND IDTHU = " + idthu +
                " AND IDTIET = " + idTiet;
        resultSet = stm.executeQuery(sql);
        while(resultSet.next()) {
            return false;
        }
        resultSet.close();
        stm.close();
        return true;
    }

    public void taoTkb(String idMonhoc, String idLopHoc, String idTiet, String idThu, String idHocKy)  throws SQLException {
        String sql = "INSERT INTO TKB VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(idMonhoc));
        ps.setInt(2, Integer.parseInt(idLopHoc));
        ps.setInt(3, Integer.parseInt(idTiet));
        ps.setInt(4, Integer.parseInt(idThu));
        ps.setInt(5, Integer.parseInt(idHocKy));
        ps.execute();
        ps.close();
    }
    public void suaTkb(String idMonhoc, String idLopHoc, String idTiet, String idThu, String idHocKy) throws  SQLException {
        String sql = "UPDATE TKB SET IDMONHOC = ? WHERE IDLOP = ? AND IDTIET = ? AND IDTHU = ? AND IDHOCKY = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(idMonhoc));
        ps.setInt(2, Integer.parseInt(idLopHoc));
        ps.setInt(3, Integer.parseInt(idTiet));
        ps.setInt(4, Integer.parseInt(idThu));
        ps.setInt(5, Integer.parseInt(idHocKy));
        ps.executeUpdate();
        ps.close();
    }
    public void xoaTkbTrong() throws SQLException{
        String sql = "DELETE FROM TKB WHERE IDMONHOC = 0";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        ps.close();
    }

}
