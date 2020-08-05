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
 * 模板导出
 */
public class TemplateExportController {

    public static void main(String[] args) throws Exception {
        templateExport();
    }

    /**
     * 模板导出
     *
     * @throws Exception
     */
    public static void templateExport() throws Exception {
        TemplateExportParams params = new TemplateExportParams("./doc/EasyPoiExcel.xlsx");

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
        FileOutputStream fos = new FileOutputStream("C:/excel/EasyPoiExcel.xlsx");
        workbook.write(fos);
        fos.close();
    }

}
