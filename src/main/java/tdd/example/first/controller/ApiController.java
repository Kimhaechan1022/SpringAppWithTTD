package tdd.example.first.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/api/test_url")
    public String MappingRequest3(){
        return  "restController Test";
    }
}
