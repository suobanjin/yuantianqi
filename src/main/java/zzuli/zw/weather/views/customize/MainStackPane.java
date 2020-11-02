package zzuli.zw.weather.views.customize;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import zzuli.zw.weather.factory.BeanFactory;

public class MainStackPane extends StackPane {
    public MainStackPane(){
        if (BeanFactory.getManageNode().get("mainVBox") != null){
            this.getChildren().add((VBox)BeanFactory.getManageNode().get("mainVBox"));
        }else {
            MainVBox vBox = new MainVBox();
            BeanFactory.getManageNode().put("MainVBox",vBox);
            this.getChildren().add(vBox);
        }
        //this.setPrefSize(650,500);
        BeanFactory.getManageNode().put("mainStack",this);
    }
}
