package mainframe;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.views.baseframe.BaseAnchorPane;
import zzuli.zw.weather.views.baseframe.BaseScene;
import zzuli.zw.weather.views.baseframe.BaseStage;
import zzuli.zw.weather.views.customize.AlertTop;
import zzuli.zw.weather.views.customize.ChoicePane;

public class AlertFrame extends BaseStage {
    public AlertFrame(){
        this.setSize(350,200);
        StackPane stackPane = new StackPane();
        BaseScene scene = new BaseScene(stackPane,this);
        BaseAnchorPane ap = new BaseAnchorPane();
        stackPane.setStyle("-fx-background-color: white;-fx-background-radius: 5px");
        AlertTop top = new AlertTop(220,350);
        ap.getChildren().add(top);
        ChoicePane choicePane = new ChoicePane();
        AnchorPane.setTopAnchor(choicePane,50.0);
        AnchorPane.setLeftAnchor(choicePane,10.0);
        ap.getChildren().add(choicePane);
        stackPane.getChildren().add(ap);
        BeanFactory.getManageUI().put("alertStage",this);
        this.setScene(scene);
    }
}
