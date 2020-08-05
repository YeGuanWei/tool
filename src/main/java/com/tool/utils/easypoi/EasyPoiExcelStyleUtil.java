package com.tool.utils.easypoi;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.params.ExcelForEachParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;

public class EasyPoiExcelStyleUtil implements IExcelExportStyler {

    /**
     * 默认字体大小
     */
    private static final short FONT_SIZE_TEN = 10;

    /**
     * 基础数据行样式
     */
    private CellStyle styles;

    /**
     * 数据行样式
     * 红色填充
     */
    private CellStyle stylesBackgroundRed;

    /**
     * 数据行样式
     * 字体红色填充
     */
    private CellStyle stylesFontRed;

    public EasyPoiExcelStyleUtil(Workbook workbook) {
        this.init(workbook);
    }

    /**
     * 初始化样式
     *
     * @param workbook
     */
    private void init(Workbook workbook) {
        this.styles = initStyles(workbook, null, null);
        this.stylesBackgroundRed = initBackgroundRed(workbook);
        this.stylesFontRed = initFontRed(workbook);
    }

    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        return styles;
    }

    @Override
    public CellStyle getTitleStyle(short color) {
        return styles;
    }

    @Override
    public CellStyle getStyles(boolean parity, ExcelExportEntity entity) {
        return styles;
    }

    @Override
    public CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        BigDecimal ret = null;

        try {
            if (data instanceof BigDecimal) {
                ret = (BigDecimal) data;
            } else if (data instanceof String) {
                ret = new BigDecimal((String) data);
            }
        } catch (Exception e) {
            return styles;
        }

        // 当参数小于0或者等于0时,返回红色样式
        if (ret.compareTo(BigDecimal.ZERO) == -1 || ret.compareTo(BigDecimal.ZERO) == 0) {
            return stylesFontRed;
        }
        return styles;
    }

    @Override
    public CellStyle getTemplateStyles(boolean isSingle, ExcelForEachParams excelForEachParams) {
        return styles;
    }

    /**
     * 基础样式
     *
     * @return
     */
    private CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //下边框
        style.setBorderBottom(BorderStyle.THIN);
        //左边框
        style.setBorderLeft(BorderStyle.THIN);
        //上边框
        style.setBorderTop(BorderStyle.THIN);
        //右边框
        style.setBorderRight(BorderStyle.THIN);
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //上下居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 初始化--数据行样式
     *
     * @param workbook
     * @return
     */
    private CellStyle initStyles(Workbook workbook, IndexedColors color, Short fontColor) {
        CellStyle style = getBaseCellStyle(workbook);
        if (fontColor != null) {
            style.setFont(getFont(workbook, FONT_SIZE_TEN, false, fontColor));
        } else {
            style.setFont(getFont(workbook, FONT_SIZE_TEN, false));
        }
        //背景色
        if (color != null) {
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return style;
    }

    /**
     * 初始化--数据行样式 -- 背景红色
     *
     * @param workbook
     * @return
     */
    private CellStyle initBackgroundRed(Workbook workbook) {
        return initStyles(workbook, IndexedColors.RED, null);
    }

    /**
     * 初始化--数据行样式 -- 字体红色
     *
     * @param workbook
     * @return
     */
    private CellStyle initFontRed(Workbook workbook) {
        return initStyles(workbook, null, Font.COLOR_RED);
    }


    /**
     * 字体样式
     *
     * @param size   字体大小
     * @param isBold 是否加粗
     * @return
     */
    private Font getFont(Workbook workbook, short size, boolean isBold) {
        Font font = workbook.createFont();
        //字体样式
        font.setFontName("宋体");
        //是否加粗
        font.setBold(isBold);
        //字体大小
        font.setFontHeightInPoints(size);
        return font;
    }

    /**
     * 字体样式
     *
     * @param size   字体大小
     * @param isBold 是否加粗
     * @param color  颜色
     * @return
     */
    private Font getFont(Workbook workbook, short size, boolean isBold, short color) {
        Font font = getFont(workbook, size, isBold);
        font.setColor(color);
        return font;
    }

}
