package com.example.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHtmlServlet",urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type; text/html; charset-utf-8

        //이걸 먼저 선언해줘서 html 형식 + utf-8 인지 알 수 있게 해야함
        response.setContentType("text/html"); //html 형식
        response.setCharacterEncoding("utf-8");

        //html로 rendering 됨, 자바코드니깐 동적으로 만들 수 있음
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println("<div>안녕></div>");
        writer.println("<body>");
        writer.println("</html>");

    }
}
