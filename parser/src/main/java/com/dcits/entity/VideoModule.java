package com.dcits.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-19
 */
@Data
public class VideoModule extends Model<VideoModule> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "VideoModule{" +
        ", id=" + id +
        "}";
    }
}
