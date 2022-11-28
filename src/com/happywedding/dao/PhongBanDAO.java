/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.PhongBan;
import com.happywedding.model.TaiKhoan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PhongBanDAO {

//    public List<PhongBan> select() {
//
//    }
//
//    public PhongBan findById(String id) {
//
//    }

    private List select(String sql, Object... args) {
        List<PhongBan> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    PhongBan PhongBan = readFromResultSet(rs);
                    list.add(PhongBan);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private PhongBan readFromResultSet(ResultSet rs) throws SQLException {
        PhongBan PhongBan = new PhongBan();
        return PhongBan;
    }
}
