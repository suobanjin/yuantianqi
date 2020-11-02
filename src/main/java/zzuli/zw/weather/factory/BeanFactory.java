package zzuli.zw.weather.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.utils.GetResources;
import zzuli.zw.weather.views.customize.WeekGridPane;
import zzuli.zw.weather.views.manageui.ManageButton;
import zzuli.zw.weather.views.manageui.ManageNode;
import zzuli.zw.weather.views.manageui.ManageUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * 容器
 */
public class BeanFactory {
    private static ObjectMapper objectMapper = new ObjectMapper() ;
    private static Properties properties;
    private  static Logger logger = Logger.getLogger(BeanFactory.class);
    private static ManageUI<String,Stage> manageUI = new ManageUI<>();
    private static ManageButton<String, ? super Button> manageButton = new ManageButton<>();
    private static ManageNode<String,Object> manageNode = new ManageNode<>();
    private static Document document = null;
    private static List<String> province = new ArrayList<>();
    private static Map<String,String> cityInfo = new HashMap<>();
    private static Map<String, Weather> cityWeather = new HashMap<>();
    private static Properties application;
    private static final String APPLICATION_CONFIG = "application.properties";
    private static WeekGridPane weekGridPane;
    static{
        properties = new Properties();
        application = new Properties();
        BufferedReader fileReader = null;
        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("cityCodes.properties");
        if (inputStream != null) {
            fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        }
        try {
            properties.load(fileReader);
            inputStream = BeanFactory.class.getClassLoader().getResourceAsStream(APPLICATION_CONFIG);
            if (inputStream != null) {
                fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            }
            application.load(fileReader);
        } catch (IOException e) {
            logger.error(e);
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
    static{
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(GetResources.getResourcePath("chinaCityInformation.xml"));
            List<Attribute> list = document.selectNodes("//province/@name");
            province.add("选择省份");
            for (Attribute attribute : list) {
                province.add(attribute.getValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public static Properties getApplication(){
        if (application == null || application.size() == 0) throw new NullPointerException("应用程序配置文件没有成功加载");
        return application;
    }
    public static Document getDocument(){
        return document;
    }
    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }
    public static Properties getProperties(){
        return properties;
    }
    public static ManageUI<String,Stage> getManageUI(){
        return manageUI;
    }
    public static ManageButton<String,? super Button> getManageButton(){
        return manageButton;
    }
    public static ManageNode<String,Object> getManageNode(){return manageNode;}

    public static List<String> getProvinces() {
        return province;
    }
    public static Map<String,String> getCityInfo(){
        return cityInfo;
    }

    public static Map<String, Weather> getCityWeather() {
        return cityWeather;
    }

}
