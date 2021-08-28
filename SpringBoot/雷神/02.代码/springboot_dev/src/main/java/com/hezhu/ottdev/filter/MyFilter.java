package com.hezhu.ottdev.filter;


import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----- This is myFilter -----");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
