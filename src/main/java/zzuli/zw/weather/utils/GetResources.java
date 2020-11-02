package zzuli.zw.weather.utils;
import java.io.InputStream;
import java.util.Objects;

public class GetResources {

    /**
     * 输入文件名称得到一个输入流
     * @param fileName 文件名称
     * @return InputStream
     */
    public static InputStream getResources(String fileName){
        if (isEmpty(fileName)){
            return null;
        }
        return GetResources.class.getClassLoader().getResourceAsStream(fileName);
    }

    /**
     * 获取图片的路径
     * @param fileName 文件名称
     * @return     String
     */
    public static String getResourcePath(String fileName){
        if (isEmpty(fileName)){
            return null;
        }
        return Objects.requireNonNull(GetResources.class.getClassLoader().getResource(fileName)).toExternalForm();
    }

    /**
     * 获取图片输入流
     * @param imageName    图片的名称
     * @return    InputStream
     */
    public static InputStream getImages(String imageName){
        if (isEmpty(imageName)){
            return null;
        }
        return GetResources.class.getClassLoader().getResourceAsStream("images/"+imageName);
    }

    /**
     * 获取图片路径
     * @param imageName 图片的名称
     * @return String 路径
     */
    public static String getImagesPath(String imageName){
        if (isEmpty(imageName)){
            return null;
        }
        if (GetResources.class.getClassLoader().getResource("images/"+imageName) == null){
            return Objects.requireNonNull(GetResources.class.getClassLoader().getResource("images/weatherIcon/defaultWeather.png")).toExternalForm();
        }
        return Objects.requireNonNull(GetResources.class.getClassLoader().getResource("images/" + imageName)).toExternalForm();
    }

    /**
     * 判断字符串是否为空
     * @param string 任意的字符串
     * @return boolean
     */
    public static boolean isEmpty(String string){
        return string == null || string.trim().isEmpty();
    }

}
