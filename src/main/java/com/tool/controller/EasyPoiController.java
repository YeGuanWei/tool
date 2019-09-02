package com.tool.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送Post请求
 */
public class EasyPoiController {

    public static void main(String[] args) throws Exception {
        //trendsExport();
        //templateExport();
        trendsExport2();
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
            temp.add(new ExcelExportEntity("合并2", "students2"));
            excelEntity.setList(temp);

            entity.add(excelEntity);

            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 1; i < 6; i++) {
                Map map = new HashMap();
                map.put("name", "姓名 " + i);
                map.put("sex", "性别 " + i);

                List<Map> listMap = new ArrayList<>();
                Map map1 = new HashMap();
                map1.put("students1","合并1" + i);
                listMap.add(map1);

                map.put("students", listMap);

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
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("测试", "测试"), entity,
                    list);
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
     * 模板导出
     *
     * @throws Exception
     */
    public static void templateExport() throws Exception {
        TemplateExportParams params = new TemplateExportParams("./doc/test.xlsx");

        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("no", String.valueOf(i + 1));
            lm.put("name", String.valueOf(i + 1));
            listMap.add(lm);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", "单个测试数据");

        map.put("list", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("C:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("C:/excel/test.xlsx");
        workbook.write(fos);
        fos.close();
    }

}
