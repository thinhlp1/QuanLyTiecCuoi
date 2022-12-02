/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;

import com.happywedding.model.TrangThaiHopDong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TrangThaiHopDongDAO {
    private final String SELECT = "SELECT * FROM TrangThaiHopDong";
       public List<TrangThaiHopDong> select() {
        return select(SELECT);
    }

     private List select(String sql, Object... args) {
        List<TrangThaiHopDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    TrangThaiHopDong tt = readFromResultSet(rs);
                    list.add(tt);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    // sửa lại
    private TrangThaiHopDong readFromResultSet(ResultSet rs) throws SQLException {

        TrangThaiHopDong tt = new TrangThaiHopDong();
        
        tt.setMaTT(rs.getString("MaTT"));
        tt.setTenTT(rs.getString("TenTT"));
        

        return tt;
//
    }
}
