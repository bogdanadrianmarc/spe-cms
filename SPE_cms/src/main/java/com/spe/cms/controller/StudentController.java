package com.spe.cms.controller;

import com.spe.cms.domain.Student;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.StudentDBRepo;

import java.util.List;

public class StudentController {

    private StudentDBRepo studentDBRepo;

    public StudentController() {
        DBInit dbInit = new DBInit();
        studentDBRepo = new StudentDBRepo(dbInit.getProps());
    }

    public List<Student> getAllStudents()
    {
        return (List<Student>) studentDBRepo.findAll();
    }

    public Student getStudentById(String id)
    {
        return studentDBRepo.findOne(id);
    }

    public Integer isUserAndPassCorrect(String user, String password)
    {
        if (studentDBRepo.findOne(user) == null)
            return 1; //if incorrect user
        else
        if (!studentDBRepo.findOne(user).getPassword().equals(password))
            return 2; //if incorrect password
        else
            return 0; //if correct
    }

    public void setStudent(Student s)
    {
        studentDBRepo.save(s);
    }
}
