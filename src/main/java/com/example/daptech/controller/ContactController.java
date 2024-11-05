package com.example.daptech.controller;

import com.example.daptech.response.Result;
import com.example.daptech.entity.dto.ContactDto;
import com.example.daptech.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "联系人列表控制器")
public class ContactController {

        private final ContactService contactService;

        //更新联系人表到后端
        @PutMapping("/updateContact")
        @Operation(summary = "后端更新联系人")
        public Result updateContact(@RequestHeader("Authorization")String token, @Valid @RequestBody List<ContactDto> contactDtoList) {
            return contactService.updateContact(token,contactDtoList);
        }
        //查询联系人表,返回前端
        @GetMapping("/getContact")
        @Operation(summary = "本地更新联系人")
        public Result getContact(@RequestHeader("Authorization")String token) {
                return contactService.getContact(token);
        }
}
