/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import com.happywedding.model.ThucDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThucDonDAO extends AbstractDAO<ThucDon> {

    private final String INSERT_THUCDON = "INSERT INTO ThucDon (MaTD, TenTD, GhiChu) VALUES (?, ?, ?, ?)";

    private final String UPDATE_THUCDON = "UPDATE ThucDon SET MaTD=?, TenTD=?, GhiChu=? WHERE\n"
            + "MaTD=?";
    private final String DELELTE_THUCDON = "DELETE FROM ThucDon WHERE MaTD=?";
    private final String SELECT_ALL = "SELECT * FROM ThucDon ";
    private final String SELECT_BY_ID = "SELECT * FROM ThucDon WHERE MaTD=? ";

    public boolean insert(ThucDon thucDon) {
        int rs = JDBCHelper.executeUpdate(INSERT_THUCDON,
                thucDon.getMaTD(),
                thucDon.getTenTD(),
                thucDon.getGhiChu()
        );
        return rs > 0;
    }

    public boolean update(ThucDon thucDon) {
        int rs = JDBCHelper.executeUpdate(UPDATE_THUCDON,
                thucDon.getMaTD(),
                thucDon.getTenTD(),
                thucDon.getGhiChu()
        );
        return rs > 0;
    }

    public void delete(String id) {
        int rs = JDBCHelper.executeUpdate(DELELTE_THUCDON, id);
    }

    @Override
    public List<ThucDon> select() {
        return select(SELECT_ALL);
    }

    @Override
    public ThucDon findById(String id) {
        List<ThucDon> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List select(String sql, Object... args) {
        List<ThucDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    ThucDon thucDon = readFromResultSet(rs);
                    list.add(thucDon);
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
    private ThucDon readFromResultSet(ResultSet rs) throws SQLException {

        ThucDon thucDon = new ThucDon();

        thucDon.setMaTD(rs.getString("MaTD"));
        thucDon.setTenTD(rs.getString("TenTD"));
        thucDon.setGhiChu(rs.getString("GhiChu"));

        return thucDon;
//
    }
}
