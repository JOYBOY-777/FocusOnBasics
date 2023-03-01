package com.jin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@TableName("tb_dept")
public class Dept extends Model<Dept> {
    private Integer deptId;
    private String deptName;
    private Integer staff;
    private String tel;

    public Dept(Dept dept){
        //首先先进行判空，不是空的话就进行初始化赋值
        Optional.ofNullable(dept).ifPresent(e->{
            this.deptId = e.getDeptId();
            this.deptName = e.getDeptName();
        });
    }
}
