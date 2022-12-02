/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.DanhMuc;
import com.happywedding.model.PhongBan;
import com.happywedding.model.VaiTro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */


public class VaiTroDAO {
    
    private final String SELECT_VAITRO = "SELECT MaVT,TenVT,pb.MaPB FROM VaiTro vt INNER JOIN PhongBan pb ON vt.MaPB = pb.MaPB";
    private final String SELECT_BY_ID = "SELECT * FROM VaiTro vt INNER JOIN PhongBan pb ON vt.MaPB = pb.MaPB";
    
    public List<VaiTro> select() {
        return select(SELECT_VAITRO);
    }

    public VaiTro findById(String id) {
        List<VaiTro> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<VaiTro> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    VaiTro course = readFromResultSet(rs);
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

    private VaiTro readFromResultSet(ResultSet rs) throws SQLException {
        VaiTro pls = new VaiTro();

        pls.setMaVT(rs.getString("MaVT"));
        pls.setMaPB(rs.getString("MaPB"));
        pls.setTenVT(rs.getString("TenVT"));
        return pls;

    }
}
