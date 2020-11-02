package mainframe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import zzuli.zw.weather.utils.GetResources;
import zzuli.zw.weather.views.customize.ChartVBox;

public class ChartVBoxTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.setHeight(500);
        stage.setWidth(400);
        VBox vBox = new VBox();
        vBox.getChildren().add(new ChartVBox());
        Scene scene = new Scene(vBox);
        scene.setFill(Paint.valueOf("#DC143C"));
        vBox.setStyle("-fx-background-color: #DC143C");
        stage.setScene(scene);
        scene.getStylesheets().add(GetResources.getResourcePath("css/chartStyle.css"));
        stage.show();
    }
}
