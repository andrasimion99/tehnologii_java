package com.records.filter;

import com.records.wrapper.ResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "HtmlFilter", urlPatterns = {"/*"})
public class HtmlFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);

        chain.doFilter(request, wrapper);

        String content = wrapper.toString();
        content = "<p> Begining of the page Filter " + content + " <p> Goodbye from the page Filter!";
        out.write(content);

    }
}
