package zzuli.zw.weather.service.events;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import zzuli.zw.weather.domain.Forecast;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.utils.DateUtils;
import zzuli.zw.weather.utils.ImageViewUtils;
import zzuli.zw.weather.utils.TextUtils;
import zzuli.zw.weather.utils.WeatherUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @ClassName: UpdateEvent
 * @date: 2020/10/8 14:02
 * @author 索半斤
 * @Description: 事件更新事件
 */
public class UpdateEvent {
    private static final String spacingStr = "   ";
    private static final String TYPE_01 = "晴";
    private static final String TYPE_02 = "多云";
    /**
     * @MethodName:  update
     * @date: 2020/10/8 14:16
     * @author 索半斤
     * @Description: 更新天气信息
     */
    public void update(Weather weather) {
        Platform.runLater(() -> {
            BeanFactory.getCityWeather().put("cityWeather",weather);
            Forecast forecast = weather.getData().getForecast()[0];
            int fl = Integer.parseInt(forecast.getFl().substring(0, 1));
            String type = forecast.getType();
            ImageView imageView = null;
            Text red = null;
            if (fl > 1){
                imageView = ImageViewUtils.setImage(25, 30, "warming.png");
                red = TextUtils.setText(20, "red", "", "今日有风，请注意防护");
            }
            if (fl > 5){
                imageView = ImageViewUtils.setImage(25, 30, "warming.png");
                red = TextUtils.setText(20, "red", "", "今日有强风，请注意防护");
            }
            if (type.contains("雨")){
                imageView = ImageViewUtils.setImage(25, 30, "warming.png");
                red = TextUtils.setText(20, "red", "", "今日有雨，不要忘记带伞");
            }
            if (type.contains("雪")){
                imageView = ImageViewUtils.setImage(25, 30, "warming.png");
                red = TextUtils.setText(20, "red", "", "今日有雪，不要忘记带伞");
            }
            if (imageView != null) {
                red.setLayoutY(-10);
                red.setLayoutX(50);
                HBox hBox = new HBox(imageView, red);
                hBox.setSpacing(10);
                AnchorPane anchorPane = new AnchorPane(hBox);
                VBox parent = (VBox) BeanFactory.getManageNode().get("mainVBox");
                parent.getChildren().add(0, anchorPane);
                Timer timer = new Timer();
                Text finalRed = red;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        TimerTask task = this;
                        Platform.runLater(() -> {
                            finalRed.setText("");
                            parent.getChildren().remove(0);
                            this.cancel();
                            task.cancel();
                            timer.cancel();
                        });
                    }
                }, 5000);
            }
            updateWeather(weather);
        });
    }

    //更新天气信息
    private void updateWeather(Weather weather){
        //温度
        updateWenDu(weather);
        //最高温/最低温
        updateHighAndLowWenDu(weather);
        //天气类型
        updateWeatherType(weather);
        // 位置信息
        updateWeatherLocation(weather);
        //更新时间
        updateWeatherTime(weather);
        // 湿度
        updateWeatherShiDu(weather);
        // pm25
        updatePm25(weather);
    }
    //更新温度信息
    private void updateWenDu(Weather weather){
        String wenDu = weather.getData().getWendu();
        if (wenDu.trim().length() < 2){
            wenDu = spacingStr + wenDu;
        }else if (wenDu.trim().length() == 2){
            wenDu = "  " + wenDu;
        }
        Text hBoxText = (Text) BeanFactory.getManageNode().get("hBoxText");
        hBoxText.setText(wenDu);
        setEffect(hBoxText);
    }
    //更新最高温和最低温
    private void updateHighAndLowWenDu(Weather weather){
        Text temperatureLh = (Text) BeanFactory.getManageNode().get("temperatureLh");
        String lowTemperature = weather.getData().getForecast()[0].getLow();
        String highTemperature = weather.getData().getForecast()[0].getHigh();
        temperatureLh.setText(lowTemperature + "/" + highTemperature);
        setEffect(temperatureLh);
    }
    //更新一言
    public void updateWord(String word){
        Platform.runLater(() -> {
            Text text = (Text)BeanFactory.getManageNode().get("yiYanWord");
            text.setText(word);
            setEffect(text);
        });
    }
    //更新pm2.5值
    private void updatePm25(Weather weather){
        Text pm25Text = (Text) BeanFactory.getManageNode().get("pm25Text");
        pm25Text.setText(spacingStr + spacingStr + weather.getData().getPm25());
        setEffect(pm25Text);
    }
    //更新天气类型
    private void updateWeatherType(Weather weather){
        String weatherType = weather.getData().getForecast()[0].getType();
        int index = weatherType.lastIndexOf("转");
        if (index != -1) {
            String str1 = weatherType.substring(0, index);
            String str2 = weatherType.substring(index + 1);
            weatherType = str1 + "-" + str2;
        }
        if (weatherType.equals(TYPE_01)){
            if (DateUtils.isNight()) {
                weatherType = "晚晴天";
            }
        }else if (weatherType.equals(TYPE_02)){
            if (DateUtils.isNight()){
                weatherType = "晚多云";
            }
        }
        ImageView typeImage = (ImageView) BeanFactory.getManageNode().get("typeImage");
        WeatherUtils.checkAndSetting(typeImage, weatherType);
        Text typeAndQuality = (Text) BeanFactory.getManageNode().get("typeAndQuality");
        String quality = weather.getData().getQuality();
        if (quality.length() <=2){
            quality = "空气" + quality;
        }
        typeAndQuality.setText( weatherType + "   " + quality);
        setEffect(typeAndQuality);
        setEffect(typeImage);
    }
    //更新位置信息
    private void updateWeatherLocation(Weather weather){
        Text locationText = (Text) BeanFactory.getManageNode().get("locationText");
        locationText.setText(weather.getCityInfo().getParent() + "  " + weather.getCityInfo().getCity());
        setEffect(locationText);
    }
    //更新时间
    private void updateWeatherTime(Weather weather){
        String updateTime = weather.getCityInfo().getUpdateTime();
        Text timeText = (Text) BeanFactory.getManageNode().get("timeText");
        if (DateUtils.compare(updateTime)){
            timeText.setText("更新于昨日 " + updateTime);
        }else {
            timeText.setText("更新于 " + updateTime);
        }
        setEffect(timeText);
    }
    //更新湿度
    private void updateWeatherShiDu(Weather weather){
        Text shiDuText = (Text) BeanFactory.getManageNode().get("shiDuText");
        shiDuText.setText(spacingStr + spacingStr + weather.getData().getShidu());
        setEffect(shiDuText);
    }
    private void setEffect(Node node) {
        FadeTransition fd = new FadeTransition();
        fd.setNode(node);
        fd.setDuration(Duration.seconds(1.2));
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.setAutoReverse(true);
        fd.setCycleCount(1);
        fd.play();
    }

}
