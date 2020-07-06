package com.anton.railway.booking.filter;

import com.anton.railway.booking.repository.entity.User;
import com.anton.railway.booking.repository.entity.enums.AccountStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Rejects unauthorized access or redirects to next filter in chain
 */
public class SecurityFilter implements Filter {
    private List<String> allowedURIs;
    private String ADMIN;
    private String HOME;
    private String LOGIN;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedURIs = Arrays.asList(filterConfig.getInitParameter("allowedURIs").split(", "));
        ADMIN = filterConfig.getInitParameter("admin");
        HOME = filterConfig.getInitParameter("home");
        LOGIN = filterConfig.getInitParameter("login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");
        String requestUri = req.getRequestURI();

        if (!checkIfUriIsAllowed(requestUri)) {
            if (requestUri.startsWith(ADMIN)) {
                if (user != null && user.getAccountStatus().equals(AccountStatus.ADMIN)) {
                    filterChain.doFilter(req, res);
                } else {
                    res.sendRedirect(HOME);
                }
            } else if (user == null) {
                res.sendRedirect(LOGIN);
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

    private boolean checkIfUriIsAllowed(String uri) {
        return uri.equals(HOME) || allowedURIs.stream().anyMatch(uri::startsWith);
    }
}
