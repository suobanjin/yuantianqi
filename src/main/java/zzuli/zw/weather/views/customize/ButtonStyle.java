package zzuli.zw.weather.views.customize;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import zzuli.zw.weather.utils.GetResources;

import java.util.Objects;

public class ButtonStyle {

    /**
     * 设置按钮的样式
     * @param button 按钮
     * @param buttonName  按钮的名称
     * @return Button
     */
    public Button setButton(Button button, String buttonName){
        button.setStyle("-fx-border-style: none;" +
                "-fx-background-color: #ffffff00");
        ImageView buttonView = new ImageView(Objects.requireNonNull(GetResources.getImagesPath(buttonName)));
        buttonView.setFitWidth(25);
        buttonView.setFitHeight(25);
        buttonView.maxWidth(25);
        buttonView.maxHeight(25);
        buttonView.minHeight(25);
        buttonView.minWidth(25);
        button.setGraphic(buttonView);
        return button;
    }
}
