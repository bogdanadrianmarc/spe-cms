package com.spe.cms.controller;

import com.spe.cms.domain.Teacher;
import com.spe.cms.repository.DBInit;
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

    public Integer isUserAndPassCorrect(String user, String password)
    {
        if (teacherDBRepo.findOne(user) == null)
            return 1; //if incorrect
        else
            return 0; //if correct
    }

    public void setTeacher(Teacher t)
    {
        teacherDBRepo.save(t);
    }
}
