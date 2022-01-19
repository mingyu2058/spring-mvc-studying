package com.example.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet",urlPatterns = "/response-header")

public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // status-line
        response.setStatus(HttpServletResponse.SC_OK);

        // response-header
        /*
        response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // 캐쉬 무효화
        response.setHeader("Pragma","no-cache"); //과거버전 캐시도 무효화
        response.setHeader("my-header","hello"); // 내가 원하는 임의의 헤더 생성
        */

        //Header의 편의 메서드
        content(response);

        //cookie의 편의 메서드
        cookie(response);

        //redirect의 편의 메서드
        redirect(response);


        // message body
        PrintWriter writer = response.getWriter();
        writer.println("안녕");

    }

    private void redirect(HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_FOUND);  ///  302
        response.sendRedirect("/basic/hello-form/html");
    }

    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie","good");
        cookie.setMaxAge(600); // 600초 동안 유효
        response.addCookie(cookie);
    }

    private void content(HttpServletResponse response) {

        // response.setHeader("Content-Type","text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        
//      response.setContentLength(2); // 생략시 바디 부분 보고 자동으로 생성
    }
}
