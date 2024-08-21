package org.example.Controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Bo.StudentBo;
import org.example.Bo.StudentBoImpl;
import org.example.dto.StudentDto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/student",loadOnStartup = 2)
public class StudentController extends HttpServlet {

    Connection connection;
    @Override
    public void init() {



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

        try (var writer = resp.getWriter()) {  //try with resources eken writer eka gannawa



            if (req.getContentType().equals("application/json")) { //Context-type eka jsonnda kiyla balanwa

                Jsonb jsonb = JsonbBuilder.create(); //jsonBuilder ekak create krla
                StudentDto studentDto = jsonb.fromJson(req.getReader(), StudentDto.class); //ena data tika Dto ekak widihata convert krnawa



                StudentBo studentBo = new StudentBoImpl(); //studentBo ekak hadagena eka athule tiyena save method ekata dto and connection yawanawa

                try {
                    if (studentBo.addStudent(studentDto,connection)) { //true wunoth(Bo eke method eken return wenne boolean ekak)
                        writer.write("Student saved sucessfully");//writerta kiyano meka write keranna kila
                        resp.setStatus(HttpServletResponse.SC_CREATED);
                    }else {
                        writer.write("Something went wrong");
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);

                }

            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST); //send error
            return;
        }

        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("id"); //parameter eken ena data ek id eata ganno
            StudentDto studentDto = JsonbBuilder.create().fromJson(req.getReader(), StudentDto.class);

            StudentBo studentBo = new StudentBoImpl();

            try {
                if (studentBo.updateStudent(studentId, studentDto, connection)) {
                    writer.write("Student updated sucessfully");
                    System.out.println("Student updated sucessfully");// methanadi api SC_NO_CONTENT meka dana nisa staus ekata writer eka denne na mkda no content nisa
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    writer.write("Something went wrong");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }


        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        try (var writer = resp.getWriter()) {
            var studentId = req.getParameter("id");
            StudentBo studentBo = new StudentBoImpl();

            try {
                System.out.println("id: " + studentId);
                if(studentBo.deleteStudent(studentId, connection)) {
                    writer.write("Student deleted sucessfully");
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                }else{
                    writer.write("Something went wrong");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


    }


