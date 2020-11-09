package zzuli.zw.weather.views.customize;

import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import zzuli.zw.weather.domain.Forecast;
import zzuli.zw.weather.domain.Weather;

public class PieChart {

    private final static String TYPE1 = "晴";
    private final static String TYPE2 = "中雨";
    private final static String TYPE3 = "大雨";
    private final static String TYPE4 = "阴";
    private final static String TYPE5 = "小雨";
    private final static String TYPE6 = "其它";
    private final static String TYPE7 = "暴雨";
    public javafx.scene.chart.PieChart getPieChart(Weather weather){
        Forecast[] forecasts = weather.getData().getForecast();
        int num1 = 0; //晴
        int num2 = 0; //中雨
        int num3 = 0; //大雨
        int num4 = 0; //阴
        int num5 = 0; //小雨
        int num6 = 0; //其它
        for (Forecast forecast : forecasts) {
            String type = forecast.getType();
            if (type.contains(TYPE1)){
                num1++;
            }else if (type.contains(TYPE2)){
                num2++;
            }else if (type.contains(TYPE3) || type.contains(TYPE7)){
                num3++;
            }else if (type.contains(TYPE4)){
                num4++;
            }else if (type.contains(TYPE5)){
                num5++;
            }else{
                num6++;
            }
        }
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections
                .observableArrayList(new javafx.scene.chart.PieChart.Data(TYPE1, num1),
                        new javafx.scene.chart.PieChart.Data(TYPE2, num2),
                        new javafx.scene.chart.PieChart.Data(TYPE3, num3),
                        new javafx.scene.chart.PieChart.Data(TYPE4, num4),
                        new javafx.scene.chart.PieChart.Data(TYPE5, num5),
                        new javafx.scene.chart.PieChart.Data(TYPE6, num6));
        javafx.scene.chart.PieChart chart = new javafx.scene.chart.PieChart(pieChartData);
        for (javafx.scene.chart.PieChart.Data d : pieChartData) {
            d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, chart));
            d.getNode().setOnMouseExited(new MouseExitAnimation());
        }
        chart.setClockwise(false);
        return chart;
    }
    static class MouseHoverAnimation implements EventHandler<Event> {
        static final Duration ANIMATION_DURATION = new Duration(500);
        static final double ANIMATION_DISTANCE = 0.15;
        private double cos;
        private double sin;
        private javafx.scene.chart.PieChart chart;

        public MouseHoverAnimation(javafx.scene.chart.PieChart.Data d, javafx.scene.chart.PieChart chart) {
            this.chart = chart;
            double start = 0;
            double angle = calcAngle(d);
            for (javafx.scene.chart.PieChart.Data tmp : chart.getData()) {
                if (tmp == d) {
                    break;
                }
                start += calcAngle(tmp);
            }
            cos = Math.cos(Math.toRadians(start + angle / 2));
            sin = Math.sin(Math.toRadians(start + angle / 2));
        }
        @Override
        public void handle(Event arg0) {
            Node n = (Node) arg0.getSource();
            double minX = Double.MAX_VALUE;
            double maxX = Double.MAX_VALUE * -1;
            for (javafx.scene.chart.PieChart.Data d : chart.getData()) {
                minX = Math
                        .min(minX, d.getNode().getBoundsInParent().getMinX());
                maxX = Math
                        .max(maxX, d.getNode().getBoundsInParent().getMaxX());
            }
            double radius = maxX - minX;
            TranslateTransitionBuilder.create()
                    .toX((radius * ANIMATION_DISTANCE) * cos)
                    .toY((radius * ANIMATION_DISTANCE) * (-sin))
                    .duration(ANIMATION_DURATION).node(n).build().play();
        }
        private static double calcAngle(javafx.scene.chart.PieChart.Data d) {
            double total = 0;
            for (javafx.scene.chart.PieChart.Data tmp : d.getChart().getData()) {
                total += tmp.getPieValue();
            }
            return 360 * (d.getPieValue() / total);
        }
    }
    static class MouseExitAnimation implements EventHandler<Event> {
        @Override
        public void handle(Event event) {
            TranslateTransitionBuilder.create().toX(0).toY(0)
                    .duration(new Duration(500)).node((Node) event.getSource())
                    .build().play();
        }
    }
}
