package com.werun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.entity.NoteBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteBookDao extends BaseMapper<NoteBook> {
    @Select("SELECT id,notebook_title,notebook_type,notebook_state,notebook_created_time,notebook_content,notebook_description" +
            ",notebook_edited_time FROM notebook WHERE id=#{id}")
    NoteBook selectById(@Param("id")Integer id);
    @Select("SELECT char_length(notebook_content) FROM notebook WHERE username=#{username}")
    List<Integer> selectWords(@Param("username")String username);
}
