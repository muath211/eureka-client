package com.aliens;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("hala-feek")
public interface WordService {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String getWord();

}
