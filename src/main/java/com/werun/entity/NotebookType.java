package com.werun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@NoArgsConstructor
@TableName("notebook_type")
public class NotebookType implements Serializable {
    private Integer id;
    private String NotebookType;
    private String username;
    public NotebookType(String notebookType){
        if(notebookType != null){
            this.NotebookType=notebookType;
        }
    }
    public NotebookType (NotebookType notebookType){
        Optional.ofNullable(notebookType).ifPresent(e->{
            this.id=e.getId();
            this.NotebookType=e.getNotebookType();
            this.username = e.getUsername();
        });
    }

}
