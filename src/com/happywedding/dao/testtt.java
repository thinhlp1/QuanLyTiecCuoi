/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happywedding.dao;

import com.happywedding.model.Sanh;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class testtt {
    
    public static void testSelectTheoMa(){
        Sanh sanh = new Sanh();
        SanhDAO sanhDAO = new SanhDAO();
        
        sanh = sanhDAO.findById("SANH01");
        
        System.out.println("Mã sảnh: "+sanh.getMaSanh() + " " + sanh.getTenSanh() + " " + sanh.getMaPL() + " " + sanh.getTenPL() + " " + sanh.getSucChua() + " " + sanh.getGiaThueSanh() + " "+ sanh.getGiaBan());
    }
    
    public static void testSelectAll(){
        List<Sanh> sanh = new ArrayList<>();
        SanhDAO sanhDAO = new SanhDAO();
        
        sanh = sanhDAO.select();
        
        //System.out.println("Mã sảnh: "+sanh.getMaSanh() + " " + sanh.getTenSanh() + " " + sanh.getMaPL() + " " + sanh.getTenPL() + " " + sanh.getSucChua() + " " + sanh.getGiaThueSanh() + " "+ sanh.getGiaBan());
        for(int i = 0; i < sanh.size(); i++){
            System.out.println(sanh.get(i).getMaSanh());
        }
    }
    
    
    
    public static void main(String args[]){
       testSelectTheoMa();
       testSelectAll();
    }
}
