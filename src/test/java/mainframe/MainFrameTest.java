package mainframe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import zzuli.zw.weather.utils.GetResources;
import zzuli.zw.weather.views.baseframe.BaseAnchorPane;
import zzuli.zw.weather.views.baseframe.BaseScene;
import zzuli.zw.weather.views.baseframe.BaseStage;
import zzuli.zw.weather.views.customize.ChartVBox;
import zzuli.zw.weather.views.customize.CloseAndMin;
import zzuli.zw.weather.views.customize.MenuFactory;
import java.util.Objects;

public class MainFrameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BaseStage stage = new BaseStage();
        stage.setSize(500,450);
        StackPane stackPane = new StackPane();
        BaseAnchorPane ap = new BaseAnchorPane();
        stackPane.getChildren().add(ap);

        BaseScene scene = new BaseScene(stackPane,stage);
        scene.getStyles("css/background.css","css/menu.css");
        ap.getStyleClass().add("image");
        stackPane.getStyleClass().add("stackPane");
        Button button = new Button("测试");
        ContextMenu menu = MenuFactory.getMenu();
        menu.getStyleClass().add("context-menu");
        button.setContextMenu(menu);
        ap.getChildren().addAll(new CloseAndMin(),button);
        stage.setScene(scene);



        VBox vBox = new VBox();
        ap.getChildren().add(vBox);
        Text text1 = new Text("11");
        text1.setStyle("-fx-font-size: 60px");
        HBox hBox = new HBox();
        hBox.getChildren().add(text1);
        vBox.getChildren().add(hBox);
        ImageView view = new ImageView(Objects.requireNonNull(GetResources.getImagesPath("sheShiDu.png")));
        view.setFitHeight(50);
        view.setFitWidth(50);
        hBox.getChildren().add(view);
        /*Text text2 = new Text("℃");
        text2.setStyle("-fx-font-size: 45px");
        text2.setTextAlignment(TextAlignment.CENTER);
        hBox.getChildren().add(text2);*/

        HBox box1 = new HBox();
        ImageView imageView = new ImageView(Objects.requireNonNull(GetResources.getImagesPath("title.png")));
        imageView.setFitHeight(19);
        imageView.setFitWidth(19);
        box1.setPadding(new Insets(11));
        box1.setSpacing(10.0);
        box1.getChildren().addAll(imageView,new Text("晴"));
        vBox.getChildren().addAll(box1,new ChartVBox());
        AnchorPane.setTopAnchor(vBox,100.0);
        AnchorPane.setLeftAnchor(vBox,190.0);
        stage.show();

    }
}
