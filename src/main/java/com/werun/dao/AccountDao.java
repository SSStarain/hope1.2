package com.werun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao extends BaseMapper<Account> {
}
