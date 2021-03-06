package com.lab.lab9.dao;

import com.lab.lab9.credentials.Credentials;
import com.lab.lab9.models.HocKy;
import com.lab.lab9.models.HocSinh;
import com.lab.lab9.models.LopHoc;

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
        resultSet.close();
        stm.close();
        return hocsinh;
    }

    public HocSinh getHocSinhData(String username) throws SQLException {
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH WHERE IDHS = '"+username+"'";
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
            return hs;
        }
        resultSet.close();
        stm.close();
        return new HocSinh();
    }
    public boolean checkIdStudent(String maHs) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH WHERE IDHS = '"+maHs+"'";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            return false;
        }
        return true;
    }

    public void addStudent(String maHs, String ten, String ngaySinh, String gioiTinh, String queQuan, String danToc, String hoKhau, String sdtPhuHuynh, String linkAvatar) throws SQLException {
        String sql = "INSERT INTO HOCSINH VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String statusDefault = "ACTIVE";
        ps.setString(1, maHs);
        ps.setString(2, ten);
        ps.setString(3, ngaySinh);
        ps.setString(4, gioiTinh);
        ps.setString(5, queQuan);
        ps.setString(6, danToc);
        ps.setString(7, hoKhau);
        ps.setString(8, sdtPhuHuynh);
        ps.setString(9, linkAvatar);
        ps.setString(10, statusDefault);
        ps.execute();
        ps.close();
    }

    public void editStudent(String maHs, String ten, String ngaySinh, String gioiTinh, String queQuan, String danToc, String hoKhau, String sdtPhuHuynh, String linkAvatar, String trangThai) throws SQLException {
        String sql = "";
        if(linkAvatar.trim().equals("")){
            sql = "UPDATE HOCSINH SET TENHS = ?, NGAYSINH = ?, GIOITINH = ?, QUEQUAN= ?, DANTOC= ?, HOKHAU= ?, SDTPHUHUYNH= ?, TRANGTHAI= ? WHERE IDHS = ?";
        }else{
            sql = "UPDATE HOCSINH SET TENHS = ?, NGAYSINH = ?, GIOITINH = ?, QUEQUAN= ?, DANTOC= ?, HOKHAU= ?, SDTPHUHUYNH= ?, LINKAVATAR= ?, TRANGTHAI= ? WHERE IDHS = ?";
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ten);
        ps.setString(2, ngaySinh);
        ps.setString(3, gioiTinh);
        ps.setString(4, queQuan);
        ps.setString(5, danToc);
        ps.setString(6, hoKhau);
        ps.setString(7, sdtPhuHuynh);
        if(linkAvatar.trim().equals("")){
            ps.setString(8, trangThai);
            ps.setString(9, maHs);
        }else{
            ps.setString(8, linkAvatar);
            ps.setString(9, trangThai);
            ps.setString(10, maHs);
        }
        ps.executeUpdate();
        ps.close();
    }

    public List<HocSinh> layHocSinhCuaLop(int idLop) throws SQLException {
        List<HocSinh> hocsinh = new ArrayList<>();
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH " +
                "LEFT JOIN HOCSINH_LOPHOC ON HOCSINH.IDHS = HOCSINH_LOPHOC.IDHS " +
                "LEFT JOIN LOP ON LOP.IDLOP = HOCSINH_LOPHOC.IDLOP " +
                "WHERE HOCSINH_LOPHOC.IDLOP = " + idLop;
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
        resultSet.close();
        stm.close();
        return hocsinh;
    }

    public void themHocsinhVaoLop(String idHocSinh, int idLop) throws SQLException {
        String sql = "INSERT INTO HOCSINH_LOPHOC VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLop);
        ps.setString(2, idHocSinh);
        ps.execute();
        ps.close();
    }
    public void xoaHocSinhKhoiLop(int idLop, String idHocSinh) throws SQLException{
        String sql = "DELETE FROM HOCSINH_LOPHOC WHERE IDLOP = ? AND IDHS = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLop);
        ps.setString(2, idHocSinh);
        ps.execute();
        ps.close();
    }
    public boolean kiemTraHocSinhCoHocLopKhacKhong(String idHocSinh, int idNamHoc) throws SQLException{
        stm = conn.createStatement();
        String sql = "SELECT * FROM HOCSINH_LOPHOC " +
                "LEFT JOIN LOP ON HOCSINH_LOPHOC.IDLOP = LOP.IDLOP " +
                "WHERE LOP.IDNAMHOC = "+idNamHoc+" AND HOCSINH_LOPHOC.IDHS = '"+idHocSinh+"'";
        resultSet = stm.executeQuery(sql);
        while (resultSet.next()) {
            return false;
        }
        return true;
    }


}
