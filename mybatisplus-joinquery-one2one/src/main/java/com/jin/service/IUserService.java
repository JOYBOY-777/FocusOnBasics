package com.jin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jin.domain.User;
import com.jin.entity.vo.UserVo;
import com.jin.mapper.UserMapper;

import java.util.List;

public interface IUserService extends IService<User> {
    //查询单个学生
    UserVo getOneUser(Integer userId);
    //查询多个学生
    List<UserVo> getUserByList();
    //分页查询学生信息
    IPage<UserVo> getUserByPage(Page<User> page);

}
