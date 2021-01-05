package zzuli.zw.weather.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import zzuli.zw.weather.factory.BeanFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 通过用户的ip获取城市名称，只是简单的获取（）
 */
public class LocationUtils {

    /**
     * 获取json串
     * @param url  URL地址
     * @return   json
     * @throws IOException IO流异常
     */
    public static String readJsonFromUrl(String url) throws IOException{
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String json = IOUtils.toString(rd);
            return DecodeCharacter.decodeUnicode(json);
        }
    }

    /**
     * 获取城市名称
     * @return String
     * @throws IOException IO流异常
     */
    public static String getAddrName() throws IOException{
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        String json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=3K3SjhczZL0hfGNZIHWGXvzkzGBgtmqo&ip=");
        ObjectMapper objectMapper = BeanFactory.getObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode contentNode = rootNode.path("content");
        JsonNode addressDetailNode = contentNode.path("address_detail");
        return addressDetailNode.path("city").asText();
    }

    public static void main(String[] args) throws IOException {
        String addrName = LocationUtils.getAddrName();
        System.out.println(addrName);
    }

}
