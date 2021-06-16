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
public class ImagePath extends Model<ImagePath> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String moduleName;

    private String imagePath;

    private String imageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        return "ImagePath{" +
        ", id=" + id +
        ", moduleName=" + moduleName +
        ", imagePath=" + imagePath +
        ", imageName=" + imageName +
        "}";
    }
}
