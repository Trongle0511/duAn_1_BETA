/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.CaLamViec;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JdbcHelper;

/**
 *
 * @author nguye
 */
public class CaLamViecDao extends EduSysDAO<CaLamViec, String> {

    @Override
    public void insert(CaLamViec entity) {
        String INSERT_SQL = "INSERT INTO chiTietCaLamViec(maNV, maCLV, tenNV, khuVuc) VALUES(?,?,?,?)";
        JdbcHelper.update(INSERT_SQL,
                entity.getMaNV(),
                entity.getMaCLV(),
                entity.getTenNV(),
                entity.getKhuVuc());
    }

    @Override
    public void update(CaLamViec entity) {
        String UPDATE_SQL = "UPDATE chiTietCaLamViec set maCLV=? where tenNV=?,khuVuc?,maNV=? ";
        JdbcHelper.update(UPDATE_SQL, entity.getMaCLV(), entity.getTenNV(), entity.getKhuVuc(), entity.getMaNV());
    }
    public void updateS(CaLamViec entity) {
        String UPDATE_SQL = "UPDATE chiTietCaLamViec set maCLV=?, tenNV=?, khuVuc=?, maNV=? where tenNV=? and khuVuc=? and maNV=? ";
        JdbcHelper.update(UPDATE_SQL,
                entity.getMaCLV(),
                entity.getTenNV(),
                entity.getKhuVuc(),
                entity.getMaNV());
    }
//        public void updateS(CaLamViec entity) {
//        String UPDATE_SQL = "UPDATE chiTietCaLamViec set maCLV=? where tenNV=? and khuVuc=? and maNV=? ";
//        JdbcHelper.update(UPDATE_SQL,
//                entity.getMaCLV());
//    }

    @Override
    public void delete(String key) {
        String DELETE_SQL = "DELETE FROM chiTietCaLamViec where maNV=?";
        JdbcHelper.update(DELETE_SQL, key);
    }
    public void deleteMaNVandMaCLV(String maNV,String maCLV) {
        String DELETE_SQL = "DELETE FROM chiTietCaLamViec where maNV=? and maCLV=? ";
        JdbcHelper.update(DELETE_SQL, maNV,maCLV);
    }

    @Override
    public List<CaLamViec> selectAll() {
        String SELECT_ALL_SQL = "SELECT * FROM chiTietCaLamViec";
        return this.selectBySql(SELECT_ALL_SQL);
    }
//    public List<CaLamViec> selectAllsa() {
//        String SELECT_ALL_SQL = "SELECT * FROM chiTietCaLamViec where maNV=?";
//        return this.selectBySql(SELECT_ALL_SQL);
//    }
    public CaLamViec selectByMaNV(String key) {
        String SELECT_BY_ID = "SELECT COUNT(*) FROM chiTietCaLamViec WHERE maNV=?";
        List<CaLamViec> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public CaLamViec selectById(String key) {
        String SELECT_BY_ID = "SELECT * FROM chiTietCaLamViec where maNV=? ";
        List<CaLamViec> list = this.selectBySql(SELECT_BY_ID, key);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public CaLamViec selectByMaNVandMaCLV(String maNV,String maCLV) {
        String SELECT_BY_ID = "SELECT * FROM chiTietCaLamViec where maNV=? and maCLV=? ";
        List<CaLamViec> list = this.selectBySql(SELECT_BY_ID, maNV,maCLV);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    protected List<CaLamViec> selectBySql(String sql, Object... args) {
        List<CaLamViec> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                CaLamViec clv = new CaLamViec();
//                nv.setMaNV(rs.getString("MaNV"));
//                nv.setTenNV(rs.getString("TenNV"));
//                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
//                nv.setChucVu(rs.getBoolean("ChucVu"));
//                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
//                nv.setDiaChi(rs.getString("DiaChi"));
//                nv.setSoDT(rs.getString("SoDT"));
//                nv.setMatKhau(rs.getString("MatKhau"));
//                nv.setKhuVuc(rs.getString("KhuVuc"));
//                list.add(nv);
                clv.setMaNV(rs.getString("maNV"));
                clv.setMaCLV(rs.getString("maCLV"));
                clv.setTenNV(rs.getString("tenNV"));
                clv.setKhuVuc(rs.getString("khuVuc"));
                list.add(clv);

            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CaLamViec> selectByKeyword(String keyword) {
        String sql = "select * from chiTietCaLamViec where maNV like ? or tenNV like ? or khuVuc like ? or maCLV like ? ";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%");
    } 
    
    public List<CaLamViec> selectByKeywordCBO(String keyword) {
        String sql = "select * from chiTietCaLamViec where maCLV like ? ";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    

}
