package com.tool.controller.http;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tool.entity.JsoupHttpDTO;

import java.util.List;

/**
 * [Java爬虫] 使用 HtmlUnit + Xpath 模拟点击、动态获取信息
 * https://blog.csdn.net/larger5/article/details/79683048
 */
public class JsoupHttpClientController {

    public static void main(String[] args) throws Exception {
        JsoupHttpDTO dto = new JsoupHttpDTO();
        dto.setMerId("89900000046015644730");
        dto.setCardNo("6211235289249397");
        dto.setName("测试你");
        dto.setMobile("13456789001");
        dto.setIdCard("351828201612120823");

        taxMP(dto);
    }

    /**
     * https://www.so.com/
     *
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
        List<HtmlElement> spanList = page2.getByXPath("//h3[@class='res-title']/a");
        for (int i = 0; i < spanList.size(); i++) {
            // 输出新页面的文本
            System.out.println(i + 1 + "、" + spanList.get(i).asText());
        }
    }

    /**
     * http://119.3.230.212/TaxMP/sub.html
     *
     * @throws Exception
     */
    private static void taxMP(JsoupHttpDTO dto) throws Exception {

        Long date = System.currentTimeMillis();

        StringBuffer url = new StringBuffer();
        url.append("http://119.3.230.212/TaxMP/su.html");

        url.append("?merId=" + dto.getMerId());
        url.append("&cardNo=" + dto.getCardNo());
        url.append("&name=" + dto.getName());
        url.append("&mobile=" + dto.getMobile());
        url.append("&idCard=" + dto.getIdCard());

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

        System.out.println("url:=====" + url);
        System.out.println("本次请求耗时 : " + (System.currentTimeMillis() - date));
    }

}
