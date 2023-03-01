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
import com.jin.mapper.DeptMapper;
import com.jin.mapper.UserMapper;
import com.jin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    DeptMapper deptMapper;

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
        //查询用户表的数据
        List<User> userList = this.list();
        List<UserVo> userVos = EntityUtils.toList(userList, UserVo::new);
        //拿到里面的id集合
        Set<Integer> deptIds = EntityUtils.toSet(userVos, UserVo::getDeptId);
        if (deptIds.size()>0) {
            //List<Dept> depts = deptMapper.selectBatchIds(deptIds);
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds)
                    .select();
            List<Dept> depts1 = deptMapper.selectList(wrapper);
            Map<Integer, Dept> deptMap = EntityUtils.toMap(depts1, Dept::getDeptId, e -> e);
            //部门表空属性赋值
            for (UserVo userVo : userVos) {
                Dept dept = deptMap.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }
        }



        return userVos;
    }

    @Override
    public List<UserVo> getUserListByAge(Integer age) {
        //筛选年龄大于1岁的
        LambdaQueryWrapper<User> gt = Wrappers.lambdaQuery(User.class).gt(User::getAge, age);
        List<User> userList = this.list(gt);
        System.out.println("======"+userList.get(0).getAge());
        List<UserVo> userVos = EntityUtils.toList(userList, UserVo::new);
        //把userVo中的deptid拿出来
        Set<Integer> deptIds = EntityUtils.toSet(userVos, UserVo::getDeptId);
        if (deptIds.size()>0){
            //通过这些ids查询dept实体
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class).in(Dept::getDeptId, deptIds)
                    .select();
            List<Dept> deptList = deptMapper.selectList(wrapper);
            //填充属性把list转成map
            Map<Integer, Dept> deptMap = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
            for (UserVo userVo : userVos) {
                Dept dept = deptMap.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }

        }
        return userVos;
    }

    @Override
    public List<UserVo> getUserListByDeptName(String name) {
        System.out.println("111111111111111111111111111111111111111");
        List<User> userList = this.list();
        List<UserVo> userVos = EntityUtils.toList(userList, UserVo::new);
        //把userVo中的deptid拿出来
        Set<Integer> deptIds = EntityUtils.toSet(userVos, UserVo::getDeptId);
        System.out.println("====================="+deptIds.size());
        if (deptIds.size()>0){
            //通过这些ids查询dept实体
            LambdaQueryWrapper<Dept> wrapper = Wrappers.lambdaQuery(Dept.class)
                    .and(i->i.eq(Dept::getDeptName,name))
                    .in(Dept::getDeptId, deptIds)
                    .select();
            List<Dept> deptList = deptMapper.selectList(wrapper);
            //填充属性把list转成map
            Map<Integer, Dept> deptMap = EntityUtils.toMap(deptList, Dept::getDeptId, e -> e);
            for (UserVo userVo : userVos) {
                Dept dept = deptMap.get(userVo.getDeptId());
                userVo.setDeptName(dept.getDeptName());
                userVo.setStaff(dept.getStaff());
                userVo.setTel(dept.getTel());
            }

        }
        return userVos;
    }

    @Override
    public IPage<UserVo> getUserByPage(Page<User> page) {
        return null;
    }
}
