package com.anton.railway.booking.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter which sets specified locale
 */
@WebFilter(urlPatterns = {"/*"})
public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getParameter("locale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("locale"));
        }

        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {

    }
}