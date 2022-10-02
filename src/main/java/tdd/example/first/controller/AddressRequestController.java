package tdd.example.first.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddressRequestController {

    @RequestMapping(value = "/test_url", method = RequestMethod.GET)
    public String MappingRequest(){
        return "addressRequest";
    }
}
