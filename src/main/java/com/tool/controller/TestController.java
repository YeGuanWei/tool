package com.tool.controller;

public class TestController {

    public static void main(String[] args) throws Exception {
        String inputFile = "C:/usr/local/yth/file/test.docx";
        String outputFile = "C:/usr/local/yth/file/outTest.docx";

        //加载示例文档
        Document document = new Document(inputFile);

        //删除第二条批注
        document.getComments().removeAt(1);

        //保存文档
        document.saveToFile(outputFile, FileFormat.Docx);

    }

}
