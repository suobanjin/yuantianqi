package zzuli.zw.weather.service.events;

import javafx.scene.control.Button;

public interface ButtonEventInterface {
    void closeEvent(Button button);
    void minEvent(Button button);
    void menuEvent(Button button);
    void updateData(Button button);
    void alertCloseEvent(Button button);
    void buttonEnter(Button button);
    void buttonExited(Button button);
}
