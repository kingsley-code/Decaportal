package com.decagon.recruitmentportal.controllers;
import com.decagon.recruitmentportal.pojos.APIResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/")
public class AppController {


    @GetMapping("/ping")
    public APIResponse<String> ping() {
        return new APIResponse<String>("success", true, "I am alive");
    }
}