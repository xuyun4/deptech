/*
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
    void contextLoads() {
        String token = JwtHelper.createToken(12L,"admin",1);
        System.out.println(token);
        if(!JwtHelper.verifyToken(token)) {
            System.out.println("token有效");
        }else {
            System.out.println("token无效");
        }
    }

    @Test
    void test() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVc2VyIiwiZXhwIjoxNzMzNTcxMjUxLCJpZCI6MTIsInBob25lbnVtYmVyIjoiYWRtaW4iLCJzdGF0dXMiOjF9.D9n0gYv7Hr0WdYqbJon2-j2m1zIRw0QNTbQVIkn_p7Asno44fWSA81UH62Y_HQ-9ihvlZzhaMdWLygvWJuxymw";
        if(!JwtHelper.verifyToken(token)) {
            System.out.println("token有效");
        }else {
            System.out.println("token无效");
        }
    }

    @Test
    void test2(String phone) {
        String path = "https://hcapi02.api.bdymkt.com/mobile";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.GET, path);
        request.setCredentials("2b2d0124b3704927a0457d0699084316", "5485db5f57b848fbbcdb3e9b92081254");



        // 设置query参数
        request.addQueryParameter("mobile", phone);


        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

        try {
            ApiExplorerResponse response = client.sendRequest(request);

            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }




    }



}
*/
