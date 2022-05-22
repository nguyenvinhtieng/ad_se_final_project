package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.LopHoc;
import com.lab.lab9.models.PhongHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopHocDAO {
    private Connection conn;
    private Statement stm;
    private ResultSet resultSet;
    private Credentials credentials = new Credentials();
    public LopHocDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = credentials.getUserName();
        String passWord = credentials.getPassWord();
        String url = credentials.getUrl();
        this.conn = DriverManager.getConnection(url, userName, passWord);
    }

    public List<LopHoc> getALlLopHoc() throws SQLException {
        List<LopHoc> lopHoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT *, LOP.IDPHONGHOC AS IDPHONGHOCLOP FROM " +
                "LOP LEFT JOIN PHONGHOC ON LOP.IDPHONGHOC = PHONGHOC.IDPHONGHOC " +
                "LEFT JOIN NAMHOC ON LOP.IDNAMHOC = NAMHOC.IDNAMHOC";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOCLOP"),
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENPHONGHOC"),
                    resultSet.getString("TENNAMHOC")
            );
            lopHoc.add(l);
        }
        return lopHoc;
    }
    public LopHoc layLopHoc(String idLop) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM LOP WHERE IDLOP = " + idLop;
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getInt("IDNAMHOC")
            );
            return l;
        }
        return new LopHoc();
    }
    public void taoLopHoc(String tenLopHoc, String khoi, String idPhongHoc, String idNamHoc) throws SQLException{
        String sql = "INSERT INTO LOP VALUES(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenLopHoc);
        ps.setInt(2, Integer.parseInt(khoi));
        ps.setInt(3, Integer.parseInt(idPhongHoc));
        ps.setInt(4, Integer.parseInt(idNamHoc));
        ps.execute();
        ps.close();
    }
    public void xoaLopHoc(String idLopHoc) throws SQLException{
        int idLop = Integer.parseInt(idLopHoc);
        String sql = "DELETE FROM LOP WHERE IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLop);
        ps.execute();
        ps.close();
    }
    public void suaLopHoc(String tenLopHoc, String khoi, String idPhongHoc, String idNamHoc, String idLop) throws SQLException{
        int idPhong = Integer.parseInt(idPhongHoc);
        String sql = "UPDATE LOP SET TENLOP = ?, KHOI = ?, IDPHONGHOC = ?, IDNAMHOC= ? WHERE IDLOP = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, tenLopHoc);
        ps.setInt(2, Integer.parseInt(khoi));
        ps.setInt(3, Integer.parseInt(idPhongHoc));
        ps.setInt(4, Integer.parseInt(idNamHoc));
        ps.setInt(5, Integer.parseInt(idLop));
        ps.executeUpdate();
        ps.close();
    }

    public List<LopHoc> layLopChuNhiem(String idGiaoVien) throws SQLException{
        List<LopHoc> lopHoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM GVCN " +
                "LEFT JOIN LOP ON GVCN.IDLOP = LOP.IDLOP " +
                "LEFT JOIN NAMHOC ON NAMHOC.IDNAMHOC = LOP.IDNAMHOC " +
                "WHERE USERNAMEGVCN = '"+idGiaoVien+"'";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENLOP"),
                    resultSet.getString("TENNAMHOC")
            );
            lopHoc.add(l);
        }
        return lopHoc;
    }

    public List<LopHoc> layLopDayMon(String idGiaoVien) throws SQLException{
        List<LopHoc> lopHoc = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM GV_MONHOC " +
                "LEFT JOIN LOP ON GV_MONHOC.IDLOP = LOP.IDLOP " +
                "LEFT JOIN NAMHOC ON LOP.IDNAMHOC = NAMHOC.IDNAMHOC " +
                "LEFT JOIN MONHOC ON GV_MONHOC.IDMONHOC = MONHOC.IDMONHOC " +
                "WHERE GV_MONHOC.IDGV = '"+idGiaoVien+"'";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("IDNAMHOC"),
                    resultSet.getString("TENNAMHOC"),
                    resultSet.getString("TENMONHOC"),
                    resultSet.getInt("IDMONHOC")
            );
            lopHoc.add(l);
        }
        return lopHoc;
    }
    public LopHoc layLopHocDuaVaoIDHocSinh(String idHocSinh, int idNamHoc) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH_LOPHOC " +
                "LEFT JOIN LOP ON LOP.IDLOP = HOCSINH_LOPHOC.IDLOP " +
                "WHERE LOP.IDNAMHOC = "+idNamHoc+" AND HOCSINH_LOPHOC.IDHS ='"+idHocSinh+"'";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            LopHoc l = new LopHoc(
                    resultSet.getInt("IDLOP"),
                    resultSet.getString("TENLOP"),
                    resultSet.getInt("KHOI"),
                    resultSet.getInt("IDPHONGHOC"),
                    resultSet.getInt("IDNAMHOC")
            );
            return l;
        }
        return new LopHoc();
    }
}
