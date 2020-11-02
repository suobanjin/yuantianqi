package zzuli.zw.weather.views.customize;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.utils.ImageViewUtils;
import zzuli.zw.weather.utils.TextUtils;

public class AlertErrorPane extends VBox {
    private HBox hBox;
    public AlertErrorPane(){
        hBox = new HBox();
    }

    public void setErrorMessage(String errorMessage){
        AnchorPane anchorPane = (AnchorPane) BeanFactory.getManageNode().get("alertApPane");
        anchorPane.getChildren().remove(1);
        anchorPane.getChildren().add(this);
        AnchorPane.setTopAnchor(this,100.0);
        AnchorPane.setLeftAnchor(this,100.0);
        Text text = TextUtils.setText(22,errorMessage);
        ImageView errorImage = ImageViewUtils.setImage(40,"error.png");
        hBox.getChildren().addAll(errorImage,text);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        this.getChildren().add(hBox);
    }
}
