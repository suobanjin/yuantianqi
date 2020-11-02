package zzuli.zw.weather.views.customize;

import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import zzuli.zw.weather.service.weatherservice.interfaces.Observer;

public class BarChartVBox extends VBox implements Observer {
    public BarChartVBox(){

    }
    public BarChart<String,Integer> integerBarChart(){
        return null;
    }
    @Override
    public void update() {

    }
}
