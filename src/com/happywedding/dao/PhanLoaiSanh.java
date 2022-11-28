/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.happywedding.helper.JDBCHelper;

/**
 *
 * @author ADMIN
 */
public class PhanLoaiSanh {
    private final String SELECT_PhanLoaiSanh = "SELECT MaPL,TenPL FROM dbo.PhanLoaiSanh";
    private final String SELECT_BY_ID = "SELECT * FROM PhanLoaiSanh WHERE  MaPL=?";
    
    
       public List<com.happywedding.model.PhanLoaiSanh> select() {
           return select(SELECT_PhanLoaiSanh);
       }

    public com.happywedding.model.PhanLoaiSanh findById(String id) {
     List<com.happywedding.model.PhanLoaiSanh> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;  
    }
    
     private List select(String sql, Object... args) {
        List<com.happywedding.model.PhanLoaiSanh> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    com.happywedding.model.PhanLoaiSanh course = readFromResultSet(rs);
                    list.add(course);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private com.happywedding.model.PhanLoaiSanh readFromResultSet(ResultSet rs) throws SQLException {
       com.happywedding.model.PhanLoaiSanh pls = new com.happywedding.model.PhanLoaiSanh();

        pls.setMaPL(rs.getString("MaPL"));
        pls.setTenPL(rs.getString("TenPL"));
        return pls;

    }
}
