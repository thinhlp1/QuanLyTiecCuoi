/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.helper;

import com.happywedding.app.HappyWeddingApp;
import com.happywedding.model.CoSoVatChat;
import com.happywedding.model.NhanVien;
import com.happywedding.model.PhanLoaiMonAn;
import com.happywedding.model.PhanLoaiSanh;
import com.happywedding.model.PhongBan;
import com.happywedding.model.VaiTro;
import com.happywedding.view.manage.LapHopDong;
import com.ui.swing.component.Menu;
import java.awt.Robot;
import java.util.List;



/**
 *
 * @author ADMIN
 */
public class AppStatus {

//    /**
//     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
//     */
    public static NhanVien USER = null;
    public static int STATUS = 0;
    public static String ROLE = "QLCC" ;

   
    public static HappyWeddingApp mainApp = null;
    public static LapHopDong lapHopDong = null;
    public static Menu menu = null;
    
    //0 is not load
    //1 is loading
    public static void loadApp(){
      
        STATUS = 1;
    }

    public static void logoff() {
        AppStatus.USER = null;
    }
//    
    public static boolean isFirstStart(){
        return STATUS == 1;
    }

}
