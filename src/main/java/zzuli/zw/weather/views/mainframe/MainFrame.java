package zzuli.zw.weather.views.mainframe;

import com.sun.javafx.application.LauncherImpl;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.service.events.MenuEvent;
import zzuli.zw.weather.service.events.UpdateEvent;
import zzuli.zw.weather.service.weatherservice.WeatherTask;
import zzuli.zw.weather.service.yiyan.DayWordTask;
import zzuli.zw.weather.utils.GetResources;
import zzuli.zw.weather.views.baseframe.BaseAnchorPane;
import zzuli.zw.weather.views.baseframe.BaseScene;
import zzuli.zw.weather.views.baseframe.BaseStage;
import zzuli.zw.weather.views.customize.AlertErrorPane;
import zzuli.zw.weather.views.customize.AlertFrame;
import zzuli.zw.weather.views.customize.MainStackPane;
import zzuli.zw.weather.views.customize.TopHBox;
import java.util.Objects;

/**
 * 主界面
 */
public class MainFrame extends Application {

    private WeatherTask task = null;       //初始化天气信息
    private DayWordTask dayWordTask = null;    //初始化一言信息
    private BooleanProperty ready = new SimpleBooleanProperty(false);  //预加载界面

    /**
     * 初始化方法，在主界面启动之间进行初始化工作
     */
    @Override
    public void init() {
        //初始化一言
        dayWordTask = new DayWordTask();
        dayWordTask.start();
        //初始化天气信息
        task = new WeatherTask(ready);
        task.start();
    }
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainFrame.class,InitAppPreloader.class,args);
    }
    @Override
    public void start(Stage primaryStage) {
        //将当前界面存入ManageNode中进行管理
        BeanFactory.getManageNode().put("mainFrame",this);
        //初始化ContextMenu事件
        new MenuEvent();
        BaseStage stage = new BaseStage();
        //通过预加载器对初始化的任务进行监听
        ready.addListener((observable, oldValue, newValue) -> {
            //如果任务已经完成，将预加载界面关闭，将当前Stage显示
            if(Boolean.TRUE.equals(newValue)){
                Platform.runLater(() -> {
                    ((Stage)BeanFactory.getManageNode().get("Preloader")).close();
                    stage.show();
                });
            }
        });
        stage.setSize(650,600);
        StackPane stackPane = new StackPane();
        BaseAnchorPane pane = new BaseAnchorPane();
        stackPane.getChildren().add(pane);
        //设置图标
        stage.getIcons().add(new Image(Objects.requireNonNull(GetResources.getImagesPath("title.png"))));
        BaseScene scene = new BaseScene(stackPane,stage);
        //将当前stage存入ManageUI进行管理
        BeanFactory.getManageUI().put("mainFrame", stage);
        //通过封装的方法加载css样式
        scene.getStyles("css/background.css","css/menu.css");
        //为特定组件加载样式
        pane.getStyleClass().add("image");
        stackPane.getStyleClass().add("stackPane");
        //top部分
        TopHBox top = new TopHBox(375,650);
        top.getStyleClass().add("topBox");
        pane.getChildren().add(top);
        //主要区域部分
        MainStackPane mainStackPane = new MainStackPane();
        //设置主区域的显示位置
        AnchorPane.setTopAnchor(mainStackPane,80.0);
        AnchorPane.setLeftAnchor(mainStackPane,220.0);
        pane.getChildren().addAll(mainStackPane);
        //将主要区域加如ManageNode进行管理
        BeanFactory.getManageNode().put("mainStackPane",mainStackPane);
        stage.setScene(scene);
        //设置启动动画
        FadeTransition fd = new FadeTransition();
        fd.setNode(stackPane);
        fd.setDuration(Duration.seconds(1.5));
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.setAutoReverse(true);
        fd.setCycleCount(1);
        fd.play();
        //通过事件监听更新界面
        task.valueProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(() ->{
            UpdateEvent event = new UpdateEvent();
            event.update(newValue);
        }));
        //如果任务初始化中出现异常则执行
        task.exceptionProperty().addListener((observable, oldValue, newValue) -> {
            ((Stage)BeanFactory.getManageNode().get("Preloader")).close();
            stage.show();
            AlertFrame alertFrame = new AlertFrame();
            AlertErrorPane errorPane = new AlertErrorPane();
            errorPane.setErrorMessage("网络连接失败！");
            alertFrame.initModality(Modality.APPLICATION_MODAL);
            alertFrame.initOwner(stage);
            alertFrame.show();
        });
        //通过事件监听更新一言部分的界面
        dayWordTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            UpdateEvent updateEvent = new UpdateEvent();
            updateEvent.updateWord(newValue);
        });
    }
}
