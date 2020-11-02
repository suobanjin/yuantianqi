import org.apache.commons.io.IOUtils;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.utils.IPUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Test {
    @org.junit.Test
    public void fun1() throws IOException {
        //http://whois.pconline.com.cn/
        URL url = new URL("http://whois.pconline.com.cn?ip=10.84.143.2&json=true/");
        InputStream inputStream = url.openStream();
        String s = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        System.out.println(s);
    }

    @org.junit.Test
    public void fun2(){
        Properties o = BeanFactory.getProperties();
        System.out.println(o.get("民权"));
        System.out.println(o);
    }

    @org.junit.Test
    public void fun3(){
        String str = "商丘市";
        int index = str.lastIndexOf("市");
        str = str.substring(0,index);
        System.out.println(str+" "+index);
    }

    @org.junit.Test
    public void fun4(){
        WeatherServiceImpl weatherServiceImpl = new WeatherServiceImpl();
        //System.out.println(weatherService.WeatherDefault());
    }
    @org.junit.Test
    public void fun5(){
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("2");
        list.add("3");
        list.add("4");
        list.sort(String::compareToIgnoreCase);
        list.forEach(System.out::println);
    }

    @org.junit.Test
    public void fun6(){
        try {
            Class<?> clazz = Class.forName("zzuli.zw.weather.domain.Weather");
            Weather weather = (Weather)clazz.newInstance();
            weather.setDate("2019");
            System.out.println(weather.getDate());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void fun7() throws UnknownHostException {
        String ip = IPUtils.getIp();
        System.out.println(ip);
    }

}
