/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.helper;

import com.happywedding.app.HappyWeddingApp;
import com.ui.swing.component.Menu;
import java.awt.Robot;



/**
 *
 * @author ADMIN
 */
public class AppStatus {

//    /**
//     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
//     */
    public static Object USER = null;
    public static int STATUS = 0;
    public static int ROLE = 0;

   
    public static HappyWeddingApp mainApp = null;
    public static Menu menu = null;
    public static Robot bot;
    
    
    public static  int menuQLHoaDonX = 250;
    
    
    
    //0 is not load
    //1 is loading
    public static void loadApp(){
        try {
               bot = new Robot();
        } catch (Exception e) {
        }
//        STATUS = 1;
    }

    public static void logoff() {
        AppStatus.USER = null;
    }
//    
    public static boolean isFirstStart(){
        return STATUS == 1;
    }

}
