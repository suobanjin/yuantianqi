package zzuli.zw.weather.views.mainframe;
import javafx.application.Preloader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import zzuli.zw.weather.factory.BeanFactory;
import zzuli.zw.weather.utils.GetResources;
import zzuli.zw.weather.views.baseframe.BaseAnchorPane;
import zzuli.zw.weather.views.baseframe.BaseScene;
import zzuli.zw.weather.views.baseframe.BaseStage;
import zzuli.zw.weather.views.customize.WelcomeView;

import java.util.Objects;

public class InitAppPreloader extends Preloader {
    private Logger logger = Logger.getLogger(this.getClass());
    @Override
    public void start(Stage primaryStage) {
        //这里可以看到和Application一样，也有舞台，我们可以定制自己的界面
        BaseStage stage = new BaseStage();
        stage.getIcons().add(new Image(Objects.requireNonNull(GetResources.getImagesPath("title.png"))));
        stage.setSize(650,600);
        StackPane stackPane = new StackPane();
        BaseAnchorPane pane = new BaseAnchorPane();
        stackPane.getChildren().add(pane);
        BeanFactory.getManageNode().put("Preloader",stage);
        BaseScene scene = new BaseScene(stackPane,stage);
        scene.getStyles("css/background.css");
        pane.getStyleClass().add("image");
        stackPane.getStyleClass().add("stackPane");
        WelcomeView welcomeView = new WelcomeView();
        AnchorPane.setTopAnchor(welcomeView,140.0);
        AnchorPane.setLeftAnchor(welcomeView,250.0);
        pane.getChildren().add(welcomeView);
        stage.setScene(scene);
        stage.show();
        logger.info("初始化完成！");
    }

}
