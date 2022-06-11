package com.example.springboot_3.api;

import com.alibaba.fastjson2.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author voidm
 * @date 2022/4/29
 */

@RestController
public class HelloController {
    
    @RequestMapping
    public Object hello(){
        HashMap<String, String> result = new HashMap<>();
        result.put("date", LocalDateTime.now().toString());
        return JSON.toJSONString(result);
    }
}
