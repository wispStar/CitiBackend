package com.bondsales.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2024-04-09 23:20:27
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bond_user")
public class User  {
    //主键    
    @TableId
    private Long id;

    //用户名
    private String username;
    //密码
    private String password;
    //用户登录成功后由系统颁发的身份验证令牌
    private String token;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //登录状态: 0代表未登录，1代表已登录
    private String status;
    //地址
    private String address;
    
}
