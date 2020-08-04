package afterturn.easypoi.excel.export.styler;

import afterturn.easypoi.excel.entity.params.SunXungExportParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.entity.vo.BaseEntityTypeConstants;
import cn.afterturn.easypoi.excel.entity.vo.PoiBaseConstants;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import cn.afterturn.easypoi.exception.excel.ExcelExportException;
import cn.afterturn.easypoi.exception.excel.enums.ExcelExportEnum;
import cn.afterturn.easypoi.util.PoiMergeCellUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.Collection;
import java.util.List;

/**
 * SunXungExcelExportService
 */
public class SunXungExcelExportService extends ExcelExportService {
    private int sunXungCurrentIndex = 0;

    @Override
    protected int createHeaderAndTitle(ExportParams entity, Sheet sheet, Workbook workbook,
                                       List<ExcelExportEntity> excelParams) {
        int rows = 0, fieldLength = getFieldLength(excelParams);
        if (entity.getTitle() != null) {
            rows += createTitle2Row(entity, sheet, workbook, fieldLength);
        }
        createHeaderRow(entity, sheet, workbook, rows, excelParams, 0);
        rows += getRowNums(excelParams, true);
        if (entity.isFixedTitle()) {
            sheet.createFreezePane(0, rows, 0, rows);
        }
        return rows;
    }

