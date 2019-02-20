package com.qh.modules.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel导入工具类
 *
 * Created by Administrator on 2018/8/17.
 */
public class ReadExcelHelper {
    /**
     * Excel 2003
     */
    private final static String XLS = "xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = "xlsx";

    /**
     * 由Excel文件的Sheet导出至List
     *
     * @param file     导入的excel文件
     * @return
     */
    public static List<Map<String, Object>> exportListFromExcel(File file) throws IOException {
        return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()));
    }

    /**
     * 由Excel流的Sheet导出至List
     *
     * @param is            输入流
     * @param extensionName 后缀名
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> exportListFromExcel(InputStream is, String extensionName) throws IOException {
        Workbook workbook = null;
        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        }
        return readCell(workbook);
    }

    /**
     * 读取Cell的值
     *
     * @param workbook 工作薄
     * @return
     */
    public static List<Map<String, Object>> readCell(Workbook workbook) {

        // 解析公式结果
        // FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //循环工作薄Sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            } else {
                // 循环行Row
                // 遍历所有行
                // for (Row row : sheet)
                // 除去表头即第一行
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    Map<String, Object> map = new HashMap<String, Object>();
                    // 遍历所有列
                    if (row == null) {
                        continue;
                    } else {
                        for (Cell cell : row) {   //通过POI取出的数值默认都是double，即使excel单元格中存的是1，取出来的值也是1.0
                            // 获取单元格的类型
                            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                            String key = cellRef.formatAsString();
                            switch (cell.getCellType()) {
                                // 字符串
                                case Cell.CELL_TYPE_STRING:
                                    map.put(key, cell.getRichStringCellValue().getString());
                                    break;
                                //数值型
                                case Cell.CELL_TYPE_NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        String cellValue = sdf.format(date);
                                        map.put(key, cellValue);
                                    } else {
                                        /*cell.setCellType(Cell.CELL_TYPE_STRING);
                                        String s = cell.toString();*/
                                        map.put(key, NumberToTextConverter.toText(cell.getNumericCellValue()));
                                    }
                                    break;
                                // boolean
                                case Cell.CELL_TYPE_BOOLEAN:
                                    map.put(key, cell.getBooleanCellValue());
                                    break;
                                // 公式型
                                case Cell.CELL_TYPE_FORMULA:
                                    map.put(key, cell.getCellFormula());
                                    break;
                                case Cell.CELL_TYPE_BLANK:
                                    map.put(key, "");
                                    break;
                                case Cell.CELL_TYPE_ERROR:
                                    map.put(key, "");
                                    break;
                                // 空值
                                default:
                                    map.put(key, "");
                            }
                        }
                    }
                    list.add(map);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        /*String paths = ReadExcelHelper.class.getResource("E:\\水表批量导入模板.xlsx").getFile();
        List<Map<String, Object>> lists = ReadExcelHelper.exportListFromExcel(new File(paths), 0);
        System.out.println(lists);*/
    }
}
