package zzuli.zw.weather.views.baseframe;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * BaseStage,取消舞台的装饰内容
 */
public class BaseStage extends Stage {

    /**
     * 取消装饰
     */
    public BaseStage(){
        this.initStyle(StageStyle.TRANSPARENT);
    }

    /**
     * 设置舞台效果
     * @param style 舞台效果
     */
    public BaseStage(StageStyle style) {
        super(style);
    }


    public void setSize(double width,double height){
        setWidth(width);
        setHeight(height);
    }
}
