package zzuli.zw.weather.utils;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.*;

/**
 * 使用Jsoup简单爬取ip信息
 */
public class IPUtils {
    private static Logger logger = Logger.getLogger(IPUtils.class);
    public static String getIp() throws UnknownHostException {
        try {
            // 打开连接
            Document doc = Jsoup.connect("http://chaipip.com/").get();
            Elements elem = doc.select("#ip");
            return elem.attr("value");
        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) throws UnknownHostException {
        String ip = IPUtils.getIp();
        System.out.println(ip);
    }
}
