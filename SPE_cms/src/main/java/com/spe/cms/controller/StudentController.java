package com.spe.cms.controller;

import com.spe.cms.domain.Student;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.StudentDBRepo;

import java.util.List;

public class StudentController {

    private StudentDBRepo studentDBRepo;

    public StudentController() {
        studentDBRepo = new StudentDBRepo(DBInit.getProps());
    }

    public List<Student> getAllStudents()
    {
        return (List<Student>) studentDBRepo.findAll();
    }

    public Student getStudentById(String id)
    {
        return studentDBRepo.findOne(id);
    }

    public Integer isUserAndPassCorrect(String username, String password)
    {
        if (studentDBRepo.findOne(username) == null)
            return 1; //if incorrect user
        else
        if (!studentDBRepo.findOne(username).getPassword().equals(password))
            return 2; //if incorrect password
        else
            return 0; //if correct
    }

    public void setStudent(Student s)
    {
        studentDBRepo.save(s);
    }
}
