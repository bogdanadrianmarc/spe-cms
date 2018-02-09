package com.spe.cms.restcontroller;

import com.spe.cms.controller.ClientController;
import com.spe.cms.controller.StudentController;
import com.spe.cms.controller.TeacherController;
import com.spe.cms.domain.Client;
import com.spe.cms.domain.Student;
import com.spe.cms.domain.Teacher;
import com.spe.cms.restcontroller.utils.Cryption;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(@RequestParam(value = "type") String type, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
    {
        if (type.equals("student"))
            if (studentController.isUserAndPassCorrect(username,password) == 0)
                return Cryption.encrypt(username)+"!"+Cryption.encrypt(password);
            else
                return "INCORRECT DETAILS";
        else
        if (type.equals("teacher"))
            if (teacherController.isUserAndPassCorrect(username,password) == 0)
                return Cryption.encrypt(username)+"!"+Cryption.encrypt(password);
            else
                return "INCORRECT DETAILS";
        else
        if (type.equals("client"))
            if (clientController.isUserAndPassCorrect(username,password) == 0)
                return Cryption.encrypt(username)+"!"+Cryption.encrypt(password);
            else
                return "INCORRECT DETAILS";
        else
            return "INCORRECT TYPE";
    }


//   ### REGISTER ###
    @CrossOrigin
    @RequestMapping(value = "/register", method = POST)
    public String register(@RequestParam(value = "type") String type, @RequestParam(value = "attributes") List<String> attributes)
    {
        if (type.equals("student"))
        {
            Student s = new Student(attributes.get(0), attributes.get(1), attributes.get(2));
            studentController.setStudent(s);
            return "OK";
        }
        else
        if (type.equals("teacher"))
        {
            Teacher t = new Teacher(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3));
            teacherController.setTeacher(t);
            return "OK";
        }
        else
        if (type.equals("client"))
        {
            Client c = new Client(attributes.get(0), attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4), attributes.get(5), attributes.get(6), attributes.get(7));
            clientController.setClient(c);
            return "OK";
        }
        else
            return "INCORRECT TYPE";
    }

}
