package com.hezhu.ottdev.interceptor;

import com.hezhu.ottdev.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----- This is Interceptor -----");
        // 编写业务拦截规则
        // 从 session 获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        //判断用户是否登录
        if (null == user) {
            // 未登录
            response.sendRedirect(request.getContextPath() + "/user/error");
            String contextPath = request.getContextPath();
            System.out.println(contextPath);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
