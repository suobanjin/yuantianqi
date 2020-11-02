package zzuli.zw.weather.views.customize;

import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.log4j.Logger;
import zzuli.zw.weather.domain.Forecast;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.service.weatherservice.interfaces.Observer;
import zzuli.zw.weather.utils.TextUtils;
import zzuli.zw.weather.utils.WeatherUtils;

import java.io.IOException;

/**
 * @ClassName: WeekGridPane
 * @date: 2020/10/8 17:06
 * @author 索半斤
 * @Description: 预测信息展示
 */
public class WeekGridPane extends GridPane implements Observer {
    private Logger logger = Logger.getLogger(WeekGridPane.class);
    WeatherServiceImpl service = new WeatherServiceImpl();
    public WeekGridPane() {
        this.setStyle("-fx-background-color: #ffffff00");
        this.setPadding(new Insets(0, 0, 0, 30));
    }
    private void setData() {
        Weather weather = BeanFactory.getCityWeather().get("cityWeather");
        if (weather == null) {
            try {
                String cityCode = BeanFactory.getCityInfo().get("cityCode");
                weather = service.weatherByCityId(cityCode);
                setForecastData(weather);
            } catch (IOException e) {
                AlertFrame alertFrame = new AlertFrame();
                new AlertErrorPane().setErrorMessage("数据获取失败！");
                alertFrame.show();
                logger.error("错误信息{}",e);
                e.printStackTrace();
            }
        }else{
            setForecastData(weather);
        }
    }

    private void setForecastData(Weather weather){
        this.getChildren().clear();
        Forecast[] forecast = weather.getData().getForecast();
        for (int i = 0; i < forecast.length; i++) {
            String week = forecast[i].getWeek();
            String date = forecast[i].getYmd();
            String[] split = date.split("-");
            date = ""+Integer.parseInt(split[1]) + "月" + split[2];
            week = date + "  " + week;
            String weatherType = forecast[i].getType();
            String low = forecast[i].getLow();
            low = low.replace("低温", "");
            String high = forecast[i].getHigh();
            high = high.replace("高温", "");
            String fl = forecast[i].getFl();
            String fx = forecast[i].getFx();
            HBox hBox = new HBox();
            hBox.setStyle("-fx-background-color: #ffffff00");
            hBox.setSpacing(40);
            hBox.setPadding(new Insets(20, 0, 0, 10));
            Text text1 = TextUtils.setText(15, week);
            Text text2 = TextUtils.setText(15, low + " / " + high);
            ImageView imageView = WeatherUtils.checkAndSetting(weatherType);
            Tooltip tip = new Tooltip(weatherType);
            Tooltip.install(imageView, tip);
            tip.setFont(Font.font(12));
            Text text3 = TextUtils.setText(15, fl + " / " + fx);
            hBox.getChildren().addAll(text1, imageView, text2, text3);
            this.addRow(i + 1, hBox);
        }
    }
    @Override
    public void update() {
        setData();
    }
}
