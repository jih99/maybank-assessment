package com.maybank.assessment.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiController {

    @GetMapping("openapi.yaml")
    public Resource openapi(){
        return new ClassPathResource("openapi.yaml");

    }
}
