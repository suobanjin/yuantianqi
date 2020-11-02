package zzuli.zw.weather.views.baseframe;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import zzuli.zw.weather.utils.GetResources;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置默认Scene为透明效果,设置拖拽事件
 */
public class BaseScene extends Scene {
    private double leftX = 0;
    private double topY = 0;
    public BaseScene(Parent root) {
        super(root);
        setFill(Paint.valueOf("#ffffff00"));
        setOnMousePressed(event -> {
            leftX = event.getScreenX() - getX();
            topY = event.getY() - getY();
        });
    }
    public BaseScene(Parent root, Stage stage) {
        super(root);
        setFill(Paint.valueOf("#ffffff00"));
        //设置拖拽事件
        setOnMousePressed(event -> {
            setCursor(Cursor.HAND);
            leftX = event.getScreenX() - stage.getX();
            topY = event.getScreenY() - stage.getY();
        });
        setOnMouseDragged(event -> {
            setCursor(Cursor.HAND);
            stage.setX(event.getScreenX()-leftX);
            stage.setY(event.getScreenY()-topY);
        });
        setOnMouseReleased(event -> {
            setCursor(Cursor.DEFAULT);
        });
        setOnMouseDragExited(event -> {
            setCursor(Cursor.DEFAULT);
        });
    }

    public BaseScene(Parent root, double width, double height) {
        super(root, width, height);
        setFill(Paint.valueOf("#ffffff00"));
    }

    public BaseScene(Parent root, Paint fill) {
        super(root, fill);
    }

    public BaseScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public BaseScene(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        setFill(Paint.valueOf("#ffffff00"));
    }

    public BaseScene(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
        setFill(Paint.valueOf("#ffffff00"));
    }

    /**
     * 封装获得css样式的方法
     * @param fileName 文件路径
     */
    public void getStyle(String fileName){
        if (fileName == null || fileName.trim().isEmpty()){
            return;
        }
        getStylesheets().add(GetResources.getResourcePath(fileName));
        setFill(Paint.valueOf("#ffffff00"));
    }

    /**
     * 封装获得多个css样式的方法
     * @param fileName 多个文件路径
     */
    public void getStyles(String... fileName){
        if (fileName == null){
            return;
        }
        List<String> list = new ArrayList<>();
        for (String s : fileName) {
            list.add(GetResources.getResourcePath(s));
        }
        getStylesheets().addAll(list);
        setFill(Paint.valueOf("#ffffff00"));
    }


}
