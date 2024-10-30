/*
package com.example.deptech.controller;

import com.worldsverre.entity.MSG;
import com.worldsverre.entity.Result;
import com.worldsverre.service.PhoneCNService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PhoneCNControllerNew {

    @Autowired
    private PhoneCNService phoneCNService;
    @Autowired
    private UsersController usersController;

    //删除号码 需要吗？NO

    //查询号码 需要吗？NO

    //修改号码 需要吗？NO

    //新增号码 需要吗？NO

    //新增号码（扩充数据库大小） 需要吗？YES

    //骚扰电话判断 需要吗？OF COURSE 如何进行：接收号码请求，返回msg
    @GetMapping("/phone/cn/api/{phone}")
    public Result api(@PathVariable String phone){
        log.info("调用api查询了号码:{}的情况",phone);
        int label = judge(phone);
        if(label == 1){
            MSG msg = new MSG((short) 1,"骚扰电话");
            return Result.success(msg);
        }else if(usersController.hasPhone(phone)){
            MSG msg = new MSG((short)1,"多个用户标记");
            return Result.success(msg);
        }else {
            MSG msg = phoneCNService.api(phone);
            return Result.success(msg);
        }
    }

    */
/**
     * 本方法用于初步筛选
     * 核心
     * 判断是否拦截号码的中国标准
     * 条件：
     * 400开头，为企业电话，需要拦截？
     * 95开头过长必诈骗，大多为推销
     * 170/171开头直接拦截
     * 00开头在中国必诈骗
     *
     * @param phone 电话号码
     * @return 1 如果号码应该被拦截，返回1
     *         0 如果号码不应该被拦截，返回0
     *//*

    public int judge(String phone) {
        // 检查电话号码是否为空或长度不符合要求
        if (phone == null || phone.length() < 3) {
            return 0;
        }

        // 400开头的企业电话，假设我们决定拦截
        if (phone.startsWith("400")) {
            return 1;
        }

        // 95开头的号码，如果长度大于3位，则认为是诈骗或推销
        if (phone.startsWith("95")) {
            return 1;
        }

        // 170/171开头的号码直接拦截
        if (phone.startsWith("170") || phone.startsWith("171")) {
            return 1;
        }

        // 00开头的号码在中国认为是诈骗
        if (phone.startsWith("00")) {
            return 1;
        }

        // 如果以上条件都不满足，则不拦截
        return 0;
    }



}
*/
