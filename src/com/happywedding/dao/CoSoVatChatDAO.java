/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.model.CoSoVatChat;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CoSoVatChatDAO extends AbstractDAO<CoSoVatChat>{

    @Override
    public boolean insert(CoSoVatChat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CoSoVatChat entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CoSoVatChat> select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CoSoVatChat findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    lấy danh sách csvc theo mã danh mục
    */
    public List<CoSoVatChat> selectByMaDM(String maDM){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
     /*
    lấy danh sách csvc theo mã danh mục con
    */
    public List<CoSoVatChat> selectByMaDMC(String maDMC){
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
