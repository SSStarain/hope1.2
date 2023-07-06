package com.werun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.entity.NoteBook;


import java.util.List;

public interface INotebookService extends IService<NoteBook> {
    Boolean modifyState(Integer id,Integer state,String username);
    Boolean modifyState(List<Integer> ids,Integer state,String username);
    Boolean modifyAll(Integer id,NoteBook noteBook,String username);
    Boolean saveType(String notebookType,String username);
    List<NoteBook> selectByTitle(String notebookTitle,String username);
    List<String> showType(String username);
    List<NoteBook> selectAll(String username);
    NoteBook selectById(Integer id,String username);
    Integer sumNoteName(String username);
    Integer sumWords(String username);

}
