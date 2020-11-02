package zzuli.zw.weather.views.customize;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import zzuli.zw.weather.domain.Forecast;
import zzuli.zw.weather.domain.Weather;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.weatherservice.WeatherServiceImpl;
import zzuli.zw.weather.service.weatherservice.interfaces.Observer;

import java.io.IOException;

public class ChartVBox extends VBox implements Observer {

    public ChartVBox() {
        //setData();
    }
    public LineChart<String, Number> getChartView() {
        Weather weather = BeanFactory.getCityWeather().get("cityWeather");
        if (weather == null) {
            try {
                weather = new WeatherServiceImpl().weatherByCityId(BeanFactory.getCityInfo().get("cityCode"));
                return paintChart(weather);
            } catch (IOException e) {
                AlertFrame frame = new AlertFrame();
                AlertErrorPane errorPane = new AlertErrorPane();
                errorPane.setErrorMessage("数据获取失败！");
                frame.show();
                e.printStackTrace();
            }
        } else {
            return paintChart(weather);
        }
        return null;
    }

    public LineChart<String, Number> paintChart(Weather weather){
        CategoryAxis x = new CategoryAxis();
        x.setLabel("日期");
        NumberAxis y = new NumberAxis();
        y.setLabel("温度");
        ObservableList<XYChart.Series<String, Number>> list = FXCollections.observableArrayList();
        XYChart.Series<String, Number> chart1 = new XYChart.Series<>();
        chart1.setName("高温");
        XYChart.Series<String, Number> chart2 = new XYChart.Series<>();
        chart2.setName("低温");
        ObservableList<XYChart.Data<String, Number>> chart1Data = chart1.getData();
        ObservableList<XYChart.Data<String, Number>> chart2Data = chart2.getData();
        Forecast[] forecasts = weather.getData().getForecast();
        for (Forecast forecast : forecasts) {
            String low = forecast.getLow();
            String high = forecast.getHigh();
            String date = forecast.getYmd();
            low = low.replace("低温", "");
            low = low.replace(" ", "");
            low = low.replace("℃", "");
            Number lowNumber = Double.parseDouble(low);
            high = high.replace("高温", "");
            high = high.replace(" ", "");
            high = high.replace("℃", "");
            Number highNumber = Double.parseDouble(high);
            String[] strings = date.split("-");
            date = Integer.parseInt(strings[1]) + "月" + Integer.parseInt(strings[2]);
            XYChart.Data<String, Number> data1 = new XYChart.Data<>(date, highNumber);
            XYChart.Data<String, Number> data2 = new XYChart.Data<>(date, lowNumber);
            chart1Data.add(data1);
            chart2Data.add(data2);
        }
        list.add(chart1);
        list.add(chart2);

        chart1Data.forEach(data -> {
            HBox hBox = new HBox();
            data.setNode(hBox);
            Tooltip tip1 = new Tooltip(data.getXValue() + "最高温：" + data.getYValue() + "℃");
            Tooltip.install(data.getNode(), tip1);
        });
        chart2Data.forEach(data -> {
            HBox hBox = new HBox();
            data.setNode(hBox);
            Tooltip tip1 = new Tooltip(data.getXValue() + "最低温：" + data.getYValue() + "℃");
            Tooltip.install(data.getNode(), tip1);
        });
        LineChart<String, Number> lineChart = new LineChart<>(x, y, list);
        lineChart.setAnimated(true);
        lineChart.setLegendVisible(true);
        return lineChart;
    }

    public void setData(){
        this.getChildren().clear();
        LineChart<String, Number> lineChart = getChartView();
        if (lineChart == null) {
            AlertFrame frame = new AlertFrame();
            new AlertErrorPane().setErrorMessage("数据获取失败！");
            frame.show();
        } else {
            this.getChildren().add(getChartView());
            this.setPrefSize(380, 480);
            this.setPadding(new Insets(50, 0, 0, -200));
        }
    }
    @Override
    public void update() {
        setData();
    }
}
