package com.dcits.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-25
 */
public class ImageSourceIndex extends Model<ImageSourceIndex> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imageSourceUrl;

    private String httpUrl;

    private String imageName;

    private String imageModule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageSourceUrl() {
        return imageSourceUrl;
    }

    public void setImageSourceUrl(String imageSourceUrl) {
        this.imageSourceUrl = imageSourceUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageModule() {
        return imageModule;
    }

    public void setImageModule(String imageModule) {
        this.imageModule = imageModule;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ImageSourceIndex{" +
        ", id=" + id +
        ", imageSourceUrl=" + imageSourceUrl +
        ", httpUrl=" + httpUrl +
        ", imageName=" + imageName +
        ", imageModule=" + imageModule +
        "}";
    }
}
