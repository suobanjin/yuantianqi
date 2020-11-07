package zzuli.zw.weather.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import zzuli.zw.weather.factory.BeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Dom4jUtils
 * @date: 2020/10/8 16:57
 * @author 索半斤
 * @Description: 根据具体业务场景对XML文件进行解析，使用了Dom4j依赖
 */
public class Dom4jUtils {
    //获取省份
    public static List<String> getCites(String provinceName){
        return getNodeList("//province[@name='"+provinceName+"']//city/@name");
    }

    //获取城市
    public static List<String> getCounty(String cityName){
        return getNodeList("//city[@name='"+cityName+"']//county/@name");
    }

    //获取节点列表
    @SuppressWarnings("unchecked")
    private static List<String> getNodeList(String regx){
        List<String> lists = new ArrayList<>();
        Document document = BeanFactory.getDocument();
        List<Attribute> attributes = document.selectNodes(regx);
        for (Attribute node : attributes) {
            lists.add(node.getValue());
        }
        return lists;
    }

    //获取城市代码
    public static String getWeatherCode(String countyName){
        Document document = BeanFactory.getDocument();
        Attribute attribute = (Attribute) document.selectSingleNode("//county[@name='" + countyName + "']/@weatherCode");
        return attribute.getValue();
    }

}
