package com.anton.railway.booking.filter;

import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Rejects unauthorized access or redirects to next filter in chain
 */
@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");

        if (!req.getRequestURI().equals("/schedule")
                && !req.getRequestURI().equals("/searchPage")
                && !req.getRequestURI().startsWith("/search")
                && !req.getRequestURI().startsWith("/css")
                && !req.getRequestURI().startsWith("/img")
                && !req.getRequestURI().equals("/login")
                && !req.getRequestURI().equals("/register")
                && !req.getRequestURI().equals("/")) {
            if (req.getRequestURI().startsWith("/admin")) {
                if (user != null && user.getAccountStatus().equals(AccountStatus.ADMIN)) {
                    filterChain.doFilter(req, res);
                } else {
                    res.sendRedirect("/");
                }
            } else if (user == null) {
                res.sendRedirect("/login");
            } else {
                filterChain.doFilter(req, res);
            }
        } else  {
            filterChain.doFilter(req, res);
        }

    }

    @Override
    public void destroy() {

    }
}
