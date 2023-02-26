package com.jin.entity.vo;

import com.jin.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserVo extends User {
    //组合属性把部门名字组合进来
    private String deptName;

    public UserVo(User user){
        super(user);
    }
}
