package com.werun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.dao.NoteBookDao;
import com.werun.dao.NotebookTypeDao;
import com.werun.entity.NoteBook;
import com.werun.entity.NotebookType;
import com.werun.service.INotebookService;
import com.werun.utils.MyCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotebookServiceImpl extends ServiceImpl<NoteBookDao, NoteBook> implements INotebookService {

    @Autowired
    private NoteBookDao noteBookDao;
    @Autowired
    private NotebookTypeDao notebookTypeDao;


    @Override
    public Boolean modifyState(Integer id,Integer state,String username) {
        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("notebook_state",state);
        return noteBookDao.update(null,updateWrapper)>0;
    }

    @Override
    public Boolean modifyState(List<Integer> ids, Integer state,String username) {
        for (Integer id:ids) {
            UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("id",id).set("notebook_state",state);
            noteBookDao.update(null,updateWrapper);
        }
        return true;
    }

    @Override
    public Boolean modifyAll(Integer id,NoteBook noteBook,String username) {
//        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
//        updateWrapper.eq("id",id).setEntity(noteBook);
        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("notebook_state",noteBook.getNotebookState()).set("notebook_title",noteBook.getNotebookTitle()).set("notebook_type",noteBook.getNotebookType()).set("notebook_content",noteBook.getNotebookContent()).set("notebook_description",noteBook.getNotebookDescription()).set("notebook_edited_time",new MyCalendar().getTime());
        System.out.println(Calendar.getInstance(Locale.CHINA));
        return noteBookDao.update(null,updateWrapper)>0;

    }

    @Override
    public Boolean saveType(String notebookType,String username) {
        NotebookType oneType=new NotebookType(notebookType);
        oneType.setUsername(username);
        return notebookTypeDao.insert(oneType)>0;
    }

    @Override
    public List<NoteBook> selectByTitle(String notebookTitle,String username) {
        QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username).like("notebook_title",notebookTitle);
        return noteBookDao.selectList(queryWrapper);
    }

    @Override
    public List<String> showType(String username) {
        return notebookTypeDao.selectAllByNotebookTypeStrings(username);
    }


    @Override
    public List<NoteBook> selectAll(String username) {
            QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",username);
            return noteBookDao.selectList(queryWrapper);
    }

    @Override
    public NoteBook selectById(Integer id,String username) {
        return noteBookDao.selectById(id);
    }

    @Override
    public Integer sumNoteName(String username) {
        QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return noteBookDao.selectCount(queryWrapper);
    }

    @Override
    public Integer sumWords(String username) {
        Integer sum = 0;
        List<Integer> words= noteBookDao.selectWords(username);
        for(Integer x:words) {
            sum += x;
        }
        return sum;
    }

}
