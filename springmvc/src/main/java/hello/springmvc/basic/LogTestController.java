package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//RestController은 return 값이 view를 찾는 게 아닌 String 그대로 웹 페이지에 반환
//Rest API 사용할 때 핵심

@Slf4j
@RestController("/log-tes")
public class LogTestController {
    //Slf4j annotaion 시 이런 변수 선언없이 바로 가능 (lombok이 하게 해줌)
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping
    public String logTest(){
        String name = "Spring";

        // 계층화
        //Trace > Debug > Info > Warn > error
        //기본은 info 레벨 부터 찍힘
        //application.properties에서 loggin.level.hello.springmvc = x (x는 trace,debug....)

        log.trace("trace log={}", name);  // loggin.level.hello.springmvc=trace설정시 전체 보여줌
        log.debug("debug log={}", name);  // loggin.level.hello.springmvc=debug설정시 trace빼고 보여줌
        log.info("info log ={}", name);   // 설정시 info부터 warn,error
        log.warn("warn log={}", name);    // 설정시 warn,error
        log.error("error log={}", name);  // 설정시 error

        // log.trace("trace log={}" + name) 이것도 가능은 하지만
        // 불필요한 연산이 발생, 출력이 안되도 연산이 발생이 가능

        return "ok";
    }
}
