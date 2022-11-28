/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happywedding.model;

/**
 *
 * @author Hades
 */
public class Sanh {

    private String maSanh;
    private String tenSanh;
    private String maPL;
    private String tenPL;
    private int sucChua;
    private long giaThueSanh;
    private long giaBan;

    public Sanh(String maSanh, String tenSanh, String maPL, String tenPL, int sucChua, long giaThueSanh, long giaBan) {
        this.maSanh = maSanh;
        this.tenSanh = tenSanh;
        this.maPL = maPL;
        this.tenPL = tenPL;
        this.sucChua = sucChua;
        this.giaThueSanh = giaThueSanh;
        this.giaBan = giaBan;
    }

    public Sanh() {
        
    }
    
    @Override
    public String toString(){
        return tenSanh;
    }
    
    
    public String getMaSanh() {
        return maSanh;
    }

    public void setMaSanh(String maSanh) {
        this.maSanh = maSanh;
    }

    public String getTenSanh() {
        return tenSanh;
    }

    public void setTenSanh(String tenSanh) {
        this.tenSanh = tenSanh;
    }

    public String getMaPL() {
        return maPL;
    }

    public void setMaPL(String maPL) {
        this.maPL = maPL;
    }
    
    public String getTenPL(){
        return tenPL;
    }
    
    public void setTenPL(){
        this.maPL = maPL;
    }

    public long getSucChua() {
        return sucChua;
    }

    public void setTenPL(String tenPL) {
        this.tenPL = tenPL;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

   
    public long getGiaThueSanh() {
        return giaThueSanh;
    }

    public void setGiaThueSanh(long giaThueSanh) {
        this.giaThueSanh = giaThueSanh;
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    
}
