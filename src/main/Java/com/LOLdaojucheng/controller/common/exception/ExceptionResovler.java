package com.LOLdaojucheng.controller.common.exception;

import com.LOLdaojucheng.common.Const;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 全局异常处理类
 */
@Component
public class ExceptionResovler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status",Const.ResponseCode.REEOR);
        modelAndView.addObject("data",e.toString());
        modelAndView.addObject("msg","接口调用出错");
        return modelAndView;

    }
}
