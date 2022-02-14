package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username ={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    //responsebody 이거 쓰면 반환 String을 뷰를 찾는 게 아니라 그대로 출력
    @ResponseBody
    @RequestMapping("request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username ={}, age={}", memberName, memberAge);
        return "ok";
    }

    // requestparam 안에 생략가능 단, 이때는 변수 이름이 parameter랑 같아야 함
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    //객체(members,user)가 아닌 String,int,Integer등
    //단순한 타입이면 @RequestParam도 생략 가능
    //근데 너무 없으면 인식이 어렵기도 함
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    //required true가 기본 값, url에 무조건 있어야 함
    //만약 false를 하면 null로 오기 때문에 int가 아닌 객체 Integer가 필요
    //빈문자는 null로 안오고 빈문자로 옴 String="" != String=null
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    //값이 안들어오면 default값으로 들어감
    //default가 있으면 null로 들어오는 게 아니기 때문에 Integer사용 안해도 됨
    //또한, required가 크게 필요x, 빈문자도 default로
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = false, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {

        log.info("username ={}, age={}", username, age);
        return "ok";
    }

    //Map으로 받으면 전체가 key,value로 다 옴 , parameter값이 1개라고 확실할 때
    //보통 1개만 씀
    //?userid1&userid2일 때
    //@RequestParam MultiValueMap으로 하면 여러 값이 올 수 있음
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {
        log.info("username ={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //modelattribute를 사용하지 않을때
    @ResponseBody
    @RequestMapping("/model-attribute-v0")
    public String modelAttribute(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //@ModelAttribute가 있으면 요청 파라미터 이름으로
    //HelloData 객체의 프로피터를 찾음 그리고 해당 프로피터의 setter호출해서 바인딩
    //*프로피터 => getUsername,setUsername이 있으면 username이 프로피터
    //잘못된 타입(int인데 String처럼)이 들어가면 바인딩 실패, exception발생
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //ModelAttribute 생략 가능
    //RquestParaam도 생략가능한데 그러면 뭘 사용??
    //스프링은 String,int,Integer 같은 단순 타입은 @RequestParam
    //그 이외에는 @ModelAttribute 적용(에외는 존재-argument resolver)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttribute(HelloData helloData) {
        log.info("username ={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
