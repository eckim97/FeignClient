package dev.be.feign.service;

import dev.be.feign.common.dto.BaseRequestsInfo;
import dev.be.feign.common.dto.BaseResponseInfo;
import dev.be.feign.feign.client.DemoFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final DemoFeignClient demoFeignClient;

    public String get() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet("CustomHeader", "CustomName", 1L);
        System.out.println("Name : " + response.getBody().getName());
        System.out.println("Age : " + response.getBody().getAge());
        System.out.println("Header : " + response.getBody().getHeader());
        return "get";
    }

    public String post() {
        BaseRequestsInfo baseRequestsInfo = BaseRequestsInfo.builder()
                .name("customName")
                .age(2L)
                .build();
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callPost("CustomHeader", baseRequestsInfo);
        System.out.println("Name : " + response.getBody().getName());
        System.out.println("Age : " + response.getBody().getAge());
        System.out.println("Header : " + response.getBody().getHeader());
        return "post";
    }

    public String errorDecoder() {
        demoFeignClient.callErrorDecoder();
        return "post";
    }
}
