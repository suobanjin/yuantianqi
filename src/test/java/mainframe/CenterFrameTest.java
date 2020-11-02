package mainframe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import zzuli.zw.weather.utils.GetResources;

import java.util.Objects;

public class CenterFrameTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(450);
        stage.setWidth(500);
        AnchorPane ap = new AnchorPane();
        VBox vBox = new VBox();
        ap.getChildren().add(vBox);
        Text text1 = new Text("11");
        text1.setStyle("-fx-font-size: 60px");
        HBox hBox = new HBox();
        hBox.getChildren().add(text1);
        vBox.getChildren().add(hBox);
        Text text2 = new Text("℃");
        text2.setStyle("-fx-font-size: 45px");
        text2.setTextAlignment(TextAlignment.CENTER);
        hBox.getChildren().add(text2);

        HBox box1 = new HBox();
        ImageView imageView = new ImageView(Objects.requireNonNull(GetResources.getImagesPath("title.png")));
        imageView.setFitHeight(19);
        imageView.setFitWidth(19);
        box1.setPadding(new Insets(11));
        box1.setSpacing(10.0);
        box1.getChildren().addAll(imageView,new Text("晴"));
        vBox.getChildren().add(box1);
        AnchorPane.setTopAnchor(vBox,100.0);
        AnchorPane.setLeftAnchor(vBox,180.0);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}
