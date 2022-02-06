package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ControllerV3;
import com.example.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
