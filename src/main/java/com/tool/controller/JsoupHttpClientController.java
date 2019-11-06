package com.tool.controller;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

/**
 * [Java爬虫] 使用 HtmlUnit + Xpath 模拟点击、动态获取信息
 * https://blog.csdn.net/larger5/article/details/79683048
 */
public class JsoupHttpClientController {

    public static void main(String[] args) throws Exception {
        taxMP();
    }

    /**
     * https://www.so.com/
     * @throws Exception
     */
    private static void so() throws Exception {
        // 创建webclient
        WebClient webClient = new WebClient();
        // 取消 JS 支持
        webClient.getOptions().setJavaScriptEnabled(false);
        // 取消 CSS 支持
        webClient.getOptions().setCssEnabled(false);
        // 获取指定网页实体
        HtmlPage page = (HtmlPage) webClient.getPage("https://www.so.com/");
        // 获取搜索输入框
        HtmlInput input = (HtmlInput) page.getHtmlElementById("input");
        // 往输入框 “填值”
        input.setValueAttribute("larger5");
        // 获取搜索按钮
        HtmlInput btn = (HtmlInput) page.getHtmlElementById("search-button");
        // “点击” 搜索
        HtmlPage page2 = btn.click();
        // 选择元素
        List<HtmlElement> spanList=page2.getByXPath("//h3[@class='res-title']/a");
        for(int i=0;i<spanList.size();i++) {
            // 输出新页面的文本
            System.out.println(i+1+"、"+spanList.get(i).asText());
        }
    }

    /**
     * http://119.3.230.212/TaxMP/submitAuthInfo.html
     * @throws Exception
     */
    private static void taxMP() throws Exception {

        StringBuffer url = new StringBuffer();
        url.append("http://119.3.230.212/TaxMP/submitAuthInfo.html");

        url.append("?merId=89900000046015644730");
        url.append("&cardNo=6211235289249397");
        url.append("&name=%E6%B5%8B%E8%AF%95");
        url.append("&mobile=18163782023");
        url.append("&idCard=6257038447373579#");

        System.out.println("url:=====" + url);

        // 创建webclient
        WebClient webClient = new WebClient();
        // 启用 JS 支持
        webClient.getOptions().setJavaScriptEnabled(true);
        // 取消 CSS 支持
        webClient.getOptions().setCssEnabled(false);
        // 获取指定网页实体
        HtmlPage page = (HtmlPage) webClient.getPage(url.toString());
        // 搜索勾选框
        HtmlInput checkbox = (HtmlInput) page.getHtmlElementById("weuiAgree");
        checkbox.setChecked(true);

        // 获取签约按钮
        HtmlAnchor anchor = (HtmlAnchor) page.getByXPath("//*[@id=\"signBtn\"]").get(0);
        HtmlPage page2 = anchor.click();

        // 输出页面
        System.out.println("====================================================================================================");
        System.out.println("====================================================================================================");
        System.out.println(page2.asXml());
    }

}
