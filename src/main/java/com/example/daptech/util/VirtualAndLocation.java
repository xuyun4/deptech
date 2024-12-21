package com.example.daptech.util;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class VirtualAndLocation {
    private static final String path="https://hcapi02.api.bdymkt.com/mobile";
    private static final String AccessKey="2b2d0124b3704927a0457d0699084316";
    private static final String SecretKey="5485db5f57b848fbbcdb3e9b92081254"; //短期套餐密钥,后续需要更换为长期密钥

    private static final String path2="https://apilayer.net/api/validate";
    private static final String AccessKey2="2473e403b98cf5652302c4bc07aafdc2";

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
     * @param phoneNumber 电话号码
     * @return 是否为虚拟号
     */
    public static boolean isVirtual(String phoneNumber) {
        String prefix = phoneNumber.substring(0, 3); // 取前缀
        return VIRTUAL_NUMBER_PREFIXES.contains(prefix);
    }

    /**
     * @param phoneNumber 电话号码
     * @return VirtualAndLocation对象，包含是否为虚拟号、归属地信息（城市）
     */

    public static com.example.daptech.entity.VirtualAndLocation getUSInformation(String phoneNumber) {
        com.example.daptech.entity.VirtualAndLocation vl = new com.example.daptech.entity.VirtualAndLocation();
        vl.setIsVirtual(false);
        vl.setLocation("US");

        try {
            // 构建请求 URL
            String requestUrl = path2 + "?access_key=" + AccessKey2 + "&number=" + phoneNumber+"&country_code=US&format=1";
            URL url = new URL(requestUrl);

            // 打开 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析 JSON 响应
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                System.out.println(jsonNode);
                // 检查是否有效
                if (jsonNode.get("valid").asBoolean()) {
                    // 获取归属地信息（城市）
                    String carrier = jsonNode.get("carrier").asText();

                    // 虚拟运营商列表
                    String[] virtualCarriers = {
                            "Google Fi", "Tracfone", "Ting", "Mint Mobile", "Lycamobile", "Boost Mobile", "Metro by T-Mobile"
                    };

                    // 遍历虚拟运营商列表,如果carrier字段中包含虚拟运营商,则判定为虚拟号
                    for (String vc : virtualCarriers) {
                        if (carrier.contains(vc)) {
                            vl.setIsVirtual(true);
                        }
                    }

                    // 获取归属地信息（城市）
                    String location = jsonNode.get("location").asText();
                    if (location != null && !location.isEmpty()) {
                        vl.setLocation(location);
                        }
                    return vl;
                } else {
                    return vl;
                }
            } else {
                System.out.println("请求失败，响应码: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vl; // API 库内查询不到或网络异常,返回不为虚拟号与US的默认值
    }

    /* 两个单独方法废弃,因为连续发送两次请求会超出速率限制,请求失败
    public static Boolean usIsVirtual(String phoneNumber) {
        try {
            // 构建请求 URL
            String requestUrl = path2 + "?access_key=" + AccessKey2 + "&number=" + phoneNumber+"&country_code=US&format=1";
            URL url = new URL(requestUrl);

            // 打开 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析 JSON 响应
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                System.out.println(jsonNode);
                // 检查是否有效
                if (jsonNode.get("valid").asBoolean()) {
                    // 获取归属地信息（城市）
                    String carrier = jsonNode.get("carrier").asText();

                    // 虚拟运营商列表
                    String[] virtualCarriers = {
                            "Google Fi", "Tracfone", "Ting", "Mint Mobile", "Lycamobile", "Boost Mobile", "Metro by T-Mobile"
                    };

                    // 遍历虚拟运营商列表,如果carrier字段中包含虚拟运营商,则判定为虚拟号
                    for (String vc : virtualCarriers) {
                        if (carrier.contains(vc)) {
                            return true;
                        }
                    }

                } else {
                    return false;
                }
            } else {
                System.out.println("请求失败，响应码: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // API 库内查询不到或网络异常,返回false
    }
*/

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
/*

    public static String getUSLocation(String phoneNumber) {
        try {
            // 构建请求 URL
            String requestUrl = path2 + "?access_key=" + AccessKey2 + "&number=" + phoneNumber+"&country_code=US&format=1";
            URL url = new URL(requestUrl);

            // 打开 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 读取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 解析 JSON 响应
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());
                System.out.println(jsonNode);

                // 检查是否有效
                if (jsonNode.get("valid").asBoolean()) {
                    // 获取归属地信息（城市）
                    String location = jsonNode.get("location").asText();
                    if (location != null && !location.isEmpty()) {
                        return location;
                    }
                    return jsonNode.get("country_name").asText();
                } else {
                    return "无效号码";
                }
            } else {
                System.out.println("请求失败，响应码: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "网络异常"; // API 库内查询不到或网络异常,返回网络异常
    }
*/



    public static JsonNode parseJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(json);
    }

    public static void main(String[] args) {
        String phone = "304-658-7567";
        com.example.daptech.entity.VirtualAndLocation vl = getUSInformation(phone);

        System.out.println(vl.getLocation());

        if(vl.getIsVirtual()){
            System.out.println("虚拟号");
        }else{
            System.out.println("非虚拟号");
        }
    }

}
