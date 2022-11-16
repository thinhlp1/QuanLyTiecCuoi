/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.model.ChiTietDichVu;
import com.happywedding.model.HopDongDichVu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDichVuDAO {
     /*
    thêm vào HopDongDichVu
    */
    public boolean insertDichVu(HopDongDichVu ctdv){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean updateDichVu(HopDongDichVu ctdv ){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public HopDongDichVu selectDichVu(String maHD, String maDV ){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void removeAllHopDongDichVu(String maHD){
        
    }
    /*
    thêm vào bảng ChiTietDichVu
    */
    public boolean insertChiTietDichVy(ChiTietDichVu csvc){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean updateChiTietDichVu(ChiTietDichVu cscv){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public List<ChiTietDichVu> selectAllChiTietDichVu(String maHD, String maDV){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
