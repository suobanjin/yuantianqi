package zzuli.zw.weather.service.weatherservice;

import javafx.beans.property.BooleanProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.views.customize.ChartVBox;
import zzuli.zw.weather.views.customize.WeekGridPane;
import java.io.IOException;


public class WeatherTask extends Service<Weather> {
    private BooleanProperty ready;
    public WeatherTask(BooleanProperty ready){
        this.ready = ready;
    }
    @Override
    protected Task<Weather> createTask() {
        return new Task<Weather>() {
            @Override
            protected Weather call(){
                WeatherServiceImpl service = WeatherServiceImpl.getInstance();
                Weather weather;
                try {
                    weather = service.WeatherDefault();
                    if (weather == null){
                        this.failed();
                        ready.setValue(Boolean.FALSE);
                    }
                    ready.setValue(Boolean.TRUE);
                    WeekGridPane weekGridPane = new WeekGridPane();
                    ChartVBox chartVBox = new ChartVBox();
                    service.registerObserver(weekGridPane);
                    service.registerObserver(chartVBox);
                    BeanFactory.getManageNode().put("chartVBox", chartVBox);
                    BeanFactory.getCityWeather().put("cityWeather", weather);
                    BeanFactory.getManageNode().put("weekGridPane", weekGridPane);
                    service.notifyObservers();
                    return weather;
                } catch (IOException e) {
                    this.setOnFailed(event -> {

                    });
                    ready.setValue(Boolean.FALSE);
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}
