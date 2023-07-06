package com.werun.controller;

import com.werun.entity.Account;
import com.werun.entity.NoteBook;
import com.werun.utils.R;
import com.werun.service.INotebookService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notebooks")
public class NoteController {
    @Autowired
    private INotebookService iNotebookService;

    /*
     *1.2.1.1
     * 新增笔记分类
     */
    @GetMapping("/saveType")
    public R saveNotebookType(@RequestParam String notebookType){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(iNotebookService.saveType(notebookType,account.getUsername()));
    }
    /*
     *1.2.1.2
     * 展示笔记分类
     */
    @GetMapping("/showType")
    public R showNotebookType(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(true,iNotebookService.showType(account.getUsername()));
    }
    /*
     *1.2.2
     * 新增笔记
     */
    @PostMapping("/save")
    public R save (@RequestBody NoteBook noteBook){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        noteBook.setUsername(account.getUsername());
        return new R(iNotebookService.save(noteBook));
    }
    /*
     *1.2.2
     * 查看笔记详情
     */
    @GetMapping("/showNote")
    public R showOneNote(@RequestParam Integer id){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(true,iNotebookService.selectById(id, account.getUsername()));
    }
    /*
     *1.2.3
     * 删除笔记
     */
    @DeleteMapping("/delete")
    public R delete(@RequestParam("id") Integer id){
        return new R(iNotebookService.removeById(id));
    }
    /*
     *1.2.3.1
     * 批量删除笔记
     */
    @DeleteMapping("/deleteAll")
    public R deleteAll(@RequestParam(value = "id") List<Integer> id){
        return new R(iNotebookService.removeByIds(id));
    }
    /*
     *1.2.4.1
     * 修改笔记状态
     */
    @PutMapping("/modifyState")
    public R modifyState(@RequestParam("id") Integer id,@RequestParam("state") Integer state){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(iNotebookService.modifyState(id,state, account.getUsername()));
    }
    /*
     *1.2.4.2
     * 批量修改笔记状态
     */
    @PutMapping("/modifyStateAll")
    public R modifyState(@RequestParam(value = "id") List<Integer> id,@RequestParam("state") Integer state){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(iNotebookService.modifyState(id,state, account.getUsername()));
    }
    /*
     *1.2.4.3
     * 编辑笔记
     */
    @PutMapping("/modifyAll")
    public R modifyAll(@RequestParam("id")Integer id,@RequestBody NoteBook noteBook){
        noteBook.setId(id);
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(iNotebookService.modifyAll(id,noteBook, account.getUsername()));
    }
    /*
     *1.2.5
     * 主页：查看所有笔记
     */
    @GetMapping("/showAllNote")
    public R showAllNote(){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(true,iNotebookService.selectAll(account.getUsername()));
    }

    /*
     *1.2.6
     * 模糊查询，标题搜索
     */
    @GetMapping("/getNotebookByTitle")
    public R getNotebookByTitle(@RequestParam("notebookTitle") String notebookTitle){
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();
        return new R(true,iNotebookService.selectByTitle(notebookTitle, account.getUsername()));
    }


}
