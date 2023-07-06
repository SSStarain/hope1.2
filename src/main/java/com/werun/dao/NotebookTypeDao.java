package com.werun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.entity.NotebookType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NotebookTypeDao extends BaseMapper<NotebookType> {
    @Select("SELECT notebook_type FROM notebook_type WHERE username=#{username}")
    List<String> selectAllByNotebookTypeStrings(@Param("username")String username);
}
