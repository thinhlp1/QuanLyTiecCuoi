/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.DanhMuc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DanhMucDAO {

    private final String SELECT_DanhMuc = "SELECT * FROM DanhMuc";
    private final String SELECT_BY_ID = "SELECT * FROM DanhMuc WHERE MaDM=?";

    /*
   lấy danh sách tất cả danh mục
     */
    public List<DanhMuc> select() {
        return select(SELECT_DanhMuc);
    }

    /*
        tìm danh mục theo mã danh mục
     */
    public DanhMuc findById(String id) {
        List<DanhMuc> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DanhMuc model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private DanhMuc readFromResultSet(ResultSet rs) throws SQLException {
        //MaDM TenDM
        DanhMuc model = new DanhMuc();
        model.setMaDM(rs.getString("MaDM"));
        model.setTenDM(rs.getString("TenDM"));
        return model;
    }

}
