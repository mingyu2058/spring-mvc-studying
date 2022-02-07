package com.example.servlet.web.frontcontroller.v4;

import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// controller한테 model을 같이 넘겨준다.
@WebServlet(name="frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();


    // class가 만들어질 때 key값(위치), controller를 저장
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get(requestURI)쓰면   http://127.0.0.1:8080/front-controller/v1/save  이 중 front-controller/v1/save  가져옴
        //get(requestURL)쓰면   전체를 다 가져옴
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        if(controller==null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
/* 기존 V3에서는 paramMap만 넘겨줬는데 V4에서는 model도 같이 넘겨줌
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        기존 V3에서 가져온 게필요 x, viewName을 바로 return 하니깐
        String viewName = mv.getViewName();
*/

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName);
        view.render(model,request,response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
