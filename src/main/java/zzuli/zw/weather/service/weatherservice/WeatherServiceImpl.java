package zzuli.zw.weather.service.weatherservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import sun.awt.SunHints;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.weatherservice.interfaces.Observer;
import zzuli.zw.weather.service.weatherservice.interfaces.Subject;
import zzuli.zw.weather.service.weatherservice.interfaces.WeatherService;
import zzuli.zw.weather.utils.LocationUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImpl implements WeatherService, Subject {
    private final static String SERVICE = "http://t.weather.itboy.net/api/weather/city/";
    List<Observer> observers;
    public WeatherServiceImpl(){
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    enum Singleton{
        WeatherService;
        private WeatherServiceImpl weatherServiceImpl;
        Singleton(){
            weatherServiceImpl = new WeatherServiceImpl();
        }
        public WeatherServiceImpl getInstance(){
            return weatherServiceImpl;
        }

    }

    public static WeatherServiceImpl getInstance(){
        return Singleton.WeatherService.getInstance();
    }
    /**
     * 通过城市名称获取天气信息
     * @param cityName 城市名称
     * @return Weather
     */
    @Override
    public Weather weatherByCityName(String cityName) throws IOException {
        if (cityName == null || cityName.trim().isEmpty()) {
            return null;
        }
        cityName = checkCityName(cityName);
        String cityId = BeanFactory.getProperties().getProperty(cityName);
        return weatherByCityId(cityId);
    }

    /**
     * 通过当前用户的ip地址获取用户的大致位置
     * @return Weather
     */
    @Override
    public Weather WeatherDefault() throws IOException {
        String cityName = LocationUtils.getAddrName();
        cityName = checkCityName(cityName);
        String cityId = BeanFactory.getProperties().getProperty(cityName);
        return weatherByCityId(cityId);
    }

    /**
     * 对城市名称进行处理
     * @param cityName 城市名称
     * @return String
     */
    private String checkCityName(String cityName) {
        if (cityName.length() < 3) {
            return cityName;
        }
        int index = cityName.lastIndexOf("市");
        if (index != -1) {
            cityName = cityName.substring(0, index);
        }
        int i = cityName.lastIndexOf("县");
        if (i != -1) {
            cityName = cityName.substring(0, index);
        }
        return cityName;
    }

    /**
     * 通过id获取天气信息
     *
     * @param cityId 城市的代码
     * @return Weather
     */
    @Override
    public Weather weatherByCityId(String cityId) throws IOException {
        URL url;
        InputStream inputStream = null;
        try {
            if (cityId == null || cityId.trim().isEmpty()) {
                return null;
            }
            url = new URL(SERVICE + cityId);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            inputStream = connection.getInputStream();
            String string = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(string, Weather.class);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
