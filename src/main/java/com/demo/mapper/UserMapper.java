package com.demo.mapper;

import com.demo.eneity.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserMapper {
    User getUser(Integer id);
}
