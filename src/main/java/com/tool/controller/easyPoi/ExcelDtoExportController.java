package com.tool.controller.easyPoi;

import afterturn.easypoi.excel.SunXungExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.tool.entity.easypoi.EasyPoiExcelDTO;
import com.tool.utils.easypoi.EasyPoiExcelStyleUtil;
import com.tool.utils.easypoi.EasyPoiExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDtoExportController {

    public void export(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 导出准备工作 ===================================================================
        List<EasyPoiExcelDTO> excelDtoList = new ArrayList<>();
        EasyPoiExcelDTO excelDto = null;

        // 导出 ======================================
        String sheetName = "sheet";
        String excelName = "class.xls";

        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(EasyPoiExcelStyleUtil.class);
        exportParams.setSheetName(sheetName);

        // 生成workbook 并导出
        // Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ApplyStatisticsDetailExcelDto.class, excelDtoList);
        Workbook workbook = SunXungExcelExportUtil.exportExcel(exportParams, EasyPoiExcelDTO.class, excelDtoList);
        EasyPoiExcelUtil.downLoadExcel(request, response, excelName, workbook);
    }

}
