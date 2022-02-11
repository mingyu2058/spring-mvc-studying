package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic")
    public String helloBasic() {
        log.info("basic");
        return ("ok");
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String helloGet() {
        log.info("basic");
        return ("ok");
    }

    @GetMapping(value = "/mapping-get-v2")
    public String helloGetV2() {
        log.info("basic");
        return ("ok");
    }

    //url 자체에 값
    //요즘에 경로 변수 많이 사용
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return ("ok");
    }

    @GetMapping("/mapping/{userId}/orders/{orders}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orders) {
        log.info("mappingPath userId={}, orders={}", userId, orders);
        return ("ok");
    }

    //parameter에 debug가 포함되어야만 가능, 잘 사용 X
    //ex) 127.0.0.1:8080/mapping-param?mode=debug
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return ("ok");
    }

    //특정 헤더로 추가 매핑 ( 즉 http header에 추가해서 보내야 함)
    @GetMapping(value = "mapping-header", headers = "mode-debug")
    public String mappingheader() {
        log.info("mappingHeader");
        return ("hello");
    }

    //Content-Type을 json이어야지 가능
    //consumes은 content-Type이랑 같은 말
    @PostMapping(value = "mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return ("hello");
    }

    //Accpet와 관련된거만 보낼 수 있다.
    //즉Accept가 Json을 받는데 지금 produces가 text/html이니 불가능
    @PostMapping(value = "mapping-produce", produces = "text/html")
    public String mappingResource(){
        log.info("mappingProduces");
        return ("hello");
    }

}
