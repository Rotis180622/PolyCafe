/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.utils;

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gaudomun
 */
public class XFile {
    public static void exportFile(Component parent, String title, String[] header, String fileName, List<Object[]> listObj) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter findXlsx = new FileNameExtensionFilter("Excel (xlsx)", "xlsx", "xlsx");
        fileChooser.setFileFilter(findXlsx);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("C:\\FIle Export"));
        fileChooser.setDialogTitle("Chọn nơi lưu");
        fileChooser.setSelectedFile(new File(fileName));
        int x = fileChooser.showDialog(parent, "Chọn thư mục");
        if(x == JFileChooser.APPROVE_OPTION) {
            try {
               String path = fileChooser.getSelectedFile().getAbsolutePath();
               if(!path.endsWith("xlsx")) path += ".xlsx";
               XCel.clear();
               XCel.setTitle(title);
               XCel.setHeader(header);
               XCel.setListObj(listObj);
               
               XCel.create(path);
               if(XDialog.confirm("Bạn có muốn mở file không?")) {
                   Desktop desktop = Desktop.getDesktop();
                   desktop.open(new File(path));
               }
            }catch(IOException ioe) {
                 ioe.printStackTrace();
                XDialog.alert("Lưu thất bại!");
            }
        }
    }
}

