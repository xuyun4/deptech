/*
package com.example.deptech.controller;

import com.worldsverre.entity.Result;
import com.worldsverre.entity.phonenumber.PendingPhone;
import com.worldsverre.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
*/
/*
 * 这个类作为总的Phone类用作完成
 * 1用户自定义黑白名单
 * 2用户标记骚扰电话更新数据库
 * 3判断是否在用户标记库中，且次数合适
 * 4自动新增号码到号码库
 *//*

public class UsersController {
    @Autowired
    private UsersService usersService;

    */
/**
     * 将号码递送到用户号码库进行查询
     * 若无查询结果则新增该号码，并将times属性置为1
     * 若有查询结果，将times属性+1
     * @param phone
     * @return result
     *//*

    @PutMapping("/phone/users/mark/{phone}")
    public Result updateByUsers(@PathVariable String phone){
        log.info("用户标记了电话{}为骚扰电话",phone);
        usersService.updateByUsers(phone);
        return Result.success();
    }

    */
/**
     * 判断曾被多少用户标记
     * 如果超过20则视作骚扰电话
     * @param phone
     * @return true / false
     *//*

    public boolean hasPhone(String phone){
        PendingPhone pendingPhone = usersService.getByPhone(phone);
        if (pendingPhone != null && pendingPhone.getTimes() > 20) {
            return true;
        }
        return false;
    }

}
*/
