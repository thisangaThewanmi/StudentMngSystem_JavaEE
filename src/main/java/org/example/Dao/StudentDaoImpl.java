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
    public boolean update(Student student, Connection connection) {
        return false;
    }

    @Override
    public boolean delete(int id, Connection connection) {
        return false;
    }
    /* @Override*/
   /* public boolean addStudent(Student student) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SAVE_STUDENT);

            ps.setString(1, studentDto.getName());
            ps.setString(2, studentDto.getCity());
            ps.setString(3, studentDto.getEmail());
            ps.setString(4, studentDto.getLevel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    }

