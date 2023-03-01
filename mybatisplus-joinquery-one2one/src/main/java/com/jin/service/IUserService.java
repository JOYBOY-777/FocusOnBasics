package com.jin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jin.domain.User;
import com.jin.entity.vo.UserVo;
import com.jin.mapper.UserMapper;

import java.util.List;

public interface IUserService extends IService<User> {
    //查询单个用户
    UserVo getOneUser(Integer userId);
    //查询多个用户

    /**
     * 无条件的把学生对应的部门查询出来
      * @return
     */
    List<UserVo> getUserByList();

    /**
     * 有条件的用户查询（并且条件在主表中user中）比如说年龄大于1岁的用户
     */
    List<UserVo> getUserListByAge(Integer age);


    /**
     * 查询部门名称为tomcat的部门，通过从表信息筛选
     * @param name
     * @return
     */
    List<UserVo> getUserListByDeptName(String name);

    /**
     *
     * @param page
     * @return
     */
    //分页查询用户信息
    IPage<UserVo> getUserByPage(Page<User> page);

}
