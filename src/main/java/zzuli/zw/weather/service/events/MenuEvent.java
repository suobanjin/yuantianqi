package zzuli.zw.weather.service.events;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.views.customize.*;

/**
 * @ClassName: MenuEvent
 * @date: 2020/10/8 13:59
 * @author 索半斤
 * @Description: 初始化菜单并为菜单项初始化事件
 */
public class MenuEvent {
    public MenuEvent(){
        ContextMenu menu = MenuFactory.getMenu();
        MenuItem weekWeather = menu.getItems().get(1);
        weekWeather.setOnAction(event -> {
            MainStackPane stackPane = (MainStackPane) BeanFactory.getManageNode().get("mainStackPane");
            stackPane.getChildren().clear();
            stackPane.getChildren().add(new ScrollPane(360,480));
        });
        MenuItem main = menu.getItems().get(0);
        main.setOnAction(event -> {
            MainStackPane stackPane = (MainStackPane) BeanFactory.getManageNode().get("mainStackPane");
            stackPane.getChildren().clear();
            stackPane.getChildren().add((MainVBox)BeanFactory.getManageNode().get("MainVBox"));
        });
        MenuItem about = menu.getItems().get(3);
        about.setOnAction(event -> {
            MainStackPane stackPane = (MainStackPane) BeanFactory.getManageNode().get("mainStackPane");
            stackPane.getChildren().clear();
            stackPane.getChildren().add(new HelpVBox());
        });
        MenuItem analyse = menu.getItems().get(2);
        analyse.setOnAction(event -> {
            MainStackPane stackPane = (MainStackPane) BeanFactory.getManageNode().get("mainStackPane");
            stackPane.getChildren().clear();
            stackPane.getChildren().add((ChartVBox)BeanFactory.getManageNode().get("chartVBox"));
        });
    }
}
