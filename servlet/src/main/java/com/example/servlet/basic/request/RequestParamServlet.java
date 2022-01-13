package com.example.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");

        //paramname은 key 즉 username, age
        //value는 paraname.request로 가져옴 hello, 20
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName->System.out.println(paramName + "="+ request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단잎 파라미터 조회]");
        //실전에서는 이렇게 단일로 많이 씀
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        //key가 username인데 value가 usname = hello & username=hello2 이렇게 있을 때
        //so, 중복일 때는 getParameterValues 사용 , 처음 사용시 제일 첫번째 parameter 전송
        //중복으로 보내는 경우 잘 없음
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames){
            System.out.println("usernames = " + name);
        }


    }
}
