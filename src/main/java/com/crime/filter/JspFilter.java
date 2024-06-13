package com.crime.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("*.jsp")
public class JspFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!req.getServletPath().contains("/errorList/error.jsp")) { // Проверяем, чтобы запрос не перенаправлялся на саму страницу ошибки
            res.sendRedirect(req.getContextPath() + "/errorList/error.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }
}