package zzuli.zw.weather.views.customize;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.events.ButtonEvent;
import zzuli.zw.weather.service.events.UpdateEvent;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.utils.*;

import java.io.IOException;
import java.util.List;

public class ChoicePane extends VBox {
    private Button button = new Button("确 定");

    public ChoicePane() {
        this.getChildren().addAll(top(), center(), bottom());
        this.setSpacing(20);
        this.setMinSize(420, 150);
    }

    public HBox top() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 0, 0, -230));
        ImageView view = ImageViewUtils.setImage(30, "select.png");
        hBox.getChildren().add(view);
        Text text = TextUtils.setText(16, "请选择居住地");
        hBox.getChildren().add(text);
        hBox.setSpacing(15);
        return hBox;
    }

    public HBox center() {
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setValue("选择省份");
        comboBox1.setMinWidth(80);
        comboBox1.setItems(FXCollections.observableArrayList(BeanFactory.getProvinces()));
        comboBox1.setVisibleRowCount(10);
        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.setVisibleRowCount(10);
        comboBox2.setPromptText("选择城市");
        ComboBox<String> comboBox3 = new ComboBox<>();
        comboBox3.setVisibleRowCount(10);
        comboBox3.setPromptText("选择县区");
        comboBox1.setOnAction(event -> {
            String province = comboBox1.getSelectionModel().getSelectedItem();
            List<String> city = Dom4jUtils.getCites(province);
            comboBox2.setItems(FXCollections.observableArrayList(city));
            comboBox2.setValue(comboBox2.getItems().get(0));
        });
        comboBox2.setOnAction(event1 -> {
            String cityName = comboBox2.getSelectionModel().getSelectedItem();
            List<String> countyLists = Dom4jUtils.getCounty(cityName);
            comboBox3.setItems(FXCollections.observableArrayList(countyLists));
            comboBox3.setValue(comboBox3.getItems().get(0));
        });
        final String[] result = {null};
        comboBox3.setOnAction(event -> result[0] = comboBox3.getSelectionModel().getSelectedItem());
        ButtonEvent buttonEvent = new ButtonEvent();
        buttonEvent.buttonEnter(button);
        buttonEvent.buttonExited(button);
        /*button.setOnMouseEntered(event -> {
            button.setCursor(Cursor.HAND);
            button.setEffect(new DropShadow());
        });
        button.setOnMouseExited(event -> {
            button.setCursor(Cursor.DEFAULT);
            button.setEffect(null);
        });*/
        button.setOnAction(event -> {
            button.setCursor(Cursor.HAND);
            button.setEffect(new DropShadow());
            Button updateButton = (Button) BeanFactory.getManageButton().get("updateButton");
            updateButton.setGraphic(null);
            ImageView view = ImageViewUtils.setImage(20, "refresh.gif");
            updateButton.setGraphic(view);
            String resultStr = result[0];
            final String[] code = {null};
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), t -> {
                if (resultStr == null) {
                    ImageView view02 = ImageViewUtils.setImage(16, "refresh.png");
                    updateButton.setGraphic(view02);
                    AlertFrame alertFrame = new AlertFrame();
                    new AlertErrorPane().setErrorMessage("内容不能为空！");
                    alertFrame.show();
                } else {
                    code[0] = Dom4jUtils.getWeatherCode(resultStr);
                    BeanFactory.getCityInfo().put("cityCode", code[0]);
                    WeatherServiceImpl service = WeatherServiceImpl.getInstance();
                    try {
                        Weather weather = service.weatherByCityId(code[0]);
                        if (weather == null || weather.getStatus() != 200) {
                            AlertFrame alertFrame = new AlertFrame();
                            new AlertErrorPane().setErrorMessage("暂无当前区域天气信息！");
                            alertFrame.initModality(Modality.APPLICATION_MODAL);
                            alertFrame.show();
                        } else {
                            BeanFactory.getCityWeather().put("cityWeather", weather);
                            service.notifyObservers();
                            UpdateEvent updateEvent = new UpdateEvent();
                            updateEvent.update(weather);
                            buttonEvent.updateData(updateButton, weather);
                        }
                    } catch (IOException e) {
                        AlertFrame alertFrame = new AlertFrame();
                        new AlertErrorPane().setErrorMessage("网络连接失败！");
                        alertFrame.initModality(Modality.APPLICATION_MODAL);
                        alertFrame.show();
                        e.printStackTrace();
                    }finally {
                        ImageView view3 = ImageViewUtils.setImage(16, "refresh.png");
                        updateButton.setGraphic(view3);
                    }
                }
            });
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames().add(keyFrame);
            Stage stage = BeanFactory.getManageUI().get("alertStage");
            stage.close();
            timeline.play();
        });
        hBox.getChildren().addAll(comboBox1, comboBox2, comboBox3);
        hBox.setPadding(new Insets(0, 0, 10, 18));
        return hBox;
    }

    public HBox bottom() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        button.setStyle("-fx-background-radius: 6px;" +
                "-fx-background-color: #1296db;" +
                "-fx-text-fill: white;" +
                "-fx-border-style: none;" +
                "-fx-font-size: 16px");
        button.setPrefSize(130, 32);
        hBox.getChildren().add(button);
        hBox.setPadding(new Insets(5, 0, 0, 0));
        return hBox;
    }
}
