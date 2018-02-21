package com.spe.cms.controller;

import com.spe.cms.domain.Teacher;
import com.spe.cms.repository.TeacherDBRepo;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherControllerTests {

    private Teacher c;
    private TeacherDBRepo repo;
    private TeacherController ctrl;

    @Before
    public void setup() {
        c = new Teacher("-1","2","3","4");
        repo = new TeacherDBRepo(DBInit.getProps());
        ctrl = new TeacherController();
    }

    @Test
    public void tests() {
        int siz = ctrl.getAllTeachers().size();
        ctrl.setTeacher(c);
        assert (ctrl.getAllTeachers().size() == siz+1);
        assert (ctrl.getTeacherById("-1").getPassword().equals("2"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}