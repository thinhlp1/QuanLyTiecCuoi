/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.model.MonAn;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChiTietDatMonDAO {
     /*
    thêm vào bảng dịch vụ đặt món
    */
    public boolean insertDichVuDatMon(String maHD, long ChiPhi){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean updateDichVuDatMon(String maHD, long ChiPhi){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean insertChiTietDatMon(String maHD, String maMA, String thuTu,String ghiChu ){       
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public boolean updateChiTietDatMon(String maHD, String maMA, String thuTu,String ghiChu ){       
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /*
    reset lại các món ăn trong ChiTietDatMon
    */
    public boolean removeAllChiTietDatMon(String maHD){
         throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /*
    lấy các món ăn có trong thực đơn
    */
    public List<MonAn> selectByMaThucDon(String maTD){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /*
    lấy các món ăn người dùng đặt
    */
    public List<MonAn> selectByMaHD(String maHD){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /*
    lấy các món ăn ko có trong thực đơn
    */
    public List<MonAn> selectNotinHopDong(String maTD){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /*
    lấy các món ăn ko có trong thực đơn
    */
    public List<MonAn> selectNotinThucDon(String maHD){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
