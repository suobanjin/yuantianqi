package zzuli.zw.weather.utils;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @ClassName: TextUtils
 * @date: 2020/10/8 16:55
 * @author 索半斤
 * @Description: 统一对文本样式等信息进行修改
 */
public class TextUtils {

    public static void setText(Text text, int FontSize){
        text.setStyle("-fx-font-size: "+FontSize+"px");
    }

    public static void setText(Text text,int fontSize,String fontColor,String fontFamily){
        text.setFont(Font.font(fontFamily));
        text.setFill(Paint.valueOf(fontColor));
        text.setStyle("-fx-font-size: "+fontSize+"px");
    }

    public static void setText(Text text,int fontSize,String fontFamily){
        text.setFont(Font.font(fontFamily));
        text.setStyle("-fx-font-size: "+fontSize+"px");
    }

    public static Text setText(int FontSize,String content){
        Text text = new Text(content);
        text.setStyle("-fx-font-size: "+FontSize+"px");
        return text;
    }

    public static Text setText(int fontSize,String fontColor,String fontFamily,String content){
        Text text = new Text(content);
        text.setFont(Font.font(fontFamily));
        text.setFill(Paint.valueOf(fontColor));
        text.setStyle("-fx-font-size: "+fontSize+"px");
        return text;
    }

    public static Text setText(String content,double opacity){
        Text text = new Text(content);
        text.setOpacity(opacity);
        return text;
    }

}
