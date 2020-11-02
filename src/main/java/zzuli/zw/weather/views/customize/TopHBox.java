package zzuli.zw.weather.views.customize;

import javafx.scene.layout.HBox;

/**
 * @ClassName: TopHBox
 * @date: 2020/10/8 17:05
 * @author 索半斤
 * @Description: 顶部信息
 */
public class TopHBox extends HBox {

    public TopHBox(double spacing,double width) {
        this.getChildren().add(left());
        this.setSpacing(spacing);
        this.getChildren().add(right());
        this.setMaxSize(width,40);
        this.setMinSize(width,40);
    }

    /**
     * 左侧标题
     *
     * @return HBox
     */
    public HBox left() {
        return new TitleBox();
    }

    public HBox right() {
        return new CloseAndMin();
    }
}