    /**
     * 创建表头
     */
    private int createHeaderRow(ExportParams title, Sheet sheet, Workbook workbook, int index,
                                List<ExcelExportEntity> excelParams, int cellIndex) {
        Row row = sheet.getRow(index) == null ? sheet.createRow(index) : sheet.getRow(index);
        int rows = getRowNums(excelParams, true);
        row.setHeight(title.getHeaderHeight());
        Row listRow = null;
        if (rows >= 2) {
            listRow = sheet.createRow(index + 1);
            listRow.setHeight(title.getHeaderHeight());
        }
        int groupCellLength = 0;
        CellStyle titleStyle = getExcelExportStyler().getTitleStyle(title.getColor());
        for (int i = 0, exportFieldTitleSize = excelParams.size(); i < exportFieldTitleSize; i++) {
            ExcelExportEntity entity = excelParams.get(i);

            /*if (getExcelExportStyler() instanceof IExcelExportStyler) {
                titleStyle = ((IExcelExportStyler) getExcelExportStyler()).getTitleStyle(title.getColor(), entity);
            }*/

            // 加入换了groupName或者结束就，就把之前的那个换行
            if (StringUtils.isBlank(entity.getGroupName()) || i == 0 || !entity.getGroupName().equals(excelParams.get(i - 1).getGroupName())) {
                if (groupCellLength > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(index, index, cellIndex - groupCellLength, cellIndex - 1));
                }
                groupCellLength = 0;
            }
            if (StringUtils.isNotBlank(entity.getGroupName())) {
                createStringCell(row, cellIndex, entity.getGroupName(), titleStyle, entity);
                createStringCell(listRow, cellIndex, entity.getName(), titleStyle, entity);
                groupCellLength++;
            } else if (StringUtils.isNotBlank(entity.getName())) {
                createStringCell(row, cellIndex, entity.getName(), titleStyle, entity);
            }
            if (entity.getList() != null) {
                // 保持原来的
                int tempCellIndex = cellIndex;
                cellIndex = createHeaderRow(title, sheet, workbook, rows == 1 ? index : index + 1, entity.getList(), cellIndex);
                List<ExcelExportEntity> sTitel = entity.getList();
                if (StringUtils.isNotBlank(entity.getName()) && sTitel.size() > 1) {
                    PoiMergeCellUtil.addMergedRegion(sheet, index, index, tempCellIndex, tempCellIndex + sTitel.size() - 1);
                }
                /*for (int j = 0, size = sTitel.size(); j < size; j++) {

                    createStringCell(rows == 2 ? listRow : row, cellIndex, sTitel.get(j).getName(),
                            titleStyle, entity);
                    cellIndex++;
                }*/
                cellIndex--;
            } else if (rows > 1 && StringUtils.isBlank(entity.getGroupName())) {
                createStringCell(listRow, cellIndex, "", titleStyle, entity);
                PoiMergeCellUtil.addMergedRegion(sheet, index, index + rows - 1, cellIndex, cellIndex);
            }
            cellIndex++;
        }
        if (groupCellLength > 1) {
            PoiMergeCellUtil.addMergedRegion(sheet, index, index, cellIndex - groupCellLength, cellIndex - 1);
        }
        return cellIndex;

    }

    /**
     * 创建 表头改变
     */
    @Override
    public int createTitle2Row(ExportParams entity, Sheet sheet, Workbook workbook,
                               int fieldWidth) {

        Row row = sheet.createRow(0);
        row.setHeight(entity.getTitleHeight());
        createStringCell(row, 0, entity.getTitle(),
                getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), null);
        for (int i = 1; i <= fieldWidth; i++) {
            createStringCell(row, i, "",
                    getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), null);
        }
        PoiMergeCellUtil.addMergedRegion(sheet, 0, 0, 0, fieldWidth);
        if (entity.getSecondTitle() != null) {
            row = sheet.createRow(1);
            row.setHeight(entity.getSecondTitleHeight());
            CellStyle style = workbook.createCellStyle();
            if (entity instanceof SunXungExportParams) {
                style.setAlignment(((SunXungExportParams) entity).getSecondTitleAlign());
            } else {
                style.setAlignment(HorizontalAlignment.RIGHT);
            }
            createStringCell(row, 0, entity.getSecondTitle(), style, null);
            for (int i = 1; i <= fieldWidth; i++) {
                createStringCell(row, i, "",
                        getExcelExportStyler().getHeaderStyle(entity.getHeaderColor()), null);
            }
            PoiMergeCellUtil.addMergedRegion(sheet, 1, 1, 0, fieldWidth);
            return 2;
        }
        return 1;
    }

    private int createIndexCell(Row row, int index, ExcelExportEntity excelExportEntity) {
        if (excelExportEntity.getName() != null && "序号".equals(excelExportEntity.getName()) && excelExportEntity.getFormat() != null
                && excelExportEntity.getFormat().equals(PoiBaseConstants.IS_ADD_INDEX)) {
            createStringCell(row, 0, sunXungCurrentIndex + "",
                    index % 2 == 0 ? getStyles(false, null) : getStyles(true, null), null);
            sunXungCurrentIndex = sunXungCurrentIndex + 1;
            return 1;
        }
        return 0;
    }

    @Override
    public void setCurrentIndex(int currentIndex) {
        super.setCurrentIndex(currentIndex);
        this.sunXungCurrentIndex = currentIndex;
    }

    @Override
    public int[] createCells(Drawing patriarch, int index, Object t,
                             List<ExcelExportEntity> excelParams, Sheet sheet, Workbook workbook,
                             short rowHeight, int cellNum) {
        try {
            ExcelExportEntity entity;
            Row row = sheet.getRow(index) == null ? sheet.createRow(index) : sheet.getRow(index);
            if (rowHeight != -1) {
                row.setHeight(rowHeight);
            }
            int maxHeight = 1, listMaxHeight = 1;
            // 合并需要合并的单元格
            int margeCellNum = cellNum;
            int indexKey = createIndexCell(row, index, excelParams.get(0));
            cellNum += indexKey;
            for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
                entity = excelParams.get(k);
                if (entity.getList() != null) {
                    Collection<?> list = getListCellValue(entity, t);
                    int listIndex = 0, tmpListHeight = 0;
                    if (list != null && list.size() > 0) {
                        int tempCellNum = 0;
                        for (Object obj : list) {
                            int[] temp = createCells(patriarch, index + listIndex, obj, entity.getList(), sheet, workbook, rowHeight, cellNum);
                            tempCellNum = temp[1];
                            tmpListHeight += temp[0];
                            listIndex++;
                        }
                        cellNum = tempCellNum;
                        listMaxHeight = Math.max(listMaxHeight, tmpListHeight);
                    }
                } else {
                    Object value = getCellValue(entity, t);

                    Cell cell = null;
                    if (entity.getType() == BaseEntityTypeConstants.STRING_TYPE) {
                        createStringCell(row, cellNum++, value == null ? "" : value.toString(),
                                index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity),
                                entity);

                        cell = row.getCell(cellNum - 1);
                        if (entity.isHyperlink()) {
                            cell.setHyperlink(dataHandler.getHyperlink(
                                    row.getSheet().getWorkbook().getCreationHelper(), t,
                                    entity.getName(), value));
                        }
                    } else if (entity.getType() == BaseEntityTypeConstants.DOUBLE_TYPE) {
                        createDoubleCell(row, cellNum++, value == null ? "" : value.toString(),
                                index % 2 == 0 ? getStyles(false, entity) : getStyles(true, entity),
                                entity);

                        cell = row.getCell(cellNum - 1);
                        if (entity.isHyperlink()) {
                            cell.setHyperlink(dataHandler.getHyperlink(
                                    row.getSheet().getWorkbook().getCreationHelper(), t,
                                    entity.getName(), value));
                        }
                    } else {
                        createImageCell(patriarch, entity, row, cellNum++,
                                value == null ? "" : value.toString(), t);
                    }

                    if (cell != null) {
                        cell.setCellStyle(getStyles(cell, row.getRowNum(), entity, t, value));
                    }
                }
            }
            maxHeight += listMaxHeight - 1;
            if (indexKey == 1 && excelParams.get(1).isNeedMerge()) {
                excelParams.get(0).setNeedMerge(true);
            }
            for (int k = indexKey, paramSize = excelParams.size(); k < paramSize; k++) {
                entity = excelParams.get(k);
                if (entity.getList() != null) {
                    margeCellNum += entity.getList().size();
                } else if (entity.isNeedMerge() && maxHeight > 1) {
                    for (int i = index + 1; i < index + maxHeight; i++) {
                        Cell cell = sheet.getRow(i).createCell(margeCellNum);
                        sheet.getRow(i).getCell(margeCellNum).setCellStyle(getStyles(false, entity));
                    }
                    PoiMergeCellUtil.addMergedRegion(sheet, index, index + maxHeight - 1, margeCellNum, margeCellNum);
                    margeCellNum++;
                }
            }
            return new int[]{maxHeight, cellNum};
        } catch (Exception e) {
            LOGGER.error("excel cell export error ,data is :{}", ReflectionToStringBuilder.toString(t));
            LOGGER.error(e.getMessage(), e);
            throw new ExcelExportException(ExcelExportEnum.EXPORT_ERROR, e);
        }
    }

    protected CellStyle getStyles(Cell cell, int dataRow, ExcelExportEntity entity, Object obj, Object data) {
        return excelExportStyler.getStyles(cell, dataRow, entity, obj, data);
    }
}
