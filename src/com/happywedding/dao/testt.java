/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.dao;

import com.happywedding.model.ChiTietDatMon;
import com.happywedding.model.ChiTietDichVuDiKem;
import com.happywedding.model.DichVuDatMon;
import com.happywedding.model.GoiDichVu;
import com.happywedding.model.HoaDon;
import com.happywedding.model.HopDongDichVuDiKem;
import com.happywedding.model.MonAn;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class testt {
     public static void main(String[] args) {
//         DichVuDatMon dv = new DichVuDatMon();
//         ChiTietDatMonDAO dao = new ChiTietDatMonDAO();
//         
//         dv = dao.selectDichVuDatMon("HD003");
//        // System.out.println(dv.getMaHD() + " " + dv.getMaTD()+ " " +dv.getChiPhi()+ " " +dv.getChiPhiPhatSinh()+ " " +dv.getGhiChu());
//        List<ChiTietDatMon> list = dao.selectChiTietDatMon("HD001");
//        for (int i = 0; i < list.size();i++){
//            ChiTietDatMon ct = list.get(i);
//            //System.out.println(ct.getMaHD() + " " + ct.getMaMA()+ " " + ct.getTenMA()+ " "+ct.getGia()+ " "+ct.getChiPhiPhatSinh()+ " " + ct.getGhiChu()+ " ");
//        }
//       
//       // dv.setChiPhi(100000);
//       // dao.insertDichVuDatMon(dv);
//        //dao.updateDichVuDatMon(dv);
//       
////          for (int i = 0; i < list.size();i++){
////            ChiTietDatMon ct = list.get(i);
////            ct.setMaHD("HD003");
////            dao.insertChiTietDatMon(ct);
////            //System.out.println(ct.getMaHD() + " " + ct.getMaMA()+ " " + ct.getTenMA()+ " "+ct.getGia()+ " "+ct.getChiPhiPhatSinh()+ " " + ct.getGhiChu()+ " ");
////        }
//
//        dao.removeAllChiTietDatMon("HD003");
            
            

          //ChiTietDichVuDiKemDAO dao = new ChiTietDichVuDiKemDAO();
          
         // dao.insertHopDongDichVuDiKem(new HopDongDichVuDiKem("HD003",1000000,0,null));
          
          
         // HopDongDichVuDiKem hd = dao.selectHopDongDichVuDiKem("HD003");
         // hd.setChiPhi(2000000);
          ///dao.updateHopDongDichVuDiKem(hd);
          //  hd = dao.selectHopDongDichVuDiKem("HD003");
         // System.out.println(hd.getMaHD() + " " + hd.getChiPhi() + " " + hd.getChiPhiPhatSinh() + " " + hd.getGhiChu());
          
          
//          ChiTietDichVuDiKem ct = new ChiTietDichVuDiKem("HD003", "BANHKEM", "Bánh kem", "bánh kem có socola", 0, 0);
//         dao.insertChiTietDichVuDiKem(ct);
////          dao.removeAllChiTietDichVuDiKem("HD003");
//          List<ChiTietDichVuDiKem> list = dao.selectChiTietDichVuDiKem("HD003");
//          for (int i = 0; i < list.size();i++){
//              ChiTietDichVuDiKem ctt = list.get(i);
//              System.out.println(ctt.getMaHD() + " " + ctt.getMaDV()+ " " +ctt.getTenDV()+ " "+ ctt.getChiPhi()+ " "+ ctt.getGhiChu()+ " "+ ctt.getChiPhiPhatSinh()+ " " );
//          }
//            GoiDichVuDAO dao = new GoiDichVuDAO();
//            List<GoiDichVu> list= dao.selectGoiDichVu("TTBANTIEC");
//            for (int i = 0;i < list.size();i++){
//                GoiDichVu goi = list.get(i);
//                System.out.println(  goi.getMaGoi() + " "+ goi.getTenGoi()+ " "+ goi.getMaDV()+ " "+ goi.getChiPhi()+ " "+ goi.getGhiChu()+ " "+ goi.getHinhAnh()+ " ");
//            }
//            
//            for (int i = 0; i < 2;i++){
//                
//            }

//              ChiTietDatMonDAO dao = new ChiTietDatMonDAO();
//              List<MonAn> list = dao.selectNotinHopDong("HD001");
//              for (int i = 0; i < list.size();i++){
//                  MonAn ma = list.get(i);
//                  System.out.println( i+ "-" + ma.getTenMA() + " " + ma.getGiaTien() + " "  );
//              }

                HoaDonDAO dao = new HoaDonDAO();
                HoaDon hd = dao.selectHoaDon("HD002");
              
                hd.setMaHD("HD003");
             hd.setMaNV("NV002");
//                hd.setTongTien(1234254254);
                dao.insertHoaDon(hd);
                hd = dao.selectHoaDon("HD003");
                 System.out.println( hd.getMaHoaDon() + "  "+hd.getMaHD() + " "  + hd.getNgayLap()+ " "+ hd.getTenNV()+ " " 
                       + hd.getTienCoc()+ " "+ hd.getPhatSinh()+ " "+ hd.getTongTien()+ " "+ hd.getTrangTha()+ " ");
                //dao.updateHoaDon(999999,"HD003");
                
              //  dao.insertChiPhiPhatSinh("HD003", "TTBANTIEC",199090, "them");
                
     
     
     }          
     


}
