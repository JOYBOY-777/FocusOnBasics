package com.jin.controller;

import com.jin.entity.vo.UserVo;
import com.jin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/detail/{userId}")
    public AjaxResult detail(@PathVariable Integer userId){
        return AjaxResult.success(userService.getOneUser(userId));
    }

    /**
     * 不加筛选条件
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(){
        return AjaxResult.success(userService.getUserByList());
    }

    /**
     * 添加筛选条件通过age筛选
     * @param age
     * @return
     */
    @GetMapping("/list/age/{age}")
    public AjaxResult listForAge(@PathVariable Integer age){
        System.out.println(age);
        return AjaxResult.success(userService.getUserListByAge(age));
    }

//    @GetMapping("/list/{age}")
//    public List<UserVo> listForAge1(@PathVariable Integer age){
//        System.out.println(age);
//        return userService.getUserListByAge(age);
//    }
    /**
     *筛选部门名称为tomcat的
     */
    @GetMapping("/list/deptName/{deptName}")
    public AjaxResult listForAge(@PathVariable String deptName){
        System.out.println(deptName);
        return AjaxResult.success(userService.getUserListByDeptName(deptName));
    }
}
