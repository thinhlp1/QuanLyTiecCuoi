/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;

import com.happywedding.model.DanhMucCon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DanhMucConDAO {
    
    
    private final String SELECT_DanhMucCon = "SELECT * FROM DanhMucCon";
    private final String SELECT_BY_ID = "SELECT * FROM DanhMucCon WHERE MaDMC=?";

    /*
   lấy danh sách tất cả danh mục
     */
    public List<DanhMucCon> select() {
        return select(SELECT_DanhMucCon);
    }

    /*
        tìm danh mục theo mã danh mục
     */
    public DanhMucCon findById(String id) {
        List<DanhMucCon> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<DanhMucCon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DanhMucCon model = readFromResultSet(rs);
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

    private DanhMucCon readFromResultSet(ResultSet rs) throws SQLException {
       
        DanhMucCon model = new DanhMucCon();
        model.setMaDMC(rs.getString("MaDMC"));
        model.setMaDM(rs.getString("MaDM"));
        model.setTenDM(rs.getString("TenDM"));
        return model;
    }
    
}
