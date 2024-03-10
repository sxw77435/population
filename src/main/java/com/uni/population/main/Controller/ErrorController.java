package com.uni.population.main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping(value = {"/denied"})
    public void accessDenied() {}

    @PostMapping(value = {"/login"})
    public void loginFailed() {}


}
