package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import zzuli.zw.weather.utils.ImageViewUtils;

public class WelcomeView extends VBox {

    public WelcomeView(){
        ImageView imageView = ImageViewUtils.setImage(170,220, "Welcome.png");
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setProgress(-1F);
        this.getChildren().addAll(imageView,progressIndicator);
        this.setPadding(new Insets(70,0,0,-38));
    }
}
