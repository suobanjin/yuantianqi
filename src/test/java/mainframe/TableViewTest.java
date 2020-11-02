package mainframe;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import zzuli.zw.weather.domain.Forecast;

public class TableViewTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.setHeight(300);
        stage.setWidth(400);
        StackPane stackPane = new StackPane();
        TableView<Forecast> tableView = new TableView<>();
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>();
        //stackPane.getChildren().add(choiceDialog);
        choiceDialog.show();

        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();
    }
}
