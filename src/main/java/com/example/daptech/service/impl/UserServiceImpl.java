package com.example.daptech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daptech.util.JwtHelper;
import com.example.daptech.response.Result;
import com.example.daptech.entity.User;
import com.example.daptech.request.FindBackPasswordRequest;
import com.example.daptech.request.LoginByPhoneNumRequest;
import com.example.daptech.request.LoginByVerifyCodeRequest;
import com.example.daptech.service.UserService;
import com.example.daptech.mapper.UserMapper;
import com.example.daptech.util.AvatarUpdater;
import com.example.daptech.util.JwtHelper;
import com.example.daptech.util.SmsSender;
import com.example.daptech.util.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
* @author 24333
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-24 10:59:28
*/
@Service
@EnableTransactionManagement
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;
    //获取黑名单管理实例
    @Autowired
    private TokenBlackListService tokenBlackListService;
    //获取验证码发送器
    @Autowired
    private SmsSender smsSender;
    //获取头像上传器
    @Autowired
    private AvatarUpdater avatarUpdater;

    //用户账号密码登录或注册
    @Override
    public Result loginByPhoneNum(@RequestBody LoginByPhoneNumRequest request) {
        //创建User对象
        User user = new User();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        //通过phonenumber判断用户是否注册过
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
        //未注册：将新用户添加到数据库
        if(user1 == null) {
            String s = request.getPhoneNumber().substring(request.getPhoneNumber().length()-4);
            user.setNickname(s);
            user.setStatus(0);
            userMapper.insert(user);
            User user2 = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", user.getPhoneNumber()));
            //给用户分发token
            String token = JwtHelper.createToken(user2.getId(), user2.getPhoneNumber(),user2.getNickname() ,user2.getStatus());
            return Result.success(token);
        }
        //已注册：判断密码是否正确，返回结果
        if(user1.getPassword().equals(request.getPassword())) {
            String token = JwtHelper.createToken(user1.getId(), user1.getPhoneNumber(),user1.getNickname(), user1.getStatus());
            return Result.success(token);
        }else {
//            throw new CustomException(401,"密码错误，请重新输入");
            return Result.error("密码错误，请重新输入");
        }

    }

    //用户验证码登录
    @Override
    public Result loginByVerifyCode(@RequestBody LoginByVerifyCodeRequest request) {
        //根据phonenumber找到该用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", request.getPhoneNumber()));
        //如果不存在该用户，抛出异常
        if(user == null) {
            return Result.error("请输入正确的手机号");
        }
        //如果存在用户，处理剩余业务
        else if (!smsSender.verifyCode(request.getPhoneNumber(), request.getVerifyCode())) {
            return Result.error("验证码错误");
        }
        String token = JwtHelper.createToken(user.getId(), user.getPhoneNumber(),user.getNickname(), user.getStatus());
        return Result.success(token);
    }

    //用户找回密码
    @Override
    public Result findBackPassword(@RequestBody FindBackPasswordRequest request) {
        //根据phonenumber找到该用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", request.getPhonenumber()));
        //如果不存在该用户，抛出异常
        if(user == null) {
            return Result.error("请输入正确的手机号");
        }
        //如果存在用户，处理剩余业务
        else {
            //检查验证码
            if(!smsSender.verifyCode(request.getPhonenumber(),request.getVerifyCode())) {
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
            String token = JwtHelper.createToken(user.getId(), user.getPhoneNumber(),user.getNickname() , user.getStatus());
            return Result.success(token);
        }
    }

    //用户退出登录
    @Override
    public Result logout(@RequestHeader(value = "Authorization", required = true)String jwtToken) {
        //将当前token加入黑名单
        tokenBlackListService.addTokenToBlacklist(jwtToken);
        return Result.success();
    }

    //用户修改昵称
    @Override
    public Result changeNickname(@RequestHeader(value = "Authorization", required = true)String jwtToken, String nickname) {
        Long id = JwtHelper.getIdFromToken(jwtToken);
        User user = userMapper.selectById(id);
        userMapper.updateNickname(id,nickname);
        return Result.success();
    }

    //用户上传头像
    @Override
    public Result updateAvatar(@RequestHeader(value = "Authorization", required = true)String jwtToken, MultipartFile file) throws IOException {
        //校验图片
        Long id = JwtHelper.getIdFromToken(jwtToken);
        System.out.println("id: " + id);
        User user = userMapper.selectById(id);
        if(file == null || file.isEmpty()){
            return Result.error("图片上传失败");
        }
        //上传图片并获取url
        String url = avatarUpdater.uploadAvatar(file);
        //将url上传到数据库
        userMapper.updateAvatarUrl(id,url);
//        Map<String,Object> map = new HashMap<>();
//        map.put("avatarUrl",url);
//        map.put("id",id);
//        userMapper.updateAvatarUrl(map);
        return Result.success();
    }

    //发送验证码
    @Override
    public Result sendSms(String phonenumber) {
        //根据phonenumber找到该用户
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("phone_number", phonenumber));
        //如果不存在该用户，抛出异常
        if(user == null) {
            //throw new CustomException(401,"请输入正确的手机号");
            return Result.error("请输入正确的手机号");
        }
        //如果存在用户，发送验证码
        smsSender.sendVerificationCode(phonenumber);
        return Result.success();
    }
}




