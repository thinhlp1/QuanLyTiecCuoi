/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happywedding.helper;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ShareHelper {

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static final Image APP_ICON;

    static {
        // Tải biểu tượng ứng dụng
        String file = "/com/happywedding/assets/app_icon.png";
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();

    }

    public static boolean saveLogo(File file) {
        File dir = new File("logos");
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {

            return false;
        }
    }

 

    /**
     * Đọc hình ảnh logo
     *
     * @param fileName là tên file logo
     * @return ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static String toMoney(long moneyy) {
        String money = moneyy + "";
        int x = 0; // cứ 3 số đơn vị sẽ là một dấu chấm
        for (int i = money.length() - 1; i >= 0; i--) {
            x++;
            if (x == 3) {
                money = money.substring(0, i) + '.' + money.substring(i, money.length());
                x = 0;
            }
        }
        if (money.charAt(0) == '.') {
            money = money.replaceFirst(".", "");
        }

        return money + " ngàn đồng";
    }

    public static long toMoney(String moneyy) {
        return Long.parseLong(moneyy.replaceAll("\\.", ""));
    }

    public static String to24Hour(String time) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date;
        try {
            date = parseFormat.parse(time);

            System.out.println(displayFormat.format(date));
            return displayFormat.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(ShareHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time;

    }

}
