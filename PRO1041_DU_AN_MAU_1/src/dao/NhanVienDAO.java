/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author Admin
 */
public class NhanVienDAO  {


    public void insert(NhanVien entity) {
        String INSERT_SQL = "insert into NhanVien(MaNV, tenNV, gioiTinh, chucVu, ngayVaoLam, diaChi, soDT, matKhau, khuVuc) values (?,?,?,?,?,?,?,?,?)";
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.isGioiTinh(), entity.isChucvu(),entity.getNgayVaoLam(),entity.getDiaChi(),entity.getSoDT(),entity.getMatKhau(),entity.getKhuVuc());
    }


    public void update(NhanVien entity) {
        String up = "update NhanVien SET tenNV=?, gioiTinh=?, chucVu=?"
                +   ", ngayVaoLam=?, diachi=?, soDT=?, matKhau=?, khuVuc=? WHERE MaNV=?";
        JdbcHelper.update(up, entity.getTenNV(), entity.isGioiTinh()
                , entity.isChucvu(),entity.getNgayVaoLam(),entity.getDiaChi()
                ,entity.getSoDT(),entity.getMatKhau(),entity.getKhuVuc());
    }


    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.update(DELETE_SQL, key);
    }


    public List<NhanVien> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
        return this.selectBySql(SELECT_ALL_SQL);
    }


    public NhanVien selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID, key);
        return list.size() > 0 ? list.get(0) : null;
    }


    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setChucvu(rs.getBoolean("ChucVu"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSoDT(rs.getString("SoDT"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setKhuVuc(rs.getString("KhuVuc"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public List<NhanVien> selectByKeyword(String keyword) {
        String sql = "select * from NhanVien where maNV like ? or tenNV like ? or khuVuc like ? or soDT like ? ";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    } 

}
