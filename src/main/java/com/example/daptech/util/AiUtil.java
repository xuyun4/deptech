package com.example.daptech.util;


import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;

import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;

/**
 * 清华智库AI调用模块
 *
 * @author ColaBlack
 */
public class AiUtil {

    /**
     * 业务ID模版
     */
    private static final String REQUEST_ID_TEMPLATE = "teaAI-request-%s";


    /**
     * AI调用客户端
     */  //密钥为个人账号密钥
    private static final ClientV4 CLIENT = new ClientV4.Builder("3c57c7133d8f8be391e669949df77a99.u89xjUfReSdFayS1").build();


    public static String summarize(List<String> sentences) {
        String text = String.join(" ", sentences);
        return aiCaller("下文为不同用户对同一个来电手机号的评价信息,请对其进行总结概括,排除无效信息,选取高频重点词汇,要求结果为一句描述性的话:"+text);
    }

    public static String summarizeUS(List<String> sentences) {
        String text = String.join(" ", sentences);
        return aiCaller("下文为不同用户对同一个来电手机号的评价信息,请对其进行总结概括,排除无效信息,选取高频重点词汇,要求结果为一句描述性的英文句子:"+text);
    }
    /**
     * 调用AI接口(同步)
     *
     * @param prompt 提示词
     * @return AI返回的答案
     */
    public static String aiCaller(String prompt) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(chatMessage);
        String requestId = String.format(REQUEST_ID_TEMPLATE, System.currentTimeMillis());
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("GLM-4-Flash")  //该模型为免费模型
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .requestId(requestId)
                .build();
        ModelApiResponse invokeModelApiResp = CLIENT.invokeModelApi(chatCompletionRequest);
        return invokeModelApiResp.getData().getChoices().get(0).getMessage().getContent().toString();
    }

    /**
     * 调用AI接口(SSE)
     *
     */
    public static Flowable<ModelData> aiCallerFlow(String prompt) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(chatMessage);
        String requestId = String.format(REQUEST_ID_TEMPLATE, System.currentTimeMillis());
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("GLM-4-Flash").
                stream(Boolean.TRUE).
                invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .requestId(requestId)
                .build();
        ModelApiResponse invokeModelApiResp = CLIENT.invokeModelApi(chatCompletionRequest);
        return invokeModelApiResp.getFlowable();
    }

    public static void main(String[] args) {
        List<String> sentences = new ArrayList<>();
        sentences.add("骚扰电话");
        sentences.add("推销产品的");
        sentences.add("淘宝广告推销");
        sentences.add("骚扰电话");
        sentences.add("推销");
        sentences.add("淘宝");
        sentences.add("广告");
        sentences.add("推销");
        sentences.add("1233123");
        sentences.add("45345");
        sentences.add("676767");
        String result1 = summarizeUS(sentences);
/*        String result2 = summarize(sentences);*/
        System.out.println(result1);
/*        System.out.println(result2);*/
    }

}