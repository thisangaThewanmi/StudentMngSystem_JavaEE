package org.example.Bo;

import org.example.dto.StudentDto;

import java.util.ArrayList;

public class StudentBoImpl  implements StudentBo {
    @Override
    public boolean addStudent(StudentDto studentDto) {
        return false;
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) {
        return false;
    }


    @Override
    public ArrayList<StudentDto> getAllStudents() {
        return null;
    }

    @Override
    public StudentDto getStudentById(int id) {
        return null;
    }
}
