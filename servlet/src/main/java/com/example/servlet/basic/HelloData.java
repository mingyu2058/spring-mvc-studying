package com.example.servlet.basic;

import lombok.Getter;
import lombok.Setter;

//lombok의 getter setter 쓰면 따로 getter, setter만들 필요 없음
@Getter @Setter
public class HelloData {
    private String username;
    private int age;

}
