package com.example.daptech.util;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VirtualAndLocation {
    private static final String path="https://hcapi02.api.bdymkt.com/mobile";
    private static final String AccessKey="2b2d0124b3704927a0457d0699084316";
    private static final String SecretKey="5485db5f57b848fbbcdb3e9b92081254"; //短期套餐密钥,后续需要更换为长期密钥

    private static final Set<String> VIRTUAL_NUMBER_PREFIXES = new HashSet<>();

    static {
        // 常见的虚拟号码前缀
        VIRTUAL_NUMBER_PREFIXES.add("400");
        VIRTUAL_NUMBER_PREFIXES.add("800");
        VIRTUAL_NUMBER_PREFIXES.add("95");
        VIRTUAL_NUMBER_PREFIXES.add("0");
        VIRTUAL_NUMBER_PREFIXES.add("171");
        VIRTUAL_NUMBER_PREFIXES.add("172");
    }

    /**
     * 判断国内电话号码是否为虚拟号
     *
     * @param phoneNumber 电话号码
     * @return 是否为虚拟号
     */
    public static boolean isVirtual(String phoneNumber) {
        String prefix = phoneNumber.substring(0, 3); // 取前缀
        return VIRTUAL_NUMBER_PREFIXES.contains(prefix);
    }

    public static String getLocation(String phone) {
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.GET, path);

        request.setCredentials(AccessKey, SecretKey);
        // 设置query参数
        request.addQueryParameter("mobile", phone);


            ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

            try {
                ApiExplorerResponse response = client.sendRequest(request);

                // 返回结果格式为Json字符串
                // 解析Json字符串
                JsonNode jsonNode = parseJson(response.getResult());
                JsonNode data = jsonNode.get("data");

                // 获取location字段的值
                return data.get("city").asText();
            } catch (Exception e) {
                e.printStackTrace();
                return "未知"; //API库内查询不到或网络异常,返回未知
            }

    }
    public static JsonNode parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }

    public static void main(String[] args) {
        String phone = "075533620700";
        String location = getLocation(phone);
        System.out.println(location);
    }
}
