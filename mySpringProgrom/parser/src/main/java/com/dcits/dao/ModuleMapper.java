package com.dcits.dao;

import com.dcits.entity.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/18 16:49
 * @Version 1.0
 * @Since study
 */
public interface ModuleMapper {
    List<Module> selectAll();
    void insertModule(Module module);
    List<Module> slectModuleById(@Param("start") int start, @Param("end") int end);
    void updateByModuleId(Module module);
}
