package com.dcits.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-21
 */
public class ImageModule extends Model<ImageModule> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imageModule;

    private String httpUrl;

    private String sourceImage;

    private String moduleName;

    private String imageName;

    private String status;

    private String statusInfo;

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageModule() {
        return imageModule;
    }

    public void setImageModule(String imageModule) {
        this.imageModule = imageModule;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(String sourceImage) {
        this.sourceImage = sourceImage;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ImageModule{" +
        ", id=" + id +
        ", imageModule=" + imageModule +
        ", httpUrl=" + httpUrl +
        ", sourceImage=" + sourceImage +
        ", moduleName=" + moduleName +
        ", imageName=" + imageName +
        "}";
    }
}
