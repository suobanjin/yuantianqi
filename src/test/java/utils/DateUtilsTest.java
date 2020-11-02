package utils;

import org.junit.Test;
import zzuli.zw.weather.utils.DateUtils;

public class DateUtilsTest {

    @Test
    public void test01(){
        boolean compare = DateUtils.compare("23:09");
        System.out.println(compare);
    }
}
