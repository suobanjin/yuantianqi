package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.events.ButtonEvent;

public class AlertClose extends HBox {
    public AlertClose(){
        Button close = setButton(new Button());
        BeanFactory.getManageButton().put("alertClose",close);
        this.getChildren().addAll(close);
        setPadding(new Insets(3));
        enter(close);
        exit(close);
        ButtonEvent buttonEvent = new ButtonEvent();
        buttonEvent.alertCloseEvent(close);
    }
    /**
     * 设置按钮的样式
     * @param button 按钮
     * @return Button
     */
    private Button setButton(Button button){
        return new ButtonStyle().setButton(button, "close.png");
    }

    /**
     * 鼠标进入时的事件
     * @param button Button
     *
     */
    private void enter(Button button){
        button.setOnMouseEntered(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, "closeAfter.png");
        });
    }

    /**
     * 鼠标退出时的事件
     * @param button Button
     *
     */
    private void exit(Button button){
        button.setOnMouseExited(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, "close.png");
        });
    }
}
