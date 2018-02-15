package com.spe.cms.restcontroller;

import com.spe.cms.controller.*;
import com.spe.cms.domain.Project;
import com.spe.cms.restcontroller.utils.Cryption;
import com.spe.cms.domain.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class StudentRESTController {

    StudentController studentController;
    TeacherController teacherController;

    @PostConstruct
    public void initialize() {
        studentController = new StudentController();
    }

    //    ### ALL STUDENTS ###
    @CrossOrigin
    @RequestMapping(value = "/students", method = POST)
    public List<Student> students(@RequestParam(value = "login_token") String login_token) {
        String user = Cryption.decrypt(login_token.split("!")[0]);
        String password = Cryption.decrypt(login_token.split("!")[1]);

        if (teacherController.isUserAndPassCorrect(user,password) == 0)
            return studentController.getAllStudents();
        else
            return new ArrayList<>();
    }
}
