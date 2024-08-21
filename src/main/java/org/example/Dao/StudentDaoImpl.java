package org.example.Dao;

import org.example.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {

    static String SAVE_STUDENT = "INSERT INTO Student (id,name,city,email,level) VALUES (?,?,?,?,?)";
    static String UPDATE_STUDENT = "UPDATE Student SET name=?, city=?, email=?, level=? WHERE id=?";
    static String GET_STUDENT = "SELECT * FROM Student WHERE id=?";
    static String DELETE_STUDENT = "DELETE FROM Student WHERE id=?";


    @Override
    public boolean save(Student student, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SAVE_STUDENT);
        ps.setString(1, student.getId());
        ps.setString(2, student.getName());
        ps.setString(3, student.getCity());
        ps.setString(4, student.getEmail());
        ps.setString(5, student.getLevel());

        return ps.executeUpdate() > 0;
    }



    @Override
    public Student getAll(int id, Connection connection) {
        return null;
    }

    @Override
    public boolean update(String StudentId,Student student, Connection connection) throws SQLException {



        PreparedStatement ps = connection.prepareStatement(UPDATE_STUDENT);
        ps.setString(1, student.getName());
        ps.setString(2, student.getCity());
        ps.setString(3, student.getEmail());
        ps.setString(4, student.getLevel());
        ps.setString(5, StudentId);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT);
        ps.setString(1, id);

        return ps.executeUpdate() > 0;
    }

    }

