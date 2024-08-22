package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")//meken kiyanne ena request hama eakatama meka apply wenawa kila ekai wild card mapping ekak daa tiyenne mkk awath meka apply wenna ona
public class CORSFilter  extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        var origin = req.getHeader("Origin"); // ena request eke header eka gannawa (web.xml ekedi api header eka denawa)
        var configuredOrigin = getServletContext().getInitParameter("origin");//meken dd eken dena origin eka gannawa

        if(origin !=null && (configuredOrigin == null || origin.contains(configuredOrigin))) {

            // allowing the request's origin to access the resource.
            res.setHeader("Access-Control-Allow-Origin", origin);

            //acess eka denna ona methods
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

            //allow karann ona heaers
            res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

            //expose karana oa headers mnwada
            res.setHeader("Access-Control-Expose-Headers", "Content-Type");

            // allowing cookies and other credentials to be included in CORS requests.
            res.setHeader("Access-Control-Allow-Credentials","true");

        }

        chain.doFilter(req, res);
    }
}
