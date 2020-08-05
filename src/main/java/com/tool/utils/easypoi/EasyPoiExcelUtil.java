package com.tool.utils.easypoi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import com.tool.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EasyPoiExcelUtil {

    private static Logger logger = LogManager.getLogger(ExcelImportUtil.class);

    public static void downLoadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + getEncodeFileName(request, fileName));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            logger.error("导出失败", e.getMessage());
        }
    }

    private static String getEncodeFileName(HttpServletRequest request, String fileName) throws Exception {
        String userAgent = request == null ? null : request.getHeader("USER-AGENT");
        if (StringUtil.isNotBlank(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("Firefox".toLowerCase())) {
                fileName = new String(fileName.getBytes(), "ISO-8859-1");
            } else {
                fileName = urlEncode(fileName, "UTF-8");
            }
        } else {
            fileName = urlEncode(fileName, "UTF-8");
        }

        return fileName;
    }

    private static String urlEncode(String str, String charset) throws UnsupportedEncodingException {
        return !StringUtil.isEmpty(str) ? URLEncoder.encode(str, charset).replaceAll("\\+", "%20") : str;
    }

}
