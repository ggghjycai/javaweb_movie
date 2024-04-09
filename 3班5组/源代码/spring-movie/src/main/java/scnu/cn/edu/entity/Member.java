package scnu.cn.edu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "member")
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "email")
    private String email;        //用户的email
    private String password;     //用户的密码
    private int pm;             //用户是否为会员

    private int userType;


}
