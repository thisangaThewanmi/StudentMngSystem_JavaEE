package org.example.Dao;

import org.example.entity.Student;

public interface StudentDao {

    boolean addStudent(Student student);
    Student getStudent(int id);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}
