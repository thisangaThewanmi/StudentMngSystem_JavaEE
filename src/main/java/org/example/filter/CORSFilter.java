package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CORSFilter  extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        var origin = req.getHeader("Origin");
        var configuredOrigin = getServletContext().getInitParameter("origin");

        if(origin !=null && (configuredOrigin == null || origin.contains(configuredOrigin))) {

            res.setHeader("Access-Control-Allow-Origin", origin);

            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

            res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

            res.setHeader("Access-Control-Expose-Headers", "Content-Type");

            res.setHeader("Access-Control-Allow-Credentials","true");

        }

        chain.doFilter(req, res);
    }
}
