package utils;

import org.junit.Test;
import zzuli.zw.weather.utils.IPUtils;

import java.net.UnknownHostException;

public class IPUtilsTest {
    @Test
    public void fun1() throws UnknownHostException {
        String ip = IPUtils.getIp();
        System.out.println(ip);
    }
}
