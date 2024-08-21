package org.example.Bo;

import org.example.Dao.StudentDao;
import org.example.Dao.StudentDaoImpl;
import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBoImpl  implements StudentBo {

   StudentDao studentDao = new StudentDaoImpl();
    @Override
    public boolean addStudent(StudentDto studentDto,Connection connection) throws SQLException {
      return  studentDao.save(new Student(studentDto.getId(), studentDto.getName(), studentDto.getCity(), studentDto.getEmail(), studentDto.getLevel()),connection);
    }

    @Override
    public boolean updateStudent(String StudentId,StudentDto studentDto,Connection connection) throws SQLException {
        return studentDao.update(StudentId,new Student(studentDto.getId(),studentDto.getName(),studentDto.getCity(),studentDto.getEmail(),studentDto.getLevel()),connection);
    }

    @Override
    public boolean deleteStudent(String id,Connection connection) {
        return false;
    }

    @Override
    public ArrayList<StudentDto> getAllStudents(Connection connection) {
        return null;
    }

    @Override
    public StudentDto getStudentById(int id,Connection connection) {
        return null;
    }
}
