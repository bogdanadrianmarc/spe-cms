package com.spe.cms.restcontroller;

import com.spe.cms.controller.ClientController;
import com.spe.cms.controller.StudentController;
import com.spe.cms.controller.TeacherController;
import com.spe.cms.domain.Client;
import com.spe.cms.domain.Student;
import com.spe.cms.domain.Teacher;
import com.spe.cms.restcontroller.utils.Cryption;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AuthenticationRESTController {

    StudentController studentController;
    TeacherController teacherController;
    ClientController clientController;

    @PostConstruct
    public void initialize() {
        studentController = new StudentController();
        teacherController = new TeacherController();
        clientController = new ClientController();
    }


//   ### LOGIN ###
    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
    {
        if (studentController.isUserAndPassCorrect(username,password) == 0)
            return Cryption.encrypt(username)+"!"+Cryption.encrypt(password)+";"+"student";
        else
        if (teacherController.isUserAndPassCorrect(username,password) == 0)
            return Cryption.encrypt(username)+"!"+Cryption.encrypt(password)+";"+"teacher";
        else
        if (clientController.isUserAndPassCorrect(username,password) == 0)
            return Cryption.encrypt(username)+"!"+Cryption.encrypt(password)+";"+"client";
        else
            return "INCORRECT DETAILS";
    }


//   ### REGISTER ###
    @CrossOrigin
    @RequestMapping(value = "/register", method = POST)
    public String register(@RequestParam(value = "type") String type, @RequestParam(value= "attributes") String attributes)
    {
        String studentId = Cryption.decrypt(attributes.split(";")[0]);
        String password = Cryption.decrypt(attributes.split(";")[1]);
        String fullName = Cryption.decrypt(attributes.split(";")[2]);
        if (type.equals("student"))
        {
            Student s = new Student(studentId, password, fullName);
            studentController.setStudent(s);
            return "OK";
        }
        else
        if (type.equals("teacher"))
        {
            Teacher t = new Teacher(studentId, password, fullName, studentId);
            teacherController.setTeacher(t);
            return "OK";
        }
        else
        if (type.equals("client"))
        {
            Client c = new Client(studentId, password, fullName, studentId, password, fullName, studentId, password);
            clientController.setClient(c);
            return "OK";
        }
        else
            return "INCORRECT TYPE";
    }

}
