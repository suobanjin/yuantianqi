package zzuli.zw.weather.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * @ClassName: WeatherUtils
 * @date: 2020/10/8 16:56
 * @author 索半斤
 * @Description: 对天气类型进行检查，如果查询不到相应的图片信息，设置默认天气
 */
public class WeatherUtils {

    public static ImageView checkAndSetting(String weatherType){
        ImageView view = ImageViewUtils.setImage(35,"weatherIcon/"+weatherType+".png");
        if (view == null){
            view = ImageViewUtils.setImage(35,"weatherIcon/defaultWeather.png");
        }
        return view;
    }

    public static void checkAndSetting(ImageView weatherImage, String weatherType){
        String imagePath = GetResources.getImagesPath("weatherIcon/" + weatherType + ".png");
        Image image;
        if (imagePath == null){
            image = new Image("images/weatherIcon/defaultWeather.png");
        }else{
            image = new Image(imagePath);
        }
        weatherImage.setImage(image);
    }

    public static void setWeatherImage(ImageView weatherImage,String weatherType){
        weatherImage.setImage(new Image(Objects.requireNonNull(GetResources.getImagesPath("images/weatherIcon/" + weatherType + ".png"))));
    }

}
