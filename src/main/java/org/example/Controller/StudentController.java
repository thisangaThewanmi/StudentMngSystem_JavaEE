package org.example.Controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/student",loadOnStartup = 2)
public class StudentController extends HttpServlet {

    @Override
    public void init() {

        Connection connection;

        //If the database connection parameters are the same for all servlets in your application, use getServletContext().

        var driverClass = getServletContext().getInitParameter("driver-class");
        var dbURL = getServletContext().getInitParameter("dbURL");
        var Password = getServletContext().getInitParameter("dbPassword");
        var username = getServletContext().getInitParameter("dbUserName");
        System.out.println("getServeletContext" + driverClass + dbURL + Password + username);


        //If these parameters are specific to this servlet, use getServletConfig()
          /*  var driverClass = getServletConfig().getInitParameter("driver-class");
            var dbURL = getServletConfig().getInitParameter("dbURL");
            var userName = getServletConfig().getInitParameter("dbUserName");
            var password = getServletConfig().getInitParameter("dbPassword");
        System.out.println("getServeletConfig()"+driverClass+dbURL+ Password+username);*/

        try {
            System.out.println("Attempting to load the database driver...");
            Class.forName(driverClass);
            System.out.println("Database driver loaded successfully.");

            System.out.println("Attempting to establish a database connection...");
            connection = DriverManager.getConnection(dbURL, username, Password);
            System.out.println("Database connection established successfully.");

            // Verify the connection is not null
            if (connection != null) {
                System.out.println("Connection object is not null, indicating a successful connection.");
            }


        } catch (SQLException e) {

                System.out.println("An error occurred while establishing the database connection.");
                e.printStackTrace(); // This will print the full stack trace to the console.
                throw new RuntimeException("Connection error", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get ok");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
