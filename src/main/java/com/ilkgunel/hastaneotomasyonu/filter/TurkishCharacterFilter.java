package com.ilkgunel.hastaneotomasyonu.filter;

import javax.servlet.*;
import java.io.*;

public class TurkishCharacterFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest requset, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        requset.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(requset, response);
    }
}
