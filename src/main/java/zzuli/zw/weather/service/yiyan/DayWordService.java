package zzuli.zw.weather.service.yiyan;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import zzuli.zw.weather.domain.Word;
import zzuli.zw.weather.factory.BeanFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class DayWordService {
    private final static String SERVICE = "https://v1.hitokoto.cn/?c=d&c=e&c=g&c=j&c=k&c=l&encode=json&charset=utf-8";
    private Logger logger = Logger.getLogger(this.getClass());
    enum Singleton{
        dayWordService;
        private DayWordService service;
        Singleton(){
            service = new DayWordService();
        }
        public DayWordService getInstance(){
            return service;
        }
    }
    public static DayWordService getInstance(){
        return Singleton.dayWordService.getInstance();
    }
    public String getWord(){
        try{
            URL url = new URL(SERVICE);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);
            InputStream inputStream = connection.getInputStream();
            String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            JsonNode rootNode = BeanFactory.getObjectMapper().readTree(json);
            String hitokoto = rootNode.path("hitokoto").asText();
            String from = rootNode.path("from").asText();
            return new Word(hitokoto,from).toString();
        } catch (IOException e) {
            String string = "开启一天好心情！";
            String from = "猿天气";
            Word word = new Word(string, from);
            logger.error("一言错误信息---->"+e);
            return word.toString();
        }
    }
}
