package com.jin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin.domain.Dept;
import com.jin.domain.User;
import com.jin.entity.vo.UserVo;
import com.jin.mapper.UserMapper;
import com.jin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private DeptServiceImpl deptService;
    @Override
    public UserVo getOneUser(Integer userId) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getUserId,userId);
        //把整个user对象转成userVo
        UserVo userVo = EntityUtils.toObj(getOne(wrapper), UserVo::new);
        //补充Vo中缺少的属性(从其他表中查询)
        Optional.ofNullable(userVo).ifPresent(this::addDeptNameInfo);
        return userVo;
    }

    //补充userVo方法
    private void addDeptNameInfo(UserVo userVo) {
        LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class)
                .eq(Dept::getDeptId,userVo.getDeptId());
        Dept dept = deptService.getOne(wrapper);
        Optional.ofNullable(dept).ifPresent(e->userVo.setDeptName(e.getDeptName()));
    }

    @Override
    public List<UserVo> getUserByList() {
        return null;
    }

    @Override
    public IPage<UserVo> getUserByPage(Page<User> page) {
        return null;
    }
}
