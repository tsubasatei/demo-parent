package com.xt.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class Dom4jTest {

    /*
     * dom4j 获取 Document 对象
     */
    @Test
    public void test () throws Exception{
        // 要创建一个Document 对象，需要我们先创建一个SAXReader 对象
        SAXReader reader = new SAXReader();
        // 这个对象用于读取xml 文件，然后返回一个Document。
        Document document = reader.read("05_xml/src/books.xml");
        System.out.println(document);
    }

    /*
     * 读取xml 文件中的内容
     */
    @Test
    public void readXML () throws Exception{
        // 需要分四步操作:
        // 第一步，通过创建SAXReader 对象。来读取xml 文件，获取Document 对象
        // 第二步，通过Document 对象。拿到XML 的根元素对象
        // 第三步，通过根元素对象。获取所有的book 标签对象
        // 第四小，遍历每个book 标签对象。然后获取到book 标签对象内的每一个元素，再通过getText() 方法拿到起始标签和结束标签之间的文本内容

        SAXReader reader = new SAXReader();
        Document document = reader.read("05_xml/src/books.xml");
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement.asXML());
        // Element.elements(标签名)它可以拿到当前元素下的指定的子元素的集合
        List<Element> elements = rootElement.elements("book");
        for (Element element : elements) {
//            System.out.println(element.asXML());
            String name = element.element("name").getText();
            String price = element.element("price").getText();
            String author = element.elementText("price");
            // 属性
            String sn = element.attributeValue("sn");
            System.out.println(new Book(sn, name, new BigDecimal(price), author));
        }
    }
}
