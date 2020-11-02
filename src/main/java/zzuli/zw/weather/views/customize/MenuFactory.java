package zzuli.zw.weather.views.customize;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import zzuli.zw.weather.utils.GetResources;

import java.util.Objects;

public class MenuFactory {
    private static ContextMenu menu = new ContextMenu();

    static{
        MenuItem day = new MenuItem();
        MenuItem week = new MenuItem();
        MenuItem analyse = new MenuItem();
        MenuItem about = new MenuItem();
        setMenuItem(day,"dayweather.png","今日天气");
        setMenuItem(week,"moreweathers.png","15天预测");
        setMenuItem(analyse,"analyseweathers.png","气象分析");
        setMenuItem(about,"about.png","关于");
        menu.getItems().addAll(day,week,analyse,about);
        menu.getStyleClass().add("context-menu");
    }
    public static ContextMenu getMenu(){
        return menu;
    }


    private static void setMenuItem(MenuItem menuItem,String imageName,String item){
        ImageView view = new ImageView(Objects.requireNonNull(GetResources.getImagesPath(imageName)));
        view.setFitHeight(25);
        view.setFitWidth(25);
        menuItem.setGraphic(view);
        menuItem.setText(item);
    }
}
