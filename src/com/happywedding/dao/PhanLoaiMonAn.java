package com.happywedding.dao;

import com.happywedding.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhanLoaiMonAn {

    private final String SELECT_PhanLoaiMonAn = "SELECT MaPL, TenPL FROM dbo.PhanLoaiMonAn";
    private final String SELECT_BY_ID = "SELECT * FROM PhanLoaiMonAn WHERE MaPL=?";

    public List<com.happywedding.model.PhanLoaiMonAn> select() {
           return select(SELECT_PhanLoaiMonAn);
       }

    public com.happywedding.model.PhanLoaiMonAn findById(String id) {
     List<com.happywedding.model.PhanLoaiMonAn> list = select(SELECT_BY_ID, id);
        return list.size() > 0 ? list.get(0) : null;  
    }
    
     private List select(String sql, Object... args) {
        List<com.happywedding.model.PhanLoaiMonAn> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    com.happywedding.model.PhanLoaiMonAn course = readFromResultSet(rs);
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

    private com.happywedding.model.PhanLoaiMonAn readFromResultSet(ResultSet rs) throws SQLException {
       com.happywedding.model.PhanLoaiMonAn pls = new com.happywedding.model.PhanLoaiMonAn();

        pls.setMaPL(rs.getString("MaPL"));
        pls.setTenPL(rs.getString("TenPL"));
        return pls;

    }
}
