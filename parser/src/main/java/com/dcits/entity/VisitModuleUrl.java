package com.dcits.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-23
 */
public class VisitModuleUrl extends Model<VisitModuleUrl> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String moduleName;

    private String moduleUrl;

    private String visit;

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

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "VisitModuleUrl{" +
        ", id=" + id +
        ", moduleName=" + moduleName +
        ", moduleUrl=" + moduleUrl +
        ", visit=" + visit +
        "}";
    }
}
