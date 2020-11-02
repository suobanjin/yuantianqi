package mainframe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zzuli.zw.weather.views.customize.WeekGridPane;

public class GridPaneTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.setHeight(300);
        stage.setWidth(400);
        StackPane stackPane = new StackPane();
        WeekGridPane gridPane = new WeekGridPane();
        ScrollPane pane = new ScrollPane();
        pane.setPrefSize(300,400);
       // pane.getChildrenUnmodifiable().add(gridPane);
        pane.setContent(gridPane);
        pane.setStyle("-fx-background-color: #ffffff00");
        stackPane.getChildren().add(pane);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();
    }
}
