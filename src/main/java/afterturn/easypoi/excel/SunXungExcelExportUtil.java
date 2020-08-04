package afterturn.easypoi.excel;

import afterturn.easypoi.excel.export.styler.SunXungExcelExportService;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * SunXungExcelExportUtil
 */
public class SunXungExcelExportUtil {
    public static int USE_SXSSF_LIMIT = 100000;
    public static final String SHEET_NAME = "sheetName";

    private SunXungExcelExportUtil() {
    }

    /**
     * @param entity    表格标题属性
     * @param pojoClass Excel对象Class
     * @param dataSet   Excel对象数据List
     */
    public static Workbook exportExcel(ExportParams entity, Class<?> pojoClass,
                                       Collection<?> dataSet) {
        Workbook workbook = getWorkbook(entity.getType(), dataSet.size());
        new SunXungExcelExportService().createSheet(workbook, entity, pojoClass, dataSet);
        return workbook;
    }

    private static Workbook getWorkbook(ExcelType type, int size) {
        if (ExcelType.HSSF.equals(type)) {
            return new HSSFWorkbook();
        } else if (size < USE_SXSSF_LIMIT) {
            return new XSSFWorkbook();
        } else {
            return new SXSSFWorkbook();
        }
    }

    /**
     * 根据Map创建对应的Excel
     *
     * @param entity     表格标题属性
     * @param entityList Map对象列表
     * @param dataSet    Excel对象数据List
     */
    public static Workbook exportExcel(ExportParams entity, List<ExcelExportEntity> entityList,
                                       Collection<?> dataSet) {
        Workbook workbook = getWorkbook(entity.getType(), dataSet.size());
        ;
        new SunXungExcelExportService().createSheetForMap(workbook, entity, entityList, dataSet);
        return workbook;
    }

    /**
     * 根据Map创建对应的Excel(一个excel 创建多个sheet)
     *
     * @param list 多个Map key title 对应表格Title key entity 对应表格对应实体 key data
     *             Collection 数据
     * @return
     */
    public static Workbook exportExcel(List<Map<String, Object>> list, ExcelType type) {
        Workbook workbook = getWorkbook(type, 0);
        for (Map<String, Object> map : list) {
            SunXungExcelExportService service = new SunXungExcelExportService();
            service.createSheet(workbook, (ExportParams) map.get("title"),
                    (Class<?>) map.get("entity"), (Collection<?>) map.get("data"));
        }
        return workbook;
    }
}
