package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import zzuli.zw.weather.utils.GetResources;

import java.util.Objects;


/**
 * 标题信息，图标信息
 */
public class TitleBox extends HBox {
    public TitleBox(){
        ImageView titleView = new ImageView(Objects.requireNonNull(GetResources.getImagesPath("iconandtitle.png")));
        titleView.setFitHeight(50);
        titleView.setFitWidth(130);
        setPadding(new Insets(5));
        setSpacing(6);
        getChildren().addAll(titleView);
    }
}
