package org.example.Bo;

import org.example.dto.StudentDto;
import org.example.entity.Student;

import java.util.ArrayList;

public interface StudentBo {

    boolean addStudent(StudentDto studentDto);
    boolean updateStudent(StudentDto studentDto);
    boolean deleteStudent(String id);
    ArrayList<StudentDto> getAllStudents();
    StudentDto getStudentById(int id);
}
