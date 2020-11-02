package zzuli.zw.weather.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


/**
 * 根据不同的场景初始化ImageView对象
 */
public class ImageViewUtils {

    /**
     * 为已经创建的ImageView对象设置长宽以及图像
     * @param view  已经创建的ImageView对象
     * @param height  高度
     * @param width   宽度
     * @param imageName  图片名称
     */
    public static void setImage(ImageView view,int height,int width,String imageName){
        if (isEmpty(imageName)){
            return;
        }
        view.setImage(new Image(Objects.requireNonNull(GetResources.getImagesPath(imageName))));
        view.setFitHeight(height);
        view.setFitWidth(width);
    }

    /**
     * 为已经创建的ImageView对象设置长宽
     * @param view  已经创建的ImageView对象
     * @param height  高度
     * @param width  宽度
     */
    public static void setImage(ImageView view,int height,int width){
        view.setFitHeight(height);
        view.setFitWidth(width);
    }

    /**
     * 根据给定的长度和宽度以及图片名称初始化ImageView对象并返回
     * @param height  高度
     * @param width   宽度
     * @param imageName  图片名称
     * @return  ImageView
     */
    public static ImageView setImage(int height,int width,String imageName){
        if (isEmpty(imageName)){
            return null;
        }
        ImageView view = new ImageView(Objects.requireNonNull(GetResources.getImagesPath(imageName)));
        view.setFitHeight(height);
        view.setFitWidth(width);
        return view;
    }

    /**
     * 根据给定的长度和宽度以及图片名称初始化ImageView对象并返回
     * @param heightAndWidth 高度和宽度
     * @param imageName 图片名称
     * @return  ImageView
     */
    public static ImageView setImage(int heightAndWidth,String imageName){
        if (isEmpty(imageName)){
            return null;
        }
        ImageView view = new ImageView(Objects.requireNonNull(GetResources.getImagesPath(imageName)));
        view.setFitHeight(heightAndWidth);
        view.setFitWidth(heightAndWidth);
        return view;
    }


    /**
     * 根据给定的图片名称初始化ImageView对象并返回
     * @param imageName 图片名称
     * @return  ImageView
     */
    public static ImageView setImage(String imageName){
        if (isEmpty(imageName)){
            return null;
        }
        return new ImageView(Objects.requireNonNull(GetResources.getImagesPath(imageName)));
    }


    private static boolean isEmpty(String string){
        return string == null || string.trim().isEmpty();
    }
}
