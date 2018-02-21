package com.spe.cms.controller;

import com.spe.cms.domain.Student;
import com.spe.cms.repository.StudentDBRepo;
import com.spe.cms.repository.utils.DBInit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTests {

    private Student c;
    private StudentDBRepo repo;
    private StudentController ctrl;

    @Before
    public void setup() {
        c = new Student("-1","2","3");
        repo = new StudentDBRepo(DBInit.getProps());
        ctrl = new StudentController();
    }

    @Test
    public void tests() {
        int siz = ctrl.getAllStudents().size();
        ctrl.setStudent(c);
        assert (ctrl.getAllStudents().size() == siz+1);
        assert (ctrl.getStudentById("-1").getPassword().equals("2"));
        repo.delete("-1");
        assert (repo.size() == siz);
    }

}