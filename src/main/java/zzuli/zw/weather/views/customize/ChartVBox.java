package zzuli.zw.weather.views.customize;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
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
    public BarChart<String, Number> getBarChartView() {
        Weather weather = BeanFactory.getCityWeather().get("cityWeather");
        if (weather == null) {
            try {
                weather = new WeatherServiceImpl().weatherByCityId(BeanFactory.getCityInfo().get("cityCode"));
                return paintBarChart(weather);
            } catch (IOException e) {
                AlertFrame frame = new AlertFrame();
                AlertErrorPane errorPane = new AlertErrorPane();
                errorPane.setErrorMessage("数据获取失败！");
                frame.show();
                e.printStackTrace();
            }
        } else {
            return paintBarChart(weather);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public BarChart<String,Number> paintBarChart(Weather weather){
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        final BarChart<String,Number> bc =
                new BarChart<>(xAxis,yAxis);
        xAxis.setLabel("日期");
        yAxis.setLabel("温度");
        XYChart.Series<String,Number> series1 = new XYChart.Series<>();
        XYChart.Series<String,Number> series2 = new XYChart.Series<>();
        series1.setName("最高温");
        series2.setName("最低温");
        Forecast[] forecast = weather.getData().getForecast();
        for (Forecast forecast1 : forecast) {
            String high = forecast1.getHigh();
            String low = forecast1.getLow();
            String date = forecast1.getYmd();
            String[] strings = date.split("-");
            date = Integer.parseInt(strings[1]) + "月" + Integer.parseInt(strings[2]);
            Number numberLow = StringToDouble(low, "低温");
            Number numberHigh = StringToDouble(high, "高温");
            series1.getData().add(new XYChart.Data<>(date,numberHigh));

            series2.getData().add(new XYChart.Data<>(date,numberLow));
        }
        bc.getData().addAll(series1,series2);
        return bc;
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
            Number lowNumber = StringToDouble(low, "低温");
            Number highNumber = StringToDouble(high,"高温" );
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

    private Number StringToDouble(String high,String type){
        high = high.replace(type, "");
        high = high.replace(" ", "");
        high = high.replace("℃", "");
        return Double.parseDouble(high);
    }
    public void setData(){
        this.getChildren().clear();
        LineChart<String, Number> lineChart = getChartView();
        if (lineChart == null) {
            AlertFrame frame = new AlertFrame();
            new AlertErrorPane().setErrorMessage("数据获取失败！");
            frame.show();
        } else {
            HBox hBox = new HBox();
            Button b1 = new Button("折线图");
            Button b2 = new Button("柱状图");
            b2.setOnAction(event -> {
                this.getChildren().remove(1);
                this.getChildren().add(getBarChartView());
            });
            b1.setOnAction(event -> {
                this.getChildren().remove(1);
                this.getChildren().add(1, getChartView());
            });
            hBox.getChildren().addAll(b1,b2);
            hBox.setSpacing(10);
            this.getChildren().addAll(hBox,getChartView());
            this.setPrefSize(380, 480);
            this.setPadding(new Insets(50, 0, 0, -200));
        }
    }
    @Override
    public void update() {
        setData();
    }
}
