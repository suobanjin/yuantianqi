package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import zzuli.zw.weather.utils.ImageViewUtils;
import zzuli.zw.weather.utils.TextUtils;

public class HelpVBox extends VBox {
    public HelpVBox(){
        ImageView imageView = ImageViewUtils.setImage(80,200, "iconandtitle.png");
        this.getChildren().addAll(imageView,center());
        this.setPadding(new Insets(120,0,0,20));
    }

    public GridPane center(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        ImageView view1 = ImageViewUtils.setImage(35, "developer.png");
        Text text1 = TextUtils.setText(16,"本软件由张万开发");
        gridPane.addRow(1,view1,text1);
        ImageView view2 = ImageViewUtils.setImage(35, "version.png");
        Text text2 = TextUtils.setText(16,"      V1.2");
        gridPane.addRow(2,view2,text2);
        return gridPane;
    }
}
