package org.example.Bo;

import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBo {

    boolean addStudent(StudentDto studentDto, Connection connection) throws SQLException;
    boolean updateStudent( String StudentId,StudentDto studentDto,Connection connection) throws SQLException;
    boolean deleteStudent(String id,Connection connection);
    ArrayList<StudentDto> getAllStudents(Connection connection);
    StudentDto getStudentById(int id,Connection connection);
}
