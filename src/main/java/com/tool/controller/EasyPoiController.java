package com.tool.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
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
        test();
    }

    public static void test() throws Exception {
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
