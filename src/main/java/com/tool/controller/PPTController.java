package com.tool.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PPT
 */
public class PPTController {

    public static void main(String[] args) throws Exception {
        getPPTByXml();
    }

    /**
     * 动态生成PPT
     *
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

    /**
     * 合并ppt
     *
     * @throws IOException
     */
    private static void mergePPT() throws IOException {

        // 导入待拼接的.pptx文件
        InputStream coveris = new FileInputStream(new File("C:\\usr\\local\\yth\\file\\001.pptx"));
        InputStream datais = new FileInputStream(new File("C:\\usr\\local\\yth\\file\\002.pptx"));

        // 待拼接PPT1
        XMLSlideShow coverppt = new XMLSlideShow(coveris);
        // 待拼接PPT2
        XMLSlideShow datappt = new XMLSlideShow(datais);
        // 此方法返回待拼接PPT3
        XMLSlideShow headppt = getPPT();


        for (XSLFSlide srcSlide : datappt.getSlides()) {
            coverppt.createSlide().importContent(srcSlide);
        }

        for (XSLFSlide srcSlide : headppt.getSlides()) {
            coverppt.createSlide().importContent(srcSlide);
        }


        // 将PPT3依次插入到PPT2的末尾，createSlide()是在末尾创建
        /*for (XSLFSlide srcSlide : headppt.getSlides()) {
            datappt.createSlide().importContent(srcSlide);
        }*/

        // 下面代码 是将当前月份的数值所对应ppt中的第几张 插入到PPT2的末尾
        /*List<XSLFSlide> coverlist = coverppt.getSlides();
        for (int i = 0; i < coverlist.size(); i++) {
            if (i == 4) {
                datappt.createSlide().importContent(coverlist.get(i));
            }
        }*/
        // 此时PPT1，PPT3都已插入到PPT2中，现在进行重新排序，  setSlideOrder(第几张PPT（slide）, 要去的位置（int）)；
        // datappt.setSlideOrder(datappt.getSlides().get(7), 0);
        // datappt.setSlideOrder(datappt.getSlides().get(5), 1);
        // datappt.setSlideOrder(datappt.getSlides().get(6), 2);

        // 以下为导出PPT的操作
        String filePath = "C:\\usr\\local\\yth\\file";
        filePath = filePath.replace("\\", "\\\\");

        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String path = dir.getPath();
        path = path.replace("\\", "\\\\");

        FileOutputStream out = new FileOutputStream(path + "\\004.pptx");
        coverppt.write(out);
        out.close();
    }

    public static XMLSlideShow getPPT() throws IOException {
        // 导入要修改的PPT
        InputStream is = new FileInputStream(new File("C:\\usr\\local\\yth\\file\\003.pptx"));
        XMLSlideShow ppt = new XMLSlideShow(is);
        java.util.List<XSLFSlide> Slides = ppt.getSlides();
        for (XSLFSlide slide : Slides) {
            java.util.List<XSLFShape> shapes = slide.getShapes();
            for (XSLFShape shape : shapes) {
                if (shape != null) {
                    if (shape instanceof AutoShape) {
                        try {
                            if (((AutoShape) shape).getText().contains("{ye}")) {
                                // 替换文字内容.用TextRun获取替换的文本来设置样式
                                TextRun rt = ((AutoShape) shape).setText(((AutoShape) shape).getText().replace("{ye}", "2018"));

                                rt.setFontColor(Color.red);
                                rt.setFontSize(13.5);
                                rt.setFontFamily("微软雅黑");

                            } else if (((AutoShape) shape).getText().contains("{mo}")) {
                                ((AutoShape) shape).setText(((AutoShape) shape).getText().replace("{mo}", "7"));
                            }
                            // System.out.println(((AutoShape)shape).getText());
                        } catch (Exception ex) {


                            ex.printStackTrace();
                        }
                        // }else {
                        // System.out.println("Process me: " +
                        // shape.getClass());
                    }
                }
            }
        }
        return ppt;
    }

    public static void getPPTByXml() throws IOException, TemplateException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("name","名字");
        map.put("id","可能是1吧");

        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File("C:\\usr\\local\\yth\\file"));

        File outFile = new File("C:\\usr\\local\\yth\\file\\123.xml");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);

        Template t = configuration.getTemplate("003.xml", "utf-8");
        t.process(map, out);

        out.close();
    }

}
