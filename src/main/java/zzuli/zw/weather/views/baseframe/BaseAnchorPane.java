package zzuli.zw.weather.views.baseframe;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;


public class BaseAnchorPane extends AnchorPane {
    /**
     * 设置背景
     * @param Color 背景颜色
     * @param radius  圆角
     * @param insets  inset
     */
    public void setBackground(String Color, int radius, Insets insets){
        setBackground(new Background(new BackgroundFill(Paint.valueOf(Color),new CornerRadii(radius),insets)));
    }
    public void setBackground(String Color,int radius,int leftRightTopBottom){
        setBackground(new Background(new BackgroundFill(Paint.valueOf(Color),new CornerRadii(radius),new Insets(leftRightTopBottom))));
    }
}
