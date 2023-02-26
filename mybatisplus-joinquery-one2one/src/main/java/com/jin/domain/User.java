package com.jin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("tb_user")
public class User extends Model<User> {
    private Integer userId;
    private String userName;
    //一对一实际上是一对多的一种特殊的形式，记住单一的id挂载在多的表上
    private Integer deptId;

    //有参构造方法，里面进行判空之后在进行初始化
    public User (User user){
        if(user != null){
            this.userId = user.getUserId();
            this.userName = user.getUserName();
            this.deptId = user.getDeptId();
        }
    }
}
