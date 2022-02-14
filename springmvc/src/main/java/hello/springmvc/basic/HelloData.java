package hello.springmvc.basic;

import lombok.Data;

//Lombok @data를 쓰면 getter,setter 등을 자동으로 적용
@Data
public class HelloData {
    private String username;
    private int age;
}
