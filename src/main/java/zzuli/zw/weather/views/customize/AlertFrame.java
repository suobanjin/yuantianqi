package zzuli.zw.weather.views.customize;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.views.baseframe.BaseAnchorPane;
import zzuli.zw.weather.views.baseframe.BaseScene;
import zzuli.zw.weather.views.baseframe.BaseStage;

public class AlertFrame extends BaseStage {
    public AlertFrame(){
        this.setSize(420,230);
        StackPane stackPane = new StackPane();
        BaseScene scene = new BaseScene(stackPane,this);
        BaseAnchorPane ap = new BaseAnchorPane();
        stackPane.setStyle("-fx-background-color: white;-fx-background-radius: 5px");
        AlertTop top = new AlertTop(220,350);
        ap.getChildren().add(top);
        ChoicePane choicePane = new ChoicePane();
        //ChoicePaneStage choicePane = new ChoicePaneStage();
        AnchorPane.setTopAnchor(choicePane,50.0);
        AnchorPane.setLeftAnchor(choicePane,10.0);
        ap.getChildren().add(choicePane);
        BeanFactory.getManageNode().put("alertApPane",ap);
        stackPane.getChildren().add(ap);
        scene.getStyles("css/comboBox.css");
        BeanFactory.getManageUI().put("alertStage",this);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
    }

}
