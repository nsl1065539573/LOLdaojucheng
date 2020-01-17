package com.LOLdaojucheng.controller.common.intercepter;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.google.gson.Gson;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class Authoritylnterceptorprotal implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        if (userInfo==null){
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            ServerResponse serverResponse = ServerResponse.serverResponseByError("没有登录");
            Gson gson = new Gson();
            String json = gson.toJson(serverResponse);
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
            return false;
        }
        if (userInfo.getRole()!=Const.RoleEnum.ROLE_CUSTOMER.getCode()){
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            ServerResponse serverResponse = ServerResponse.serverResponseByError("没有权限");
            Gson gson = new Gson();
            String json = gson.toJson(serverResponse);
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
