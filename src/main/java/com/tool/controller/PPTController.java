package com.tool.controller;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * PPT
 */
public class PPTController {

    public static void main(String[] args) throws Exception {
        ppt();
    }

    /**
     * 动态生成PPT
     * @throws IOException
     */
    private static void ppt() throws IOException {
        //  步骤1 :通过实例化 XMLSlideShow 类创建一个空的演示文稿，如下所示:
        XMLSlideShow ppt = new XMLSlideShow();
        String slideWidth = "25.36";
        String slideHeight = "19.01";
        //  设置幻灯片大小
        ppt.setPageSize(new Dimension(Double.valueOf(Double.valueOf(slideWidth) / 3.53 * 100).intValue(), Double.valueOf(Double.valueOf(slideHeight) / 3.53 * 100).intValue()));
        //  步骤2 :使用 getSlideMasters()方法获取幻灯片主题列表。 此后，使用索引选择所需的幻灯片母带，如下所示:
        XSLFSlideMaster slideMaster = ppt.getSlideMasters().get(0);//可设置母版的
        //  这里我们得到的默认幻灯片母版是在幻灯片主数据的第0位置。
        byte[] bt2 = FileUtils.readFileToByteArray(new File("C:\\usr\\local\\yth\\file\\20200421110010.png"));
        XSLFPictureData idx2 = ppt.addPicture(bt2, XSLFPictureData.PictureType.PNG);
        XSLFPictureShape picture = slideMaster.createPicture(idx2);//设置母版
        picture.setAnchor(new Rectangle(0, 0, ppt.getPageSize().width, ppt.getPageSize().height)); //设置母版大小
        //  创建幻灯片
        XSLFSlide slide1 = ppt.createSlide();

        String x = "3.45";  //水平位置
        String y = "12.70"; //垂直位置
        String w = "12.45"; //宽度
        String h = "0.50";  //高度
        XSLFTextBox textBox = slide1.createTextBox();
        textBox.setAnchor(new Rectangle2D.Double(Double.valueOf(x) / 3.53 * 100, Double.valueOf(y) / 3.53 * 100, Double.valueOf(w) / 3.53 * 100, Double.valueOf(h) / 3.53 * 100));
        XSLFTextRun textRun = textBox.setText("实际发布:10");
        textRun.setFontFamily("宋体");
        textRun.setFontSize(16.0);
        textRun.setBold(true); //设置粗体状态

        String x2 = "2.96";  //水平位置
        String y2 = "13.91"; //垂直位置
        String w2 = "10.56"; //宽度
        String h2 = "0.50";  //高度

        XSLFTextBox textBox2 = slide1.createTextBox();
        textBox2.setAnchor(new Rectangle2D.Double(Double.valueOf(x2) / 3.53 * 100, Double.valueOf(y2) / 3.53 * 100, Double.valueOf(w2) / 3.53 * 100, Double.valueOf(h2) / 3.53 * 100));
        /** 生成一个新的文本段落 **/
        XSLFTextParagraph xslfTextRuns = textBox2.addNewTextParagraph();
        /** 添加新的文本 **/
        XSLFTextRun xslfTextRun = xslfTextRuns.addNewTextRun();
        /** 添加新的文本 **/
        xslfTextRun.setText("福海信息港");
        /** 设置字体类型 **/
        xslfTextRun.setFontFamily("宋体");
        /** 设置字体大小 **/
        xslfTextRun.setFontSize(16.0);

        ppt.write(new FileOutputStream("C:\\usr\\local\\yth\\file\\ppt2.pptx"));
    }

}
