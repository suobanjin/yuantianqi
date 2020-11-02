package zzuli.zw.weather.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import zzuli.zw.weather.domain.City;
import zzuli.zw.weather.factory.BeanFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class JsonToProperties {
    /**
     * 将json转换成properties
     * @param jsonName json路径
     * @param propertiesName     properties路径
     */
    public static void jsonToProperties(String jsonName, String propertiesName) {
        InputStream jsonStream = null;
        FileWriter fileWriter = null;
        try {
            //从类路径获取输入流
            jsonStream = Objects.requireNonNull(JsonToProperties.class.getClassLoader().getResourceAsStream(jsonName));
            //通过工厂获得json解析器
            ObjectMapper objectMapper = BeanFactory.getObjectMapper();
            //将json映射为实体类
            City[] cities = objectMapper.readValue(jsonStream, City[].class);
            Properties properties = new Properties();
            for (City city : cities) {
                properties.setProperty(city.getCityZh(), city.getId());
            }
            //回写
            fileWriter = new FileWriter(propertiesName);
            properties.store(fileWriter, "cityCode");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (jsonStream != null ) {
                    jsonStream.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        jsonToProperties("1.json", "cityCode.properties");
    }
}
