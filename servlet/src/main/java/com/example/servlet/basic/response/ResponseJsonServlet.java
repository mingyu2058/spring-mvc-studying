package com.example.servlet.basic.response;

import com.example.servlet.basic.HelloData;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        //json 형식, utf-8 향식이라 charset=utf-8과 같은 추가 파라미터를 지원하지 않음 의미없는 파라미터 추가할 필요 없다
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // 앞서있는 helloData객체를 JSON 형식으로
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);


    }
}
