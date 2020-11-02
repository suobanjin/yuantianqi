package service;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.junit.Test;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.utils.Dom4jUtils;

import java.util.List;

public class Dom4jTest {
    @Test
    public void fun1() {
        Document document = BeanFactory.getDocument();
        List<Attribute> list = document.selectNodes("//province/@name");
       /* for (Attribute attribute : list) {
            System.out.println(attribute.getValue());
        }*/
        /*List<Attribute> attributes = document.selectNodes("//province[@name='河南']//city/@name");
        for (Attribute node : attributes) {
            System.out.println(node.getValue());
        }*/
        String str = Dom4jUtils.getWeatherCode("民权");
        System.out.println(str);
    }
}
