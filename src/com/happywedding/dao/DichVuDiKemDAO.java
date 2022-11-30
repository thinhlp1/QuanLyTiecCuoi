/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ChiTietDichVuDiKem;
import com.happywedding.model.DichVuDiKemModel;
import com.happywedding.view.manage.DichVuDiKem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DichVuDiKemDAO {

    private final String SELECT = "SELECT * FROM DichVuDiKem";

    public List<DichVuDiKemModel> select() {
        return selectDichVuDiKem(SELECT);
    }

    private List selectDichVuDiKem(String sql, Object... args) {
        List<DichVuDiKemModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    DichVuDiKemModel model = readichVuDiKemFromResultSet(rs);
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

    private DichVuDiKemModel readichVuDiKemFromResultSet(ResultSet rs) throws SQLException {
        DichVuDiKemModel dvdk = new DichVuDiKemModel();

        dvdk.setMaDV(rs.getString("MaDV"));
        dvdk.setTenDV(rs.getString("TenDV"));
        dvdk.setGia(rs.getLong("Gia"));
        return dvdk;

    }

}
