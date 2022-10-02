package tdd.example.first.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressRequestController {

    @RequestMapping(value = "/test_url", method = RequestMethod.GET)
    public String MappingRequest(){
        return "addressRequest";
    }

    @ResponseBody
    @GetMapping("/test_url2")
    public String MappingRequest2(){
        return "This is response of get mapping";
    }
}
