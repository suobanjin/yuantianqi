package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.events.ButtonEvent;

public class CloseAndMin extends HBox {

    public CloseAndMin(){
        Button close = setButton(new Button(),"close.png");
        Button min = setButton(new Button(),"min.png");
        Button menu = setButton(new Button(),"menu.png");
        BeanFactory.getManageButton().put("close",close);
        BeanFactory.getManageButton().put("min",min);
        BeanFactory.getManageButton().put("menu",menu);
        this.getChildren().addAll(menu,min,close);
        setPadding(new Insets(4));
        setSpacing(-4);
        enter(close,"closeAfter.png");
        exit(close,"close.png");
        enter(min,"minAfter.png");
        exit(min,"min.png");
        enter(menu,"menuAfter.png");
        exit(menu,"menu.png");
        ButtonEvent buttonEvent = new ButtonEvent();
        buttonEvent.closeEvent(close);
        buttonEvent.minEvent(min);
        buttonEvent.menuEvent(menu);
    }

    /**
     * 设置按钮的样式
     * @param button 按钮
     * @param buttonName  按钮的名称
     * @return Button
     */
    private Button setButton(Button button,String buttonName){
        return new ButtonStyle().setButton(button,buttonName);
    }

    /**
     * 鼠标进入时的事件
     * @param button Button
     * @param buttonName  按钮的名称
     */
    private void enter(Button button,String buttonName){
        button.setOnMouseEntered(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, buttonName);
        });
    }

    /**
     * 鼠标退出时的事件
     * @param button Button
     * @param buttonName 按钮的名称
     */
    private void exit(Button button,String buttonName){
        button.setOnMouseExited(event -> {
            button.setCursor(Cursor.HAND);
            new ButtonStyle().setButton(button, buttonName);
        });
    }
}
