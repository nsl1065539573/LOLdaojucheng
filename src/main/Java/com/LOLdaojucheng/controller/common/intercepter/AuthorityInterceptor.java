package com.LOLdaojucheng.controller.common.intercepter;

import com.LOLdaojucheng.common.Const;
import com.LOLdaojucheng.common.ServerResponse;
import com.LOLdaojucheng.pojo.UserInfo;
import com.LOLdaojucheng.service.IUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService iUserService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo)session.getAttribute(Const.CURRENTUSER);

        if (userInfo==null){//从cookie中获取token信息
            Cookie[] cookies = request.getCookies();
            if (cookies!=null&&cookies.length>0){
                for (Cookie cookie :
                      cookies  ) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(Const.AUTOLOGINTOKEN)){
                        String autoLoginToken = cookie.getValue();
                        //根据token查询用户信息
                        userInfo = iUserService.findUserInfoByToken(autoLoginToken);
                        System.out.print(userInfo.getRole());
                        if (userInfo!=null){
                            session.setAttribute(Const.CURRENTUSER,userInfo);
                        }
                        break;
                    }
                }
            }

        }
        if (userInfo==null||userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            if (userInfo==null){
                ServerResponse serverResponse = ServerResponse.serverResponseByError("未登录");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
            }else {
                ServerResponse serverResponse = ServerResponse.serverResponseByError("没有权限");
                Gson gson = new Gson();
                String json = gson.toJson(serverResponse);
                printWriter.write(json);
            }
            printWriter.flush();
            printWriter.close();
            return false;
        }




        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器2");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("拦截器3");
    }
}
