package org.example.Dao;

import org.example.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;

public interface StudentDao {

    boolean save(Student student, Connection connection) throws SQLException;
    Student getAll(int id,Connection connection);
    boolean update(Student student,Connection connection);
    boolean delete(int id,Connection connection);
}
