package zzuli.zw.weather.views.customize;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.events.ButtonEvent;
import zzuli.zw.weather.service.events.UpdateEvent;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.service.weatherservice.interfaces.Observer;
import zzuli.zw.weather.service.yiyan.DayWordService;
import zzuli.zw.weather.service.yiyan.DayWordTask;
import zzuli.zw.weather.utils.ImageViewUtils;
import zzuli.zw.weather.utils.TextUtils;
import zzuli.zw.weather.utils.WeatherUtils;

public class MainVBox extends VBox implements Observer {

    public MainVBox() {
        WeatherServiceImpl.getInstance().registerObserver(this);
        HBox top = top();
        GridPane gridPane = center();
        VBox vBox = bottom();
        /*ImageView imageView = ImageViewUtils.setImage(25, 30, "warming.png");
        Text red = TextUtils.setText(20, "red", "", "今日有暴风，请注意防护");
        red.setLayoutY(-10);
        red.setLayoutX(50);
        HBox hBox = new HBox(imageView,red);
        hBox.setSpacing(10);
        AnchorPane anchorPane = new AnchorPane(hBox);
        VBox parent = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerTask task = this;
                Platform.runLater(()->{
                    red.setText("");
                    parent.getChildren().remove(0);
                    this.cancel();
                    task.cancel();
                    timer.cancel();
                });
             }
            },5000);*/
        this.getChildren().addAll(top, gridPane, vBox);
        BeanFactory.getManageNode().put("mainVBox", this);
        BeanFactory.getManageNode().put("main_top", top);
        this.setPadding(new Insets(0, 0, 0, -32));
    }

    private String wenDu = "NA";

    @Override
    public void update() {

    }

    public HBox top() {
        HBox hBox = new HBox();
        String wenDu = "NA";
        Text text = TextUtils.setText(90, wenDu);
        hBox.getChildren().add(text);
        ImageView view = ImageViewUtils.setImage(80, "sheShiDu.png");
        hBox.getChildren().add(view);
        BeanFactory.getManageNode().put("hBoxText", text);
        return hBox;
    }

    public GridPane center() {
        GridPane gridPane = new GridPane();
        String spacingStr = "   ";
        Text spacing = new Text("               ");

        //
        Text text = TextUtils.setText(" " + "NA" + "/" + "NA", 0.9);
        TextUtils.setText(text, 14, "gray", "Verdana");
        BeanFactory.getManageNode().put("temperatureLh", text);
        gridPane.addRow(1, spacing, text);
        //

        ImageView view = WeatherUtils.checkAndSetting("DefaultWeather");
        Text text3 = TextUtils.setText(16, spacingStr + spacingStr + "  " + spacingStr + spacingStr + spacingStr);
        BeanFactory.getManageNode().put("typeAndQuality", text3);
        BeanFactory.getManageNode().put("typeImage", view);
        Button button = new Button();
        ImageView view3 = ImageViewUtils.setImage(16, "refresh.png");
        button.setStyle("-fx-background-color: #ffffff00");
        button.setGraphic(view3);
        BeanFactory.getManageButton().put("updateButton", button);
        new ButtonEvent().updateData(button);
        gridPane.addRow(2, view, text3, button);

        //
        ImageView view2 = ImageViewUtils.setImage(35, "Location.png");
        Text text4 = TextUtils.setText(16, spacingStr + spacingStr + "  " + spacingStr + spacingStr + spacingStr);
        BeanFactory.getManageNode().put("locationText", text4);
        Label label = new Label(" [切换]");
        label.setOnMousePressed(event -> {
            label.setCursor(Cursor.HAND);
            AlertFrame alertFrame = new AlertFrame();
            alertFrame.showAndWait();
        });
        label.setOnMouseEntered(event -> {
            label.setCursor(Cursor.HAND);
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setHeight(label.getHeight());
            innerShadow.setWidth(label.getWidth());
            innerShadow.setColor(Color.DARKGRAY);
            label.setEffect(innerShadow);
        });
        label.setOnMouseExited(event -> label.setEffect(null));
        gridPane.addRow(3, view2, text4, label);

        //
        ImageView view4 = ImageViewUtils.setImage(35, "date.png");
        Text text5 = TextUtils.setText(16, spacingStr + spacingStr + " " + spacingStr);
        BeanFactory.getManageNode().put("timeText", text5);
        gridPane.addRow(4, view4, text5);

        //
        ImageView view5 = ImageViewUtils.setImage(35, "shiDu.png");
        Text text6 = TextUtils.setText(16, spacingStr + spacingStr + spacingStr);
        BeanFactory.getManageNode().put("shiDuText", text6);
        gridPane.addRow(5, view5, text6);

        ImageView view6 = ImageViewUtils.setImage(35, "pm25.png");
        Text text7 = TextUtils.setText(16, spacingStr + spacingStr + spacingStr);
        BeanFactory.getManageNode().put("pm25Text", text7);
        gridPane.addRow(6, view6, text7);
        gridPane.setVgap(10);
        gridPane.setHgap(4);
        gridPane.setPadding(new Insets(0, 0, 0, 38));
        return gridPane;
    }

    public VBox bottom() {
        VBox vBox = new VBox();
        HBox hBox = new HBox();

        Text text = TextUtils.setText("开启一天好心情！ —— 猿天气 ", 1);
        text.setOnMouseClicked(event -> {
            /*Platform.runLater(() -> {
                UpdateEvent updateEvent = new UpdateEvent();
                DayWordService dayWordService = new DayWordService();
                updateEvent.updateWord(dayWordService.getWord());
            });*/
            DayWordTask dayWordTask = new DayWordTask();
            dayWordTask.valueProperty().addListener((observable, oldValue, newValue) -> {
                UpdateEvent updateEvent = new UpdateEvent();
                //updateEvent.updateWord(newValue);
                Platform.runLater(()-> updateEvent.updateWord(newValue));
            });
            dayWordTask.start();
        });
        text.setOnMouseEntered(event -> {
            text.setCursor(Cursor.HAND);
        });
        TextUtils.setText(text, 16);
        BeanFactory.getManageNode().put("yiYanWord", text);
        text.setWrappingWidth(350);
        hBox.setSpacing(12);
        hBox.setAlignment(Pos.CENTER);
        ImageView view = ImageViewUtils.setImage(45, "dayWord.png");
        hBox.getChildren().addAll(view, text);
        vBox.getChildren().addAll(hBox);
        vBox.setLayoutX(-150);
        vBox.setLayoutY(20);
        vBox.setPadding(new Insets(20, 10, 10, -60));
        return vBox;
    }

}
