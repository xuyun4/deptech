package com.example.deptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.deptech.entity.Result;
import com.example.deptech.entity.User;
import com.example.deptech.mapper.UserMapper;
import com.example.deptech.request.findBackPasswordRequest;
import com.example.deptech.request.loginByPhoneNumRequest;
import com.example.deptech.request.loginByVerifyCodeRequest;
import com.example.deptech.service.UserService;
import com.example.deptech.util.JwtHelper;
import com.example.deptech.util.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
* @author 24333
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-24 10:59:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    //用户账号密码登录或注册
    @Override
    public Result loginByPhoneNum(@RequestBody loginByPhoneNumRequest request) {
        //创建User对象
        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        //通过phoneNumber判断用户是否注册过
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
        //未注册：将新用户添加到数据库
        if(user1 == null) {
            userMapper.insert(user);
            //给用户分发token
            String token = JwtHelper.createToken(user.getId(), user.getPhoneNumber(), user.getPassword());
            return Result.success(token);
        }
        //已注册：判断密码是否正确，返回结果
        if(user1.getPassword().equals(request.getPassword())) {
            String token = JwtHelper.createToken(user.getId(), user.getPhoneNumber(), user.getPassword());
            return Result.success(token);
        }else {
//            throw new CustomException(401,"密码错误，请重新输入");
            return Result.error("密码错误，请重新输入");
        }

    }

    //用户验证码登录
    @Override
    public Result loginByVerifyCode(@RequestBody loginByVerifyCodeRequest request) {
        return null;
    }

    //用户找回密码
    @Override
    public Result findBackPassword(@RequestBody findBackPasswordRequest request) {
        //根据phonenumber找到该用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", request.getPhonenumber()));
        //如果不存在该用户，抛出异常
        if(user == null) {
//            throw new CustomException(401,"请输入正确的手机号");
            return Result.error("请输入正确的手机号");
        }
        //如果存在用户，处理剩余业务
        else {
            //检查验证码
            String verifyCode = "123456";
            if(!verifyCode.equals(request.getVerifyCode())) {
//                throw new CustomException(401,"验证码错误");
                return Result.error("验证码错误");
            }
            //检查密码是否相等且符合格式
            if(!request.getPassword().equals(request.getConfirmPassword())) {
//                throw new CustomException(401,"两次密码输入不一致");
                return Result.error("两次密码输入不一致");
            }
            //都通过颁发token并将用户信息存入数据库
            user.setPassword(request.getPassword());
            userMapper.updateById(user);
            String token = JwtHelper.createToken(user.getId(), user.getPhoneNumber(), user.getPassword());
            return Result.success(token);

        }
    }

    //用户退出登录
    @Override
    public Result logout(@RequestHeader("Authorization")String jwtToken) {
        //获取token，并删除"bearer"前缀
        String token = jwtToken.replace("Bearer ", "");
        //将当前token加入黑名单
        TokenBlackListService.addTokenToBlacklist(token);
        return Result.success();
    }
}




