package com.example.servlet.web.springmvc.v3;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //method = RequestMethod.GET  GET일 때 호출 가능 더 좋은 설계
    //@RequestMapping(value = "/new-form",method = RequestMethod.GET)
    // 더 줄임
    @GetMapping("/new-form")
    public String newForm(){
    //ModelAndView 말고 String으로 반환 가능
        return "new-form";
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model){
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }


    //@RequestMapping(value = "/save",method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age")int age,
            Model model){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
