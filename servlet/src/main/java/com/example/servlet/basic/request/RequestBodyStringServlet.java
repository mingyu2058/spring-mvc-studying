package com.example.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(name = "requestBodyStringServlet",urlPatterns="/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //JSON이 아닌 단순히 문자로 주고 받을 때 사용하는 방버
        ServletInputStream inputStream = request.getInputStream(); // byte로 가져옴
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//byte를 인코딩할 때 어떤 문자 쓸지
        System.out.println("messageBody = " + messageBody);
        response.getWriter().write("ok");

    }

}
