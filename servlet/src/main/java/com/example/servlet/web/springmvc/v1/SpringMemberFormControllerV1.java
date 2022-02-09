package com.example.servlet.web.springmvc.v1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//Controller만 있어도 자동으로 bean에 등록 가능 Cotroller가 @Component를 가짐

//이렇게 두개 다 쓰면 @Controller랑 같음
//@Component
//@RequestMapping
@Controller
public class SpringMemberFormControllerV1 {

    //@Controller가 있으면 @RequestMapping은 핸들러 정보구나 하고 꺼냄
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }
}
