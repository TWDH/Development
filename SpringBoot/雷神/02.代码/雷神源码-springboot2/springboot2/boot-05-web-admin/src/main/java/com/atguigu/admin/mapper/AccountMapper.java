package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface AccountMapper {


    public Account getAcct(Long id);
}
