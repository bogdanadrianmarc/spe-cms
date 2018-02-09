package com.spe.cms.controller;

import com.spe.cms.domain.Teacher;
import com.spe.cms.repository.utils.DBInit;
import com.spe.cms.repository.TeacherDBRepo;

import java.util.List;

public class TeacherController {

    private TeacherDBRepo teacherDBRepo;

    public TeacherController() {
        DBInit dbInit = new DBInit();
        teacherDBRepo = new TeacherDBRepo(dbInit.getProps());
    }

    public List<Teacher> getAllTeachers()
    {
        return (List<Teacher>) teacherDBRepo.findAll();
    }

    public Teacher getTeacherById(String id)
    {
        return teacherDBRepo.findOne(id);
    }

    public Integer isUserAndPassCorrect(String username, String password)
    {
        if (teacherDBRepo.findOne(username) == null)
            return 1; //if incorrect user
        else
        if (!teacherDBRepo.findOne(username).getPassword().equals(password))
            return 2; //if incorrect password
        else
            return 0; //if correct
    }

    public void setTeacher(Teacher t)
    {
        teacherDBRepo.save(t);
    }
}
