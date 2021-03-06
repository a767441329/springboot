/*
 * Copyright (c) 2019. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：实战SpringBoot
 * 类名称：LombokController.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhboot.controller;

import com.zhanghan.zhboot.controller.request.LombokRequest;
import com.zhanghan.zhboot.util.wrapper.WrapMapper;
import com.zhanghan.zhboot.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "演示Lombok控制器",tags = {"演示Lombok控制器"})
public class LombokController {

    private static Logger logger = LoggerFactory.getLogger(LombokController.class);

    @ApiOperation(value="演示Lombok",tags = {"演示Lombok控制器"})
    @RequestMapping(value = "/lombok", method = RequestMethod.POST)
    public Wrapper lombok(@RequestBody LombokRequest lombokRequest) {

        logger.info("lombok param {}", lombokRequest.toString());

        Map<String, Object> map = new HashMap();
        map.put("intLombok", lombokRequest.getIntLombok());
        map.put("strLombok", lombokRequest.getStrLombok());
        map.put("boleanLombok", lombokRequest.getBoleanLombok());
        map.put("personLombok", lombokRequest.getPersonLombok());
        return WrapMapper.ok(map);
    }

}
