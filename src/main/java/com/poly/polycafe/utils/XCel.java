/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.polycafe.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Gaudomun
 */
public class XCel {
    private static String[] cols = null;
    private static String title = null;
    private static int index = 0;
    private static CellStyle cellStyleFormatNumber = null;
    private static List<Object[]> listObj = new ArrayList<>();
    
    public static void create(String path) throws IOException {
        final List<Object[]> objects = getListObj();
        writeExcel(objects, path);
    }
    
    public static void setListObj(List<Object[] >listObject) {
        listObj = listObject;
    }
    
    public static List<Object[]> getListObj() {
        return listObj;
    }
    
    public static void clear() {
        cols = null;
        listObj.clear();
        title = null;
        index = 0;
    }
    
    public static void create() throws IOException {
        final List<Object[]> listObj = getListObj();
    }

    public static Workbook getWorkbook(String excelFilePath) throws IOException{ 
        Workbook workbook = null;
        
        if(excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        }else if(excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        }else {
            throw new IllegalArgumentException("File này không phải định dạng excel");
        }
        
        return workbook;
    }
    
    public static void writeExcel(List<Object[]> listObj, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        
        Sheet sheet = workbook.createSheet("1");
        int rowIndex = 1;
        
        writeTitle(sheet);
        writeHeader(sheet, rowIndex);
        rowIndex++;
        
        for(Object[] objs : listObj) {
            index++;
            Row row = sheet.createRow(index);
            writeObject(rowIndex, objs, index, row);
            rowIndex++;
        }
        int numOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numOfColumn);
        
        createOutputFile(workbook, excelFilePath);
    }
    
    public static void setTitle(String title) { XCel.title = title; }

    public static void writeTitle(Sheet sheet) {
        CellStyle cellStyle = createStyleForTitle(sheet);
        
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0, cols.length));
         titleCell.setCellStyle(cellStyle);
         titleCell.setCellValue(title);
    }
    
    public static void setHeader(String[] head) { cols = head; }
    
    public static void writeHeader(Sheet sheet, int rowIndex) {
        CellStyle cellStyle = createStyleForTitle(sheet);
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue("STT");
        cell.setCellStyle(cellStyle);
        
        for(int i=0; i<cols.length; i++) {
            row.createCell(i+1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(cols[i]);
            
        }
    }
    
    public static CellStyle createStyleForTitle(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.BLUE.getIndex());
        
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
    
    private static CellStyle createStyleForHeader(Sheet sheet) {
       Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());
 
        
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
    
    private static void writeObject(int rowIndex, Object[] objs, int index, Row row) {
        if(cellStyleFormatNumber == null) {
            short format = (short) BuiltinFormats.getBuiltinFormat("#.##0");
            
            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }
        Cell stt = row.createCell(0);
        stt.setCellValue(String.valueOf(index));
        for(int i =0; i<objs.length; i++) {
            Object obj = objs[i];
            Cell cell = row.createCell(i+1);
            cell.setCellValue(obj.toString());
        }
    }
    
      private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

}
