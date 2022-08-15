package com.jack.interceptor;

import com.jack.result.Result;
import com.jack.result.ResultCodeEnum;
import com.jack.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author :Jack
 * @CreatTime : 2022/8/13
 * @Description :
 * 前端登录拦截器
 **/
public class LoginInterceptor implements HandlerInterceptor {

    // 执行controller前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userInfo = request.getSession().getAttribute("USER");
        if (null == userInfo){
            Result result = Result.build("未登陆", ResultCodeEnum.LOGIN_AUTH);
            WebUtil.writeJSON(response,result);
            return false;
        }
        return true;
    }

    // 执行controller,返回视图之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    // 执行controller,返回视图之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
