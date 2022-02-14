package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello","age":20}
 * content-type: apllication/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException{
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("messageBody={},age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //RequestBody에 객체를 지정
    //HttpEntity나 RequestBody를 쓰면 Http 메시지 컨버터가 Http 바디 내용을
    //우리가 원하는 문자나 객체등으로 변환
    //컨버터는 문자 뿐만 아니라 JSON도 객체로 반환 , RequestBody 생략은 불가
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException{
        log.info("username={},age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //RequestBody가 아닌 HttpEntity 사용도 가능하긴 함
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity){
        HelloData data = httpEntity.getBody();
        log.info("username={},age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    //http 메시지 컨버터가 나갈 때도 적용 가능
    //data가 JSON으로 바뀜
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data){
        log.info("username={},age={}", data.getUsername(), data.getAge());
        return data;
    }

}
