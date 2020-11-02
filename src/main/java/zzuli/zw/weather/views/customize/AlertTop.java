package zzuli.zw.weather.views.customize;

import javafx.scene.layout.HBox;

public class AlertTop extends TopHBox{
    public AlertTop(double spacing, double width) {
        super(spacing, width);
    }

    @Override
    public HBox right() {
        return new AlertClose();
    }
}
