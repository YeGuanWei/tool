package com.tool.controller.easyPoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelXorHtmlUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EasyPoi动态导出
 */
public class TrendsExportController {

    public static void main(String[] args) throws Exception {
        trendsExport2();
    }

    /**
     * 动态导出
     */
    public static void trendsExport() {
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
            // 构造对象等同于@Excel
            ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
            excelentity.setNeedMerge(true);
            entity.add(excelentity);
            entity.add(new ExcelExportEntity("性别", "sex"));
            excelentity = new ExcelExportEntity("123456", "students");
            List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
            temp.add(new ExcelExportEntity("姓名", "name"));
            temp.add(new ExcelExportEntity("性别", "sex"));
            // 构造List等同于@ExcelCollection
            excelentity.setList(temp);
            entity.add(excelentity);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            // 把我们构造好的bean对象放到params就可以了
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试", "速度测试", "测试"), entity, list);
            FileOutputStream fos = new FileOutputStream("C:/excel/ExcelExportForMap.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态导出2
     */
    public static void trendsExport2() {
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();

            entity.add(new ExcelExportEntity("姓名", "name"));
            entity.add(new ExcelExportEntity("性别", "sex"));

            ExcelExportEntity excelEntity = new ExcelExportEntity("合并", "students");
            excelEntity.setNeedMerge(true);
            List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
            temp.add(new ExcelExportEntity("合并1", "students1"));
            temp.add(new ExcelExportEntity("合并1", "students1"));
            temp.add(new ExcelExportEntity("合并3", "students3"));
            excelEntity.setList(temp);

            entity.add(excelEntity);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 1; i < 6; i++) {
                Map map = new HashMap();

                List<Map> listMap = new ArrayList<>();
                Map map1 = new HashMap();
                map1.put("students2", "合并2" + i);
                map1.put("students3", "合并3" + i);
                listMap.add(map1);

                map.put("students", listMap);


                map.put("name", "姓名 " + i);
                map.put("sex", "性别 " + i);

                list.add(map);
            }

            // 把我们构造好的bean对象放到params就可以了
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), entity, list);
            FileOutputStream fos = new FileOutputStream("C:/excel/ExcelExportForMap.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态导出3
     */
    public static void trendsExport3() {
        try {
            List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();

            entity.add(new ExcelExportEntity("姓名", "name"));
            entity.add(new ExcelExportEntity("性别", "sex"));

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 1; i < 6; i++) {
                Map map = new HashMap();

                map.put("name", "姓名 " + i);
                map.put("sex", "性别 " + i);

                list.add(map);
            }

            // 把我们构造好的bean对象放到params就可以了
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "t"), entity, list);

            Sheet sheet = workbook.getSheet("t");

            int i = 1;

            Row row = sheet.createRow((short) (sheet.getLastRowNum() + 1));
            row.createCell(i++).setCellValue("546546");
            row.createCell(i++).setCellValue("546546");
            row.createCell(i++).setCellValue("546546");

            FileOutputStream fos = new FileOutputStream("C:/excel/ExcelExportForMap.xls");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
