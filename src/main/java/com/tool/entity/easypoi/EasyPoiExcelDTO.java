package com.tool.entity.easypoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class EasyPoiExcelDTO {

    public static final String SUBJECT = "学科";

    @Excel(name = "班级名称", width = 25, orderNum = "1", mergeVertical = true, needMerge = true)
    private String className;

    @Excel(name = "学院名称", width = 25, orderNum = "2")
    private String userName;

    @Excel(name = "语文", width = 15, groupName = SUBJECT, orderNum = "3")
    private String chinese;

    @Excel(name = "数学", width = 15, groupName = SUBJECT, orderNum = "4")
    private String mathematics;

    @Excel(name = "英语", width = 15, groupName = SUBJECT, orderNum = "5")
    private String english;

}
