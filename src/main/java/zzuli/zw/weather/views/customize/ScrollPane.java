package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import zzuli.zw.weather.factory.BeanFactory;

public class ScrollPane extends javafx.scene.control.ScrollPane {
    public ScrollPane(double width,double height){
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color: #ffffff00");
        //this.setContent(new WeekGridPane());
        this.setContent((WeekGridPane)BeanFactory.getManageNode().get("weekGridPane"));
        this.setPadding(new Insets(0,0,0,-190));
    }
}
