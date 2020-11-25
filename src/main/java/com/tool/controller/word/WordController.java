package com.tool.controller.word;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/word")
public class WordController {

    public static void main(String[] args) throws Exception {
        wordToPdf("C:/Users/Yegua/Downloads/6.4 技术服务合同.doc","C:/Users/Yegua/Downloads/6.4 技术服务合同.pdf");
    }

    private static void wordToPdf(String sourcerFile,String targetFile){

        try {
            long old = System.currentTimeMillis();
            File file = new File(targetFile);  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(sourcerFile);                    //sourcerFile是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
