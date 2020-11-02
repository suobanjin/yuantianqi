package zzuli.zw.weather.service.events;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.utils.ImageViewUtils;
import zzuli.zw.weather.views.customize.AlertErrorPane;
import zzuli.zw.weather.views.customize.AlertFrame;
import zzuli.zw.weather.views.customize.ButtonStyle;
import zzuli.zw.weather.views.customize.MenuFactory;

import java.io.IOException;

/**
 * @ClassName: ButtonEvent
 * @date: 2020/10/8 14:01
 * @author 索半斤
 * @Description: 初始化按钮事件并为按钮初始化事件监听信息
 */
public class ButtonEvent implements ButtonEventInterface{

    @Override
    public void closeEvent(Button button) {
        button.setOnAction(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, "closeAfter.png");
            Stage stage = BeanFactory.getManageUI().get("mainFrame");
            stage.close();
        });
    }

    @Override
    public void minEvent(Button button) {
        button.setOnAction(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, "closeAfter.png");
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);
        });
    }

    @Override
    public void menuEvent(Button button) {
        button.setOnAction(event -> {
            button.setCursor(Cursor.HAND);
            Bounds bounds = button.getLayoutBounds();
            Bounds screenBounds = button.localToScreen(bounds);
            int x = (int) screenBounds.getMinX();
            int y = (int) screenBounds.getMinY();
            MenuFactory.getMenu().show(BeanFactory.getManageUI().get("mainFrame"), x + 13, y + 18);
        });
    }

    private RotateTransition routeEvent(Button button) {
        RotateTransition rotateTransition;
        double fromAngle = 0.0;
        //播放持续时间
        double playTime;
        //结束角度
        double toAngle = 360;
        //根据旋转角度大小计算动画播放持续时间
        playTime = Math.abs(toAngle - fromAngle) * 0.01;
        //Duration.seconds(3)设置动画持续时间
        rotateTransition = new RotateTransition(Duration.seconds(playTime), button);
        //设置旋转角度
        rotateTransition.setFromAngle(fromAngle);
        rotateTransition.setToAngle(toAngle);
        // 只播放一次
        rotateTransition.setCycleCount(1);
        // 每次旋转后是否改变旋转方向
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();
        return rotateTransition;
    }

    @Override
    public void updateData(Button button) {
        BeanFactory.getManageButton().put("updateButton",button);
        buttonEnter(button);
        buttonExited(button);
        button.setOnAction(event -> Platform.runLater(() -> {
            button.setCursor(Cursor.HAND);
            //RotateTransition rotateTransition = routeEvent(button);
            button.setGraphic(ImageViewUtils.setImage(20, "refresh.gif"));
            Timeline timelines = new Timeline();
            timelines.setCycleCount(1);
            timelines.setAutoReverse(false);
            KeyFrame keyFrames = new KeyFrame(Duration.millis(2000), t1 -> {
                WeatherServiceImpl service = WeatherServiceImpl.getInstance();
                UpdateEvent updateEvent = new UpdateEvent();
                try {
                    updateEvent.update(service.WeatherDefault());
                    service.notifyObservers();
                } catch (IOException e) {
                    AlertFrame frame = new AlertFrame();
                    new AlertErrorPane().setErrorMessage("数据获取失败！");
                    frame.show();
                    e.printStackTrace();
                } finally {
                    //rotateTransition.stop();
                    button.setGraphic(ImageViewUtils.setImage(16, "refresh.png"));
                }
            });
            timelines.getKeyFrames().clear();
            timelines.getKeyFrames().add(keyFrames);
            timelines.play();
        }));
    }

    public void updateData(Button button, Weather weather){
        buttonEnter(button);
        buttonExited(button);
        button.setOnAction(event -> Platform.runLater(() -> {
            button.setCursor(Cursor.HAND);
            button.setGraphic(ImageViewUtils.setImage(20, "refresh.gif"));
            Timeline timelines = new Timeline();
            timelines.setCycleCount(1);
            timelines.setAutoReverse(false);
            KeyFrame keyFrames = new KeyFrame(Duration.millis(2000), t1 -> {
                WeatherServiceImpl service = WeatherServiceImpl.getInstance();
                UpdateEvent updateEvent = new UpdateEvent();
                try {
                    updateEvent.update(weather);
                    service.notifyObservers();
                } finally {
                    button.setGraphic(ImageViewUtils.setImage(16, "refresh.png"));
                }
            });
            timelines.getKeyFrames().clear();
            timelines.getKeyFrames().add(keyFrames);
            timelines.play();
        }));
    }

    @Override
    public void alertCloseEvent(Button button) {
        button.setOnAction(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, "closeAfter.png");
            Stage stage = BeanFactory.getManageUI().get("alertStage");
            stage.close();
        });
    }

    @Override
    public void buttonEnter(Button button){
        button.setOnMouseEntered(event -> button.setCursor(Cursor.HAND));
    }

    @Override
    public void buttonExited(Button button){
        button.setOnMouseExited(event -> button.setCursor(Cursor.DEFAULT));
    }
}
