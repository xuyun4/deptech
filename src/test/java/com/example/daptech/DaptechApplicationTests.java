
package com.example.daptech;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.example.daptech.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DaptechApplicationTests {


    @Test
    void test() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVc2VyIiwiZXhwIjoxNzM3MTg3NjE4LCJpZCI6NCwicGhvbmVudW1iZXIiOiIxNTI3NTE2NTc3OCIsIm5pY2tuYW1lIjoidV91dGFhIiwic3RhdHVzIjowfQ.DggHDAvC5VLBI55g-TbxXc1bfdAZd5F9O4C4DWSJjyh5PAwZkuWaNMZQKhEbShDiuOLJgIt1H20m8dJlcMuhFQ";
        if(!JwtHelper.verifyToken(token)) {
            System.out.println("token有效");
        }else {
            System.out.println("token无效");
        }
    }

}

