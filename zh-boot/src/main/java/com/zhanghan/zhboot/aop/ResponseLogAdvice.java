/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：ResponseLogAdvice.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.aop;

import com.zhanghan.zhboot.util.FileBeatLogUtil;
import com.zhanghan.zhboot.util.HttpTypeUtil;
import com.zhanghan.zhboot.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@ControllerAdvice
public class ResponseLogAdvice implements ResponseBodyAdvice {

    @Autowired
    private Environment env;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            if (o != null) {

                Logger log = LoggerFactory.getLogger("logstashInfo");

                String applicationName = env.getProperty("spring.application.name");

                String responseParams = JsonUtil.objtoJson(o);

                String reqName = methodParameter.getDeclaringClass().getName() + "." + methodParameter.getMember().getName();

                FileBeatLogUtil.writeLog(log, applicationName, HttpTypeUtil.RESPONSE, reqName, responseParams.toString());

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return o;
    }
}