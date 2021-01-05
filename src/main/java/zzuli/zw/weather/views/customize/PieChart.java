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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieChart {

    private final static String TYPE1 = "晴";
    private final static String TYPE2 = "雨";
    private final static String TYPE3 = "阴";
    private final static String TYPE4 = "多云";
    private final static String TYPE5 = "霾";
    private final static String TYPE6 = "雪";
    private final static String TYPE7 = "冰雹";
    private final static String TYPE8 = "其它";
    public javafx.scene.chart.PieChart getPieChart(Weather weather){
        Forecast[] forecasts = weather.getData().getForecast();
        int num1 = 0; //晴
        int num2 = 0; //雨
        int num3 = 0; //阴
        int num4 = 0; //多云
        int num5 = 0; //霾
        int num6 = 0; //雪
        int num7 = 0; //冰雹
        int num8 = 0; //其它
        for (Forecast forecast : forecasts) {
            String type = forecast.getType();
            if (type.contains(TYPE1)){
                num1++;
            }else if (type.contains(TYPE2)){
                num2++;
            }else if (type.contains(TYPE3)){
                num3++;
            }else if (type.contains(TYPE4)){
                num4++;
            }else if (type.contains(TYPE5)){
                num5++;
            }else if (type.contains(TYPE6)) {
                num6++;
            } else if (type.contains(TYPE7)){
                num7++;
            }else {
                num8++;
            }
        }
        Map<String,Integer> map = new HashMap<>(8);
        putType(num1, num2, num3, num4, map, TYPE1, TYPE2, TYPE3, TYPE4);
        putType(num5, num6, num7, num8, map, TYPE5, TYPE6, TYPE7, TYPE8);
        List<javafx.scene.chart.PieChart.Data> list = new ArrayList<>();
        map.forEach((key, value) -> {
            //Integer count = finalNum + finalNum1 + finalNum2 + finalNum3 + finalNum4 + finalNum5 + finalNum6 + finalNum7;
            //String temp = "("+value/count +")";
            javafx.scene.chart.PieChart.Data data = new javafx.scene.chart.PieChart.Data(key, value);
            list.add(data);
        });
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections
                .observableArrayList(list);
        javafx.scene.chart.PieChart chart = new javafx.scene.chart.PieChart(pieChartData);
        for (javafx.scene.chart.PieChart.Data d : pieChartData) {
            d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, chart));
            d.getNode().setOnMouseExited(new MouseExitAnimation());
        }
        chart.setClockwise(false);
        return chart;
    }

    private void putType(int num1, int num2, int num3, int num4, Map<String, Integer> map, String type1, String type2, String type3, String type4) {
        if (num1 != 0){
            map.put(type1, num1);
        }
        if (num2 != 0){
            map.put(type2, num2);
        }
        if (num3 != 0){
            map.put(type3, num3);
        }
        if (num4 != 0){
            map.put(type4, num4);
        }
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
